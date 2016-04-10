import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class CountingSheep {

    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner in = new Scanner(new File("input.txt"))) {
            int numCases = in.nextInt();
            for (int i = 0; i < numCases; i++) {
                final long answer = processCase(in.nextInt());
                System.out.println("Case #" + (i+1) + ": " + (answer == -1 ? "INSOMNIA" : answer));
            }
        }
    }

    private static long processCase(int nextInt) {
        Set<Integer> allSeenInts = new HashSet<>();
        Set<Set<Integer>> digitsSeenSets = new HashSet<>();
        long runningNum = nextInt;
        while (allSeenInts.size() < 10) {
//            System.out.println("running num " + runningNum);
            if (nextInt <= 0) {
                return -1;
            }
            Set<Integer> digitsForRunningNum = new HashSet<>();
            long runningNumCopy = runningNum;
            while (runningNumCopy > 0) {
               digitsForRunningNum.add((int) (runningNumCopy % 10));
               runningNumCopy /= 10;
            }
            allSeenInts.addAll(digitsForRunningNum);
            digitsSeenSets.add(digitsForRunningNum);
            if (allSeenInts.size() == 10) {
                return runningNum;
            }
            runningNum += nextInt;
        }

        return runningNum - nextInt;
    }

}
