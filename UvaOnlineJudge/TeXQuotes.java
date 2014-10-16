package UvaOnlineJudge;
import java.util.Scanner;

/**
 * TeX quotes -- 272
 * 
 * @author kellend
 * 
 */
public class TeXQuotes {
  static boolean inQuotes = false;

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String line = in.nextLine();
      for (char c : line.toCharArray()) {
        if (c == '\"') {
          System.out.print(inQuotes ? "\'\'" : "``");
          inQuotes = !inQuotes;
        } else {
          System.out.print(c);
        }
      }
      System.out.println();
    }
    in.close();
  }
}
