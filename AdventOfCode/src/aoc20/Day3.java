package aoc20;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Board b = new Board(in);
        int r = 0;
        int c = 0;
        int dr = 1;
        int dc = 3;
        int numTreesHit = 0;
        while (!b.pastLastRow(r)) {
            if (b.atTree(r, c)) {
                numTreesHit++;
            }
            r += dr;
            c += dc;
            //System.err.println(String.format("%s,%s", r, c));
        }

        System.out.println(numTreesHit);

    }
}

class Board {

    private final int rows;
    private final int cols;
    private final boolean[][] trees;

    public Board(Scanner scanner) {
        List<String> lines = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (!line.isEmpty()) {
                lines.add(line);
            }
            else {
                break;
            }
        }
        rows = lines.size();
        cols = lines.get(0).length();

        trees = new boolean[rows][];
        for (int r = 0; r < rows; r++) {
            String row = lines.get(r);
            trees[r] = new boolean[cols];
            for (int c = 0; c < cols; c++) {
                trees[r][c] = row.charAt(c) == '#';
            }
        }
    }

    public boolean pastLastRow(int r) {
        return r >= rows;
    }

    public boolean atTree(int r, int c) {
        int c_to_check = c % cols;
        return trees[r][c_to_check];
    }
}
