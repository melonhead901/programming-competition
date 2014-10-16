package UvaOnlineJudge;
import java.util.Scanner;

/**
 * 102 - Ecological Bin Packing
 * 
 * @author kellend
 * 
 */
class EcologicalBinPacking {
  static Scanner in = new Scanner(System.in);

  public static void main(String[] args) {
    while (in.hasNext()) {
      processCase();
    }
  }

  static int b1, g1, c1, b2, g2, c2, b3, g3, c3;

  private static void processCase() {
    Scanner line = new Scanner(in.nextLine());
    b1 = line.nextInt();
    g1 = line.nextInt();
    c1 = line.nextInt();
    b2 = line.nextInt();
    g2 = line.nextInt();
    c2 = line.nextInt();
    b3 = line.nextInt();
    g3 = line.nextInt();
    c3 = line.nextInt();
    line.close();
    int moves = calcMoves(new int[] { b2, b3, c1, c3, g1, g2 });
    String name = "BCG";
    int bgc = calcMoves(new int[] { b2, b3, g1, g3, c1, c2 });
    if (bgc < moves) {
      name = "BGC";
      moves = bgc;
    }
    int cbg = calcMoves(
        new int[] { c2, c3, b1, b3, g1, g2 });
    if (cbg < moves) {
      name = "CBG";
      moves = cbg;
    }
    int cgb = calcMoves(
        new int[] { c2, c3, g1, g3, b1, b2 });
    if (cgb < moves) {
      name = "CGB";
      moves = cgb;
    }
    int gbc = calcMoves(
        new int[] { g2, g3, b1, b3, c1, c2 });
    if (gbc < moves) {
      name = "GBC";
      moves = gbc;
    }
    int gcb = calcMoves(
        new int[] { g2, g3, c1, c3, b1, b2 });
    if (gcb < moves) {
      name = "GCB";
      moves = gcb;
    }
    
    System.out.println(name + " " + moves);
  }

  private static int calcMoves(int[] moves) {
    int totalMoves = 0;
    for (int m : moves) {
      totalMoves += m;
    }
    return totalMoves;
  }
}
