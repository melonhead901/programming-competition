import java.util.Scanner;

public class BinaryString {

    private DigitString string;
    private final RequiredStringLength requiredLength;

    private BinaryString(String string, int requiredLength) {
        this.string = new DigitString(string);
        this.requiredLength = new RequiredStringLength(requiredLength);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        BinaryString binaryString = new BinaryString(in.nextLine().trim(), 35651584);
        binaryString.randomize();
        binaryString.printChecksumLength();
    }

    private void randomize() {
        while (!longEnough()) {
            string = string.extend();
        }
    }

    private void printChecksumLength() {
        if (!longEnough()) {
            throw new IllegalStateException();
        }

        Checksum checkSum = string.createChecksum(requiredLength);

        while (!checkSum.isReady()) {
            checkSum = checkSum.performSingleChecksumRound();
        }

        System.out.println(checkSum);
    }

    private boolean longEnough() {
        return string.satisfiesLength(requiredLength);
    }

}

class Checksum extends DigitString {
    public Checksum(String bareString) {
        super(bareString);
    }

    public boolean isReady() {
        return (string.length() % 2) != 0;
    }

    public Checksum performSingleChecksumRound() {
        StringBuilder newCheckSum = new StringBuilder();
        for (int i = 0; i < string.length(); i += 2) {
            char c1 = string.charAt(i);
            char c2 = string.charAt(i + 1);
            if (c1 == c2) {
                newCheckSum.append("1");
            } else {
                newCheckSum.append("0");
            }
        }
        return new Checksum(newCheckSum.toString());
    }

}

class DigitString {
    protected String string;

    public DigitString(String bareString) {
        this.string = bareString;
    }

    public DigitString extend() {
        String copy = string;
        copy = new StringBuilder(copy).reverse().toString();
        copy = flipBits(copy);
        string = string + "0" + copy;
        return new DigitString(copy);
    }

    private String flipBits(String string) {
        StringBuilder builder = new StringBuilder();
        for (char c : string.toCharArray()) {
            addOppositeCharToBuilder(builder, c);
        }
        return builder.toString();
    }

    private void addOppositeCharToBuilder(StringBuilder builder, char c) {
        switch (c) {
            case '0':
                builder.append(1);
                break;
            case '1':
                builder.append(0);
                break;
            default:
                throw new IllegalArgumentException("Unexpected char: " + c);
        }
    }

    boolean satisfiesLength(RequiredStringLength requiredStringLength) {
        return string.length() > requiredStringLength.length;
    }

    public String trimToLength(RequiredStringLength requiredLength) {
        return string.substring(0, requiredLength.length);
    }

    public Checksum createChecksum(RequiredStringLength requiredLength) {
        return new Checksum(trimToLength(requiredLength));
    }
}

class RequiredStringLength {
    final int length;

    RequiredStringLength(int length) {
        this.length = length;
    }
}