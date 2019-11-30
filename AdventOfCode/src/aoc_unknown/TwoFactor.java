package aoc_unknown;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by kdonohue on 12/7/16.
 */
public class TwoFactor {
    final Grid grid;

    public TwoFactor(Grid grid) {
        this.grid = grid;
    }

    public static void main(String[] args) {
        TwoFactor tf = new TwoFactor(Grid.emptyGrid());
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            tf.processInstruction(in.nextLine());
            System.out.println(tf.calculateActivePixels());
            tf.printGrid();
        }
    }

    private void printGrid() {
        grid.printGrid();
    }

    private int calculateActivePixels() {
        return grid.onCells.size();
    }

    private void processInstruction(String s) {
        if (s.startsWith("rect")) {
            s = s.split(" ")[1];
            int xSize = Integer.valueOf(s.split("x")[0]);
            int ySize = Integer.valueOf(s.split("x")[1]);
            grid.rect(new XPos(xSize), new YPos(ySize));
        } else if (s.startsWith("rotate column x=")) {
            s = s.split("rotate column x=")[1];
            int col = Integer.valueOf(s.split(" by ")[0]);
            int moveAmount = Integer.valueOf(s.split(" by ")[1]);
            grid.rotateCol(new XPos(col), new MoveAmount(moveAmount));
        } else if (s.startsWith("rotate row y=")) {
            s = s.split("rotate row y=")[1];
            int row = Integer.valueOf(s.split(" by ")[0]);
            int moveAmount = Integer.valueOf(s.split(" by ")[1]);
            grid.rotateRow(new YPos(row), new MoveAmount(moveAmount));
        } else {
            throw new IllegalArgumentException(s);
        }
    }

    static class MoveAmount {
        public MoveAmount(int moveAmount) {
            this.moveAmount = moveAmount;
        }

        final int moveAmount;
    }

    static class Grid {
        private final Set<Coord> onCells;

        public Grid(Set<Coord> onCells) {
            this.onCells = onCells;

        }

        public void rect(XPos xSize, YPos ySize) {
            for (int i = 0; i < xSize.xPosition; i++) {
                for (int j = 0; j < ySize.yPosition; j++) {
                    onCells.add(new Coord(new XPos(i), new YPos(j)));
                }
            }
        }

        public void rotateCol(XPos colToRotate, MoveAmount moveAmount) {
            List<Coord> col = onCells.stream().filter(cell -> cell.isInCol(colToRotate)).collect(Collectors
                .toList());
            onCells.removeAll(col);
            onCells.addAll(col.stream().map(oldCoord -> oldCoord.rotateCol(moveAmount)).collect(Collectors.toList()));
        }

        public void rotateRow(YPos rowToRotate, MoveAmount moveAmount) {
            List<Coord> row = onCells.stream().filter(cell -> cell.isInRow(rowToRotate)).collect(Collectors.toList
                ());
            onCells.removeAll(row);
            onCells.addAll(row.stream().map(oldCoord -> oldCoord.rotateRow(moveAmount)).collect(Collectors.toList()));
        }

        public static Grid emptyGrid() {
            return new Grid(new HashSet<>());
        }

        public void printGrid() {
            boolean[][] output = new boolean[YPos.MAX][XPos.MAX];
            for (Coord c : onCells) {
                c.populateGrid(output);
            }
            for (int r = 0; r < output.length; r++) {
                for (int c = 0; c < output[0].length; c++) {
                    System.out.print(output[r][c] ? '#' : '.');
                }
                System.out.println();
            }
        }
    }
}
