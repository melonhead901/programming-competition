package hackercup2016;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class ThePriceIsCorrect {

    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner in = new Scanner(new File("price.txt"))) {
            int cases = in.nextInt();
            for (int i = 0; i < cases; i++) {
                processCase(in, i + 1);
            }
        }
    }

    private static final long UNCALC_INDIC = -1;
    private static long price;
    private static long[] boxes;
    private static int numBoxes;

    private static void processCase(Scanner in, int caseNum) {
        numBoxes = in.nextInt();
        price = in.nextInt();
        boxes = new long[numBoxes];
        for (int i = 0; i < numBoxes; i++) {
           boxes[i] = in.nextLong();
        }
        long[] ways = new long[numBoxes];
        Arrays.fill(ways, UNCALC_INDIC);
        recur(ways, 0, 0);
        long sum = 0;
        for (int i = 0; i < numBoxes; i++) {
           sum += ways[i];
        }
        System.out.println("Case #" + caseNum + ": " + sum);

    }

    private static long recur(long[] ways, int pos, long sumSoFar) {
        if (pos >= numBoxes) {
            return 0;
        }
        if (ways[pos] != UNCALC_INDIC) {
            return UNCALC_INDIC;
        }
        long val = boxes[pos];
        long answer = 0;
        if (sumSoFar + val <= price) {
             answer += 1 + recur(ways, pos+1, sumSoFar + val);
        } else {
            answer += recur(ways, pos+1, 0);
        }
        ways[pos] = answer;
        return answer;
    }

}
