import java.util.Scanner;

public class BinaryString {

    private String string;
    private final int requiredLength;

    private BinaryString(String string, int requiredLength) {
        this.string = string;
        this.requiredLength = requiredLength;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        BinaryString binaryString = new BinaryString(in.nextLine().trim(), 272);
        binaryString.randomize();
        binaryString.printChecksumLength();
    }

    private void randomize() {
        while (!longEnough()) {
            String copy = string;
            copy = new StringBuilder(copy).reverse().toString();
            copy = flipBits(copy);
            string = string + "0" + copy;
        }
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

    private void printChecksumLength() {
        if (!longEnough()) {
            throw new IllegalStateException();
        }

        String checkSum = string.substring(0, requiredLength);

        while ((checkSum.length() % 2) == 0) {
            checkSum = performSingleChecksumRound(checkSum);
        }

        System.out.println(checkSum);
    }

    private String performSingleChecksumRound(String checkSum) {
        StringBuilder newCheckSum = new StringBuilder();
        for (int i = 0; i < checkSum.length(); i += 2) {
            char c1 = checkSum.charAt(i);
            char c2 = checkSum.charAt(i + 1);
            if (c1 == c2) {
                newCheckSum.append("1");
            } else {
                newCheckSum.append("0");
            }
        }
        return  newCheckSum.toString();
    }

    private boolean longEnough() {
        return string.length() >= requiredLength;
    }

}
