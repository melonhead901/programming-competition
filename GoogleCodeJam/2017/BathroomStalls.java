import java.util.Scanner;

/**
 * Created by kdonohue on 4/8/17.
 */
public class BathroomStalls {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numCases = in.nextInt();
        for (int i = 1; i <= numCases; i++) {
            System.out.println("Case #" + i + ": " + processCase(in));
        }
    }

    private static String processCase(Scanner in) {
        int n = in.nextInt();
        int k = in.nextInt();
        boolean[] occupied = new boolean[n];
        int[] distL = new int[n];
        int[] distR = new int[n];
        for (int i = 0; i < k; i++) {
            recalculateDistL(distL, occupied);
            recalculateDistR(distR, occupied);
            int seatToOccupy = chooseSeatToOccupy(distL, distR);
            occupied[seatToOccupy] = true;
        }

        return null;

    }

    private static int chooseSeatToOccupy(int[] distL, int[] distR) {
        return 0;
    }

    private static void recalculateDistR(int[] distR, boolean[] occupied) {
        for (int i = 0; i < distR.length; i++) {
            distR[i] = 0;
            for (int j = i + 1; (j < distR.length) && !occupied[j]; j++) {
                distR[i]++;
            }
        }
    }

    private static void recalculateDistL(int[] distL, boolean[] occupied) {
        for (int i = distL.length - 1; i >= 0; i--) {
            distL[i] = 0;
            for (int j = i - 1; (j >= 0) && !occupied[j]; j--))))) {
                distL[i]--;
            }
        }
    }
}
