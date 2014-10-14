import java.util.Scanner;

/**
 * 10189 - Minesweeper
 * @author kellend
 *
 */
public class Minesweeper {
  static Scanner in = new Scanner(System.in);

  static int rows, cols;

  public static void main(String[] args) {
    rows = in.nextInt();
    cols = in.nextInt();
    int i = 1;
    while (rows != 0 || cols != 0) {
      in.nextLine();
      processCase(i);
      rows = in.nextInt();
      cols = in.nextInt();
      if (rows != 0 || cols != 0) {
        System.out.println();
      }
      i++;
    }

  }

  static int[][] mines;
  final static int MINE = 9999;

  private static void processCase(int caseNum) {
    System.out.println("Field #" + caseNum + ":");
    mines = new int[rows][cols];
    for (int i = 0; i < rows; i++) {
      char[] line = in.nextLine().toCharArray();
      for (int j = 0; j < cols; j++) {
        if (line[j] == '*') {
          mines[i][j] = MINE;
        }
      }
    }

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (mines[i][j] != MINE) {
          System.out.print(calc(i, j));
        } else {
          System.out.print('*');
        }
      }
      System.out.println();
    }
  }

  private static int calc(int i, int j) {
    int total = 0;
    total += addMaybe(i - 1, j - 1);
    total += addMaybe(i - 1, j);
    total += addMaybe(i - 1, j + 1);

    total += addMaybe(i, j - 1);
    total += addMaybe(i, j + 1);

    total += addMaybe(i + 1, j - 1);
    total += addMaybe(i + 1, j);
    total += addMaybe(i + 1, j + 1);
    return total;
  }

  private static int addMaybe(int i, int j) {
    if (i < 0 || i >= rows || j < 0 || j >= cols) {
      return 0;
    }
    return mines[i][j] == MINE ? 1 : 0;
  }

}
