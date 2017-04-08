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
        int n = in.nextInt();
        int lastTidy = 1;
        for (int i = 0; i <= n; i++) {
            if (isTidy(i)) {
                lastTidy = i;
            }
        }
        return lastTidy + "";
    }

    private static boolean isTidy(int n) {
        char[] chars = (n + "").toCharArray();
        for (int i = 0; i + 1 < chars.length; i++) {
            if (chars[i] > chars[i+1]) {
                return  false;
            }
        }
        return true;
    }
}
