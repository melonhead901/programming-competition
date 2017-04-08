import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BinaryStringRandomizer {

    private DigitString string;
    private final RequiredStringLength requiredLength;

    private BinaryStringRandomizer(DigitString string, RequiredStringLength requiredLength) {
        this.string = string;
        this.requiredLength = requiredLength;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        BinaryStringRandomizer binaryString = new BinaryStringRandomizer(DigitString.createDigitString(in.nextLine()), new
            RequiredStringLength
            (272));
        binaryString.randomize();
        binaryString.printChecksumLength();
    }

    private void randomize() {
        while (!longEnough()) {
            string = string.extend();
        }
    }

    private boolean longEnough() {
        return string.satisfiesLength(requiredLength);
    }

    private void printChecksumLength() {
        if (!longEnough()) {
            throw new IllegalStateException();
        }

        Checksum checkSum = string.createChecksum(requiredLength);

        while (!checkSum.isReady()) {
            checkSum = checkSum.performSingleChecksumRound();
        }

        checkSum.printOut();
    }

}

abstract class AbstractDigitString {
    protected List<BinaryDigit> binaryDigitList;

    public AbstractDigitString(List<BinaryDigit> digits) {
       this.binaryDigitList = digits;
    }
}

class Checksum extends AbstractDigitString {

    public Checksum(List<BinaryDigit> digits) {
        super(digits);
    }

    public boolean isReady() {
        return (binaryDigitList.size() % 2) != 0;
    }

    public Checksum performSingleChecksumRound() {
        List<BinaryDigit> binaryDigits = new ArrayList<>();
        for (int i = 0; i < binaryDigitList.size(); i += 2) {
            processSinglePair(binaryDigits, i);
        }
        return new Checksum(binaryDigits);
    }

    private void processSinglePair(List<BinaryDigit> newCheckSum, int i) {
        BinaryDigit c1 = binaryDigitList.get(i);
        BinaryDigit c2 = binaryDigitList.get(i + 1);
        if (c1.getClass() == c2.getClass()) {
            newCheckSum.add(new DigitOne());
        } else {
            newCheckSum.add(new DigitZero());
        }
    }

    public void printOut() {
        for (BinaryDigit bd : binaryDigitList) {
            bd.printOut();
        }
    }
}

class DigitString extends AbstractDigitString {

    public DigitString(List<BinaryDigit> digits) {
        super(digits);
    }

    public static DigitString createDigitString(String bareString) {
        List<BinaryDigit> binaryDigitList = new ArrayList<>();
        for (char c : bareString.toCharArray()) {
            binaryDigitList.add(BinaryDigit.produceForChar(c));
        }
        return new DigitString(binaryDigitList);
    }

    public DigitString extend() {
        List<BinaryDigit> copy = new ArrayList<>(binaryDigitList);
        Collections.reverse(copy);
        flipBits(copy);
        binaryDigitList.add(new DigitZero());
        binaryDigitList.addAll(copy);
        return new DigitString(binaryDigitList);
    }

    private void flipBits(List<BinaryDigit> digits) {
        digits.replaceAll(BinaryDigit::produceOpposite);
    }

    public boolean satisfiesLength(RequiredStringLength requiredStringLength) {
        return binaryDigitList.size() > requiredStringLength.length;
    }

    public Checksum createChecksum(RequiredStringLength requiredLength) {
        List<BinaryDigit> binaryDigits = new ArrayList<>();
        for (int i = 0; (i < requiredLength.length) && (i < binaryDigitList.size()); i++) {
            binaryDigits.add(binaryDigitList.get(i));
        }
        return new Checksum(binaryDigits);
    }
}

class RequiredStringLength {
    final int length;

    RequiredStringLength(int length) {
        this.length = length;
    }
}

abstract class BinaryDigit {
    abstract BinaryDigit produceOpposite();

    public static BinaryDigit produceForChar(char c) {
        switch (c) {
            case '0':
                return new DigitZero();
            case '1':
                return new DigitOne();
            default:
                throw new IllegalArgumentException();
        }
    }

    public abstract void printOut();
}

class DigitOne extends BinaryDigit {
    @Override
    BinaryDigit produceOpposite() {
        return new DigitZero();
    }

    @Override
    public void printOut() {
        System.out.print("1");
    }
}

class DigitZero extends BinaryDigit {
    @Override
    BinaryDigit produceOpposite() {
        return new DigitOne();
    }

    @Override
    public void printOut() {
        System.out.print("0");
    }
}

