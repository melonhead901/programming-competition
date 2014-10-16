package UvaOnlineJudge;
import java.util.Scanner;

/**
 * 299 - Train Swapping
 * 
 * @author kellend
 * 
 */
public class TrainSwapping {
  static Scanner in = new Scanner(System.in);

  public static void main(String[] args) {
    int numCases = in.nextInt();
    for (int i = 0; i < numCases; i++) {
      processCase();
    }
    in.close();
  }

  private static void processCase() {
    int length = in.nextInt();
    int[] cars = new int[length];
    for (int i = 0; i < length; i++) {
      cars[i] = in.nextInt();
    }
    int swaps = 0;
    boolean swapped = true;
    while (swapped) {
      swapped = false;
      for (int i = 0; i < cars.length - 1; i++) {
        if (cars[i] > cars[i+1]) {
          int temp = cars[i];
          cars[i] = cars[i+1];
          cars[i+1] = temp;
          swapped = true;
          swaps++;
        }
      }
    }
    System.out.println("Optimal train swapping takes " +  swaps + " swaps.");
  }
}
