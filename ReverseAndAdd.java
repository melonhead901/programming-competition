import java.util.Scanner;

/**
 * 10018 - Reverse and Add
 * 
 * @author kellend
 * 
 */
public class ReverseAndAdd {
  static Scanner in = new Scanner(System.in);

  public static void main(String[] args) {
    int numCases = in.nextInt();
    for (int i = 0; i < numCases; i++) {
      processCase();
    }

  }

  private static void processCase() {
    long n = in.nextInt();
    if (n < 0) {
      System.out.println("1 0");
      return;
    }
    String s = String.valueOf(n);
    String reverse = reverse(s);
    long add = Long.parseLong(reverse) + n;
    String added = String.valueOf(add);
    int iterations = 1;
    while (!isPalindrome(added)) {
      s = String.valueOf(add);
      reverse = reverse(s);
      add = Long.parseLong(reverse) + add;
      added = String.valueOf(add);
      iterations++;
    }
    System.out.println(String.format("%s %s", iterations, added));
  }

  private static boolean isPalindrome(String added) {
    char[] chars = added.toCharArray();
    for (int i = 0; i < chars.length / 2; i++) {
      if (!(chars[i] == chars[chars.length - 1 - i])) {
        return false;
      }
    }
    return true;
  }

  private static String reverse(String s) {
    StringBuilder builder = new StringBuilder(s);
    builder.reverse();
    return builder.toString();
  }
}
