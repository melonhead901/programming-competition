import java.io.*;
import java.util.*;

public class CoinChangeProblem {

    // Complete the getWays function below.
    static long getWays(long n, long[] c) {
        Map<Long, Map<Integer, Long>> waysMap = new HashMap<>();
        return getWaysHelper(waysMap, c.length - 1, n, c);
    }

    static long getWaysHelper(Map<Long, Map<Integer, Long>> ways, int max, long n, long[] c) {
        if (n == 0) {
            return 1;
        }
        if (n < 0) {
            return 0;
        }
        if (max < 0) {
            return 0;
        }
        if (ways.containsKey(n) && ways.get(n).containsKey(max)) {
            return ways.get(n).get(max);
        }
        long waysForN = getWaysHelper(ways, max - 1, n, c) + getWaysHelper(ways, max, n - c[max], c);
        //System.out.println(String.format("Ways for %s %s", n, waysForN));
        if (!ways.containsKey(n)) {
            ways.put(n, new HashMap<>());
        }
        ways.get(n).put(max, waysForN);
        return waysForN;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        long[] c = new long[m];

        String[] cItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < m; i++) {
            long cItem = Long.parseLong(cItems[i]);
            c[i] = cItem;
        }

        // Print the number of ways of making change for 'n' units using coins having the values given by 'c'

        long ways = getWays(n, c);

        System.out.println(ways);

        scanner.close();
    }
}

