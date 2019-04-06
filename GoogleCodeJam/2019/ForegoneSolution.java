import java.util.Scanner;

public class ForegoneSolution {

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        int numCases = in.nextInt();
        for (int i = 1; i <= numCases; i++) {
            System.out.println("Case #" + i + ": " + processCaseBetter(in));
        }
    }

    private static String processCase(Scanner in) throws Exception {
        int n = in.nextInt();
        for (int i = 1; i <= (n / 2); i++) {
            if (contains4(i)) {
                continue;
            }
            if (!contains4(n - i)) {
                return String.format("%s %s", i, n - i);
            }
        }
        throw new Exception("impossible");
    }

    private static String processCaseBetter(Scanner in) throws Exception {
        int n = in.nextInt();
        int a = n / 2;
        int b = n - a;
        char[] ca = String.valueOf(a).toCharArray();
        char[] cb = String.valueOf(b).toCharArray();
        for (int i = ca.length - 1; i >= 0; i--) {
            while ((ca[i] == '4') || (cb[i] == '4')) {
                ca[i]++;
                cb[i]--;
            }
        }
        return String.format("%s %s", String.valueOf(ca), String.valueOf(cb));
    }

    private static boolean contains4(int i) {
        return String.valueOf(i).contains("4");
    }
}
