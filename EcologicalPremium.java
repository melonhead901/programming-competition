import java.util.Scanner;

/**
 * 10300 - Ecological Premium
 * @author kellend
 *
 */
public class EcologicalPremium {
  static Scanner in = new Scanner(System.in);

  public static void main(String[] args) {
    int numCases = in.nextInt();
    for (int i = 0; i < numCases; i++) {
      processCase();
    }
  }

  private static void processCase() {
    int numFarmers = in.nextInt();
    int total = 0;
    for (int i = 0; i < numFarmers; i++) {
      int a = in.nextInt();
      in.nextInt();
      total += a * in.nextInt();
    }
    System.out.println(total);
  }
}