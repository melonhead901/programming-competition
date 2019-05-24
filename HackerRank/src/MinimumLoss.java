import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class MinimumLoss {

    // Complete the minimumLoss function below.
    static int minimumLossSlow(long[] price) {
        int val = Integer.MAX_VALUE;
        for (int i = 0; i < price.length; i++) {
            for (int j = i + 1; j < price.length; j++) {
                int diff = (int) (price[i] - price[j]);
                if (diff > 0) {
                    val = Math.min(val, diff);
                }
            }
        }
        return val;
    }

    static int minimumLoss(long[] price) {
        Map<Long, Integer> map = new HashMap<>(price.length);
        for (int i = 0; i < price.length; i++) {
            map.put(price[i], i);
        }
        List<Long> sorted = new ArrayList<>(map.keySet());
        Collections.sort(sorted);

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < (sorted.size() - 1); i++) {
            long first = sorted.get(i);
            long second = sorted.get(i+1);
            if (map.get(first) > map.get(second)) {
                result = (int) Math.min(result, second - first);
            }
        }
        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        long[] price = new long[n];

        String[] priceItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            long priceItem = Long.parseLong(priceItems[i]);
            price[i] = priceItem;
        }

        int result = minimumLoss(price);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

