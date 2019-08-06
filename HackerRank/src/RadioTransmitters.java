import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class RadioTransmitters {

    // Complete the hackerlandRadioTransmitters function below.
    static int hackerlandRadioTransmitters(int[] x, int k) {

        List<Integer> locations = new ArrayList<>();
        for (int i : x) {
            locations.add(i);
        }
        Collections.sort(locations);

        int minCovered = Integer.MIN_VALUE;
        int count = 0;

        for (int i : locations) {
            if (i > minCovered) {
                System.out.println(i + " uncovered");
                count++;
                int placement = i;
                for (int j = i + k; j > i; j--) {
                    if (locations.contains(j)) {
                        placement = j;
                        break;
                    }
                }
                System.out.println("placing at " + placement);
                minCovered = placement + k;
                System.out.println("covering up to " + minCovered);
            } else {
                System.out.println(i + " covered already");
            }
        }

        return count;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[] x = new int[n];

        String[] xItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int xItem = Integer.parseInt(xItems[i]);
            x[i] = xItem;
        }

        int result = hackerlandRadioTransmitters(x, k);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

