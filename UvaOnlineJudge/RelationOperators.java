package UvaOnlineJudge;
import java.util.Scanner;


/**
 * 11172 - Relation Operators
 * @author kellend
 *
 */
 class RelationOperators {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int numCases = in.nextInt();
    for (int i = 0; i <  numCases; i++) {
      int first = in.nextInt();
      int second = in.nextInt();
      if (first < second) {
        System.out.println("<");
      } else if (first > second) {
        System.out.println(">");
      } else {
        System.out.println("=");
      }
    }
    in.close();

  }
}
