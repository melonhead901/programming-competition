package aoc20;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Day17 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Board board = new Board();
        int lineNum = 0;
        while (in.hasNextLine()) {
            String line = in.nextLine();
            if (line.isEmpty()) {
                break;
            }
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '#') {
                    board.activateCell(lineNum, i, 0);
                }
            }
            lineNum++;
        }
        for (int i = 0; i < 6; i++) {
            System.out.printf("%s: %s%n", i, board.numActiveCells());
            Set<Coord> allNeighbors = board.getAllPossibleNeighborsAndCells();
            Board newBoard = new Board();
            for (Coord c : allNeighbors) {
                boolean isActive = board.isCellActive(c);
                int numNeighbors = board.countActiveNeighbors(c.x, c.y, c.z);
                if ((isActive && (numNeighbors == 2)) || (numNeighbors == 3)) {
                    newBoard.activateCell(c.x, c.y, c.z);
                }
            }
            board = newBoard;
        }
        System.out.print(board.numActiveCells());
    }

    static class Board {
        Map<Integer, Map<Integer, Set<Integer>>> cells;

        Board() {
            this.cells = new HashMap<>();
        }

        public void activateCell(int x, int y, int z) {
            if (!cells.containsKey(x)) {
                cells.put(x, new HashMap<>());
            }
            Map<Integer, Set<Integer>> row = cells.get(x);
            if (!row.containsKey(y)) {
                row.put(y, new HashSet<>());
            }
            Set<Integer> col = row.get(y);
            col.add(z);
        }

        public void deActivateCell(int x, int y, int z) {
            if (!cells.containsKey(x)) {
                cells.put(x, new HashMap<>());
            }
            Map<Integer, Set<Integer>> row = cells.get(x);
            if (!row.containsKey(y)) {
                row.put(y, new HashSet<>());
            }
            Set<Integer> col = row.get(y);
            col.remove(z);
        }
        public boolean isCellActive(Coord c) {
            return isCellActive(c.x, c.y, c.z);
        }

        public boolean isCellActive(int x, int y, int z) {
            return cells.containsKey(x)
                    && cells.get(x).containsKey(y)
                    && cells.get(x).get(y).contains(z);
        }

        public List<Coord> neighbors(int x, int y, int z) {
            List<Coord> neighbors = new ArrayList<>();
            for (int i = x - 1; i <= (x + 1); i++) {
                for (int j = y - 1; j <= (y + 1); j++) {
                    for (int k = z - 1; k <= (z + 1); k++) {
                        if (!((i == x) && (j == y) && (k == z))) {
                            neighbors.add(new Coord(i, j, k));
                        }
                    }
                }
            }
            return neighbors;

        }

        public int countActiveNeighbors(int x, int y, int z) {
            return (int) neighbors(x, y, z).stream().filter(c -> isCellActive(c.x, c.y, c.z)).count();
        }

        public Set<Coord> getAllPossibleNeighborsAndCells() {
            Set<Coord> coords = new HashSet<>();
            for (int row : cells.keySet()) {
                for (int col : cells.get(row).keySet()) {
                    for (int z : cells.get(row).get(col)) {
                        coords.addAll(neighbors(row, col, z));
                    }
                }
            }
            return coords;

        }

        public long numActiveCells() {
            long result = 0;
            for (int row : cells.keySet()) {
                for (int col : cells.get(row).keySet()) {
                    final int size = cells.get(row).get(col).size();
                    System.out.printf("(r, c, zs) %s %s %s: %s%n", row, col, cells.get(row).get(col), size);
                    result += size;
                }
            }
            return result;
        }
    }

    static class Coord {
        final int x, y, z;

        @Override
        public String toString() {
            return String.format("(%s,%s,%s)", x, y, z);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Coord coord = (Coord) o;

            if (x != coord.x) return false;
            if (y != coord.y) return false;
            return z == coord.z;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            result = 31 * result + z;
            return result;
        }

        Coord(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
}
