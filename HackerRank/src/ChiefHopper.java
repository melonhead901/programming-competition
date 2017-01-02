import java.util.Scanner;

/**
 * Created by kdonohue on 1/2/17.
 */
public class ChiefHopper {
    private final int[] buildings;

    public ChiefHopper(int size) {
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
        int min = 0;
        while (min < max) {
            int guess = (int) Math.floor((min+max)/2.0);
            System.err.print(String.format("Guessing: %s, [%s, %s]: ", guess, min, max));
            if (canPassWith(guess)) {
                System.err.println(" SUCCESS");
                max = Math.min(max-1, guess);
            } else {
                System.err.println(" FAILURE");
                min = Math.max(min+1,guess);
            }
        }
        System.err.println("Max that worked: " + max);
        System.out.println(max);
    }

    private int findMax() {
        int guess = 1;
        while (!canPassWith(guess)) {
            guess *= 2;
        }
        return  guess;
    }

    private boolean canPassWith(int guess) {
        System.err.print(guess + ": ");
        for (int buildingHeight : buildings) {
            System.err.print(guess + ", ");
            if (guess < 0) {
                return false;
            }
            guess += guess - buildingHeight;
        }
        System.err.println( "FINAL: " + guess);
        return guess > 0;
    }

    private void addBuilding(int i, int nextInt) {
        buildings[i] = nextInt;
    }
}
