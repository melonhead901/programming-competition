package aoc21;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Day4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        Scanner numScanner = new Scanner(line).useDelimiter(",");
        List<Integer> numbers = new ArrayList<>();
        while (numScanner.hasNextInt()) {
            numbers.add(numScanner.nextInt());
        }
        List<Board> boards = new ArrayList<>();
        while (in.hasNext()) {
            boards.add(Board.loadBoard(in));
        }

        for (int num : numbers) {
            boards.forEach(board -> board.cover(num));
            Optional<Board> coveredBoard = boards.stream().filter(Board::isBoardCovered).findAny();
            if (coveredBoard.isPresent()) {
                System.out.println(coveredBoard.get().sumUncovered() * num);
                break;
            }
        }

    }

    private static class Board {
        private List<Cell> cells;
        int[] valsPerRow;
        int[] valsPerCol;

        private Board() {
            this.cells = new ArrayList<>(25);
            this.valsPerCol = new int[5];
            this.valsPerRow = new int[5];
        }

        public static Board loadBoard(Scanner in) {
            Board b = new Board();
            for (int c = 0; c < 5; c++) {
                for (int r = 0; r < 5; r++) {
                    b.cells.add(new Cell(r, c, in.nextInt()));
                }
            }
            return b;
        }

        public boolean isBoardCovered() {
            for (int i : valsPerRow) {
                if (i == 5) {
                    return true;
                }
            }
            for (int i : valsPerCol) {
                if (i == 5) {
                    return true;
                }
            }
            return false;
        }

        public void cover(int num) {
            Optional<Cell> cell = cells.stream().filter(c -> c.val == num).findFirst();
            if (cell.isPresent()) {
                Cell c = cell.get();
                c.covered = true;
                this.valsPerRow[c.r]++;
                this.valsPerCol[c.c]++;
            }
        }

        public int sumUncovered() {
            return cells.stream().filter(c -> !c.covered).map(c -> c.val).reduce(0, Integer::sum);
        }

        private static class Cell {
            int r;
            int c;
            int val;
            public boolean covered;

            public Cell(int r, int c, int val) {
                this.r = r;
                this.c = c;
                this.val = val;
            }
        }
    }
}
