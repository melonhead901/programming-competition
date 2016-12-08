import com.google.common.base.Strings;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;

public class JamCoin {

    private static final BigInteger TWO = new BigInteger("2");

    public static void main(String[] args) throws FileNotFoundException {

        try (Scanner in = new Scanner(new File("input.txt"))) {
            int numCases = in.nextInt();
            in.nextLine();
            for (int i = 0; i < numCases; i++) {
                final String answer = processCase(in.nextInt(), in.nextInt());
                System.out.println("Case #" + (i + 1) + ": \n" + answer);
            }
        }
    }

    private static String processCase(int n, int j) {
        StringBuilder builder = new StringBuilder();
        String candidate = 1 + StringUtils.repeat("0", n - 2) + 1;
        for (int i = 0; i < j; i++) {
            String answer = generateJamCoin(bumpCandidate(candidate));
            final String proof = proveCandidate(answer).trim();
            if (Strings.isNullOrEmpty(proof)) {
                i--;
                candidate = bumpCandidate(candidate);
                continue;
            }
            builder.append(proof);
            builder.append("\n");
            candidate = answer;
        }
        return builder.toString();
    }

    private static final BigInteger[] bases = {
            TWO,
            new BigInteger("3"),
            new BigInteger("4"),
            new BigInteger("5"),
            new BigInteger("6"),
            new BigInteger("7"),
            new BigInteger("8"),
            new BigInteger("9"),
            BigInteger.TEN,
    };

    private static String proveCandidate(String answer) {
        StringBuilder builder = new StringBuilder();
        builder.append(answer);
        for (int i = 2; i <= 10; i++) {
            BigInteger numInBase = new BigInteger(answer, i);
//            System.out.println("Considering " + numInBase + " for base " + i);
            boolean success = false;

            for (BigInteger bi : bases) {
                if (bi.mod(new BigInteger("100000000")).equals(BigInteger.ZERO)) {
                    System.out.println("Skipping...");
                    return "";
                }
                if (numInBase.mod(bi).equals(BigInteger.ZERO)) {
//                    System.out.println("Found factor " + bi);
                    builder.append(" ").append(bi.toString());
                    success = true;
                    break;
                }
            }
            if (!success) {
                return "";
            }
        }
        return builder.toString();
    }

    private static String generateJamCoin(String candidate) {
        while (true) {
            if (isJamCoin(candidate)) {
                return candidate;
            }
            candidate = bumpCandidate(candidate);
        }
    }

    private static String bumpCandidate(String candidate) {
        String newVal = (new BigInteger(candidate, 2).add(TWO)).toString(2);
//        System.out.println("Bumping from " + candidate + " to " + newVal);
        return newVal;
    }

    static boolean isJamCoin(String candidate) {
        // System.out.println("Testing " + candidate);
        for (int i = 2; i <= 10; i++) {
            BigInteger bi = new BigInteger(String.valueOf(candidate), i);
            if (bi.isProbablePrime(100)) {
                return false;
            }
        }
        return true;
    }
}
