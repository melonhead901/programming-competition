import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RevengeOfThePancakes {

    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner in = new Scanner(new File("input.txt"))) {
            int numCases = in.nextInt();
            in.nextLine();
            for (int i = 0; i < numCases; i++) {
                final long answer = processCase(in.nextLine());
                System.out.println("Case #" + (i+1) + ": " + (answer == -1 ? "INSOMNIA" : answer));
            }
        }

    }

    private static long processCase(String str) {
        int count = 0;
        char[] vals = str.toCharArray();
//        System.out.println(vals);
        for (int i = vals.length-1 ; i >=0; i--) {
            if (vals[i] =='-') {
                flip(vals, i);
                count++;
            }
        }
        return count;
    }

    private static void flip(char[] vals, int i) {
        for (int j = 0; j <= i; j++) {
            if (vals[j] == '-') {
                vals[j] = '+';
            } else if (vals[j] == '+') {
                vals[j] = '-';
            } else {
                throw new IllegalStateException();
            }
        }

    }

}
