import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by kdonohue on 1/2/17.
 */
public class ChiefHopper {
    private int[] buildings;

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
        hopper.finishBuildings();
        hopper.printFinalAnswer();
    }

    private void finishBuildings() {
        /*
        System.err.println("Initial array: " + Arrays.toString(buildings));
        int maxLoc = 0;
        int max = Arrays.stream(buildings).max().getAsInt();
        System.err.println("max val " + max);
        for (int i = 0; i < buildings.length; i++) {
           if (max == buildings[i]) {
               maxLoc = i + 1;
               System.err.println("max loc " + maxLoc);
               break;
           }
        }
        int[] newBuildings = new int[maxLoc];
        System.arraycopy(buildings, 0, newBuildings, 0, maxLoc);
        buildings = newBuildings;
        System.err.println("Final array: " + Arrays.toString(buildings));
        */
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

    private boolean canPassWith(long guess) {
        System.err.print(guess + "{");
        int theMax = Arrays.stream(buildings).max().getAsInt();
        for (int buildingHeight : buildings) {
            if (guess > theMax) {
                System.err.println("CLEARED}");
                return true;
            }
            guess += guess - buildingHeight;
            System.err.print(guess + "->");
            if (guess < 0) {
                System.err.println("FAILED}");
                return false;
            }
        }
        System.err.println( "FINAL: " + guess + "}");
        return guess >= 0;
    }

    private void addBuilding(int i, int nextInt) {
        buildings[i] = nextInt;
    }
}
