import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by kdonohue on 1/2/17.
 */
public class ChiefHopper {
    private final int[] buildings;

    private ChiefHopper(int size) {
        buildings = new int[size];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        ChiefHopper hopper = new ChiefHopper(n);
        for (int i = 0; i < n; i++) {
            hopper.addBuilding(i, in.nextInt());
        }
        hopper.printFinalAnswer();
    }


    private void printFinalAnswer() {
        int max = findMax();
        // We know half of the current didn't work, so start the search there.
        int min = max / 2;
        while (min < max) {
            int guess = (int) ((min + max) / 2.0);
            if (canPassWith(guess)) {
                max = Math.min(max - 1, guess);
            } else {
                min = Math.max(min + 1, guess);
            }
        }
        System.out.println(max);
    }

    private int findMax() {
        int guess = 1;
        while (!canPassWith(guess)) {
            guess *= 2;
        }
        return guess;
    }

    private boolean canPassWith(long guess) {
        int theMax = Arrays.stream(buildings).max().getAsInt();
        for (int buildingHeight : buildings) {
            // Optimization: if we have more energy than the max building, all other values will be positive.
            if (guess > theMax) {
                return true;
            }
            guess += guess - buildingHeight;
            if (guess < 0) {
                return false;
            }
        }
        return guess >= 0;
    }

    private void addBuilding(int i, int nextInt) {
        buildings[i] = nextInt;
    }
}
