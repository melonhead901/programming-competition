package UvaOnlineJudge;
import java.util.Scanner;


/**
 * 10783 - Odd Sum
 * @author kellend
 *
 */
public class OddSum {
  static Scanner in = new Scanner(System.in);
  public static void main(String[] args) {
    int numCases = in.nextInt();
    for (int i = 0; i < numCases; i++) {
      processCase(i+1);
    }

  }
  private static void processCase(int caseNum) {
    int a = in.nextInt();
    int b = in.nextInt();
    int total = 0;
    for (int i = a; i <= b; i++) {
      if (i % 2 == 1) {
        total += i;
      }
    }
    System.out.println(String.format("Case %s: %s", caseNum, total));
  }
}
