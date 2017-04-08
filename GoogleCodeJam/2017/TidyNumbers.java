import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by kdonohue on 4/7/17.
 */
public class TidyNumbers {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numCases = in.nextInt();
        for (int i = 1; i <= numCases; i++) {
            System.out.println("Case #" + i + ": " + processCase(in));
        }
    }

    private static String processCase(Scanner in) {
        BigInteger n = in.nextBigInteger();
        while (!isTidy(n.toString())) {

            char[] chars = n.toString().toCharArray();
            for (int i = chars.length - 1; i > 0; i--) {
                if (chars[i] < chars[i - 1]) {
                    chars[i] = '9';
                    while (chars[i-1] == '0') {
                        chars[i-1] = '9';
                        chars[i-2]--;
                        i--;
                    }
                    chars[i-1]--;
                }
                //System.out.println(chars);
            }
            n = new BigInteger(new String(chars));
        }

        return n.toString();
    }

    private static boolean isTidy(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i + 1 < chars.length; i++) {
            if (chars[i] > chars[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
