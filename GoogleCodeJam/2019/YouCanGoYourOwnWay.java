import java.util.Scanner;

public class YouCanGoYourOwnWay {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        int numCases = in.nextInt();
        in.nextLine();
        for (int i = 1; i <= numCases; i++) {
            System.out.println("Case #" + i + ": " + processCase(in));
        }
    }

    private static String processCase(Scanner in) {
        int n = in.nextInt();
        in.nextLine();
        String them = in.nextLine();
        return them.replaceAll("S", "X").replaceAll("E", "S").replaceAll("X", "E");
    }
}
