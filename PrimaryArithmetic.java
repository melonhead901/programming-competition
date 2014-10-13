import java.util.Scanner;

/**
 * 10035 - Primary Arithmetic
 * 
 * @author kellend
 * 
 */
public class PrimaryArithmetic {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    long a = in.nextLong();
    long b = in.nextLong();
    while (a != 0 || b != 0) {
      processCase(a, b);
      a = in.nextLong();
      b = in.nextLong();
    }
    in.close();
  }

  private static void processCase(long a, long b) {
    int numCarries = 0;
    int carry = 0;
    while (a > 0 || b > 0) {
      long sum = a % 10 + b % 10 + carry;
      if (sum > 9) {
        carry = 1;
        numCarries++;
      } else {
        carry = 0;
      }
      a /= 10;
      b /= 10;
    }
    if (numCarries == 0) {
      System.out.println("No carry operation.");
    } else if (numCarries == 1) {
      System.out.println("1 carry operation.");
    } else {
      System.out.println(numCarries + " carry operations.");
    }
  }
}
