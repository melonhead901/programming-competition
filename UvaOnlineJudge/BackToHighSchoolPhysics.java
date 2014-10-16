package UvaOnlineJudge;
import java.util.Scanner;


/**
 * Problem 10071 -- Back to High School Physics
 * @author kellend
 *
 */
class BackToHighSchoolPhysics {
  static Scanner in = new Scanner(System.in);
  public static void main(String[] args) {
    while (in.hasNext()) {
      System.out.println(in.nextInt() * in.nextInt() * 2);
    }
  }
}
