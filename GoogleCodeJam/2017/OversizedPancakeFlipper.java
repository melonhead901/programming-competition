import java.util.Scanner;

/**
 * Created by kdonohue on 4/7/17.
 */
public class OversizedPancakeFlipper {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numCases = in.nextInt();
        for (int i = 1; i <= numCases; i++) {
            System.out.println("Case #" + i + ": " + processCase(in));
        }

    }

    private static String processCase(Scanner in) {
        char[] chars = in.next().toCharArray();
        int k = in.nextInt();
        int countFlips = 0;
        for (int i = 0; i <= chars.length - k; i++) {
            if (chars[i] == '-') {
                flip(chars, i, k);
                countFlips++;
            }
        }
        //System.out.println("after all flips");
        //System.out.println(chars);
        for (int i = chars.length - k; i < chars.length; i++) {
            if (chars[i] == '-') {
                return "IMPOSSIBLE";
            }
        }
        return countFlips + "";
    }

    private static void flip(char[] chars, int start, int k) {
        for (int i = 0; i < k; i++) {
            chars[start + i] = (chars[start + i] == '-') ? '+' : '-';
        }
        //System.out.println(chars);
    }
}
