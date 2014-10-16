package UvaOnlineJudge;
import java.math.BigInteger;
import java.util.Scanner;


/**
 * 113 - Power of Cryptography
 * @author kellend
 *
 */
public class PowerOfCryptography {
  static Scanner in = new Scanner(System.in);
  public static void main(String[] args) {
    while (in.hasNext()) {
      BigInteger n = new BigInteger(in.nextLine());
      BigInteger p = new BigInteger(in.nextLine());
      double val = Math.pow(p.doubleValue(), 1.0/n.doubleValue());
      System.out.println(Math.round(val));
    }
  }
}
