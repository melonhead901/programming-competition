package aoc20;

import java.util.HashMap;
import java.util.HashSet;
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
                    board.activateCell(0, lineNum, i, 0);
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
                int numNeighbors = board.countActiveNeighbors(c);
                if ((isActive && (numNeighbors == 2)) || (numNeighbors == 3)) {
                    newBoard.activateCell(c);
                }
            }
            board = newBoard;
        }
        System.out.print(board.numActiveCells());
    }

    static class Board {
        Map<Integer, Map<Integer, Map<Integer, Set<Integer>>>> cells;

        Board() {
            this.cells = new HashMap<>();
        }

        public void activateCell(Coord c) {
            activateCell(c.x, c.y, c.z, c.zz);
        }

        public void activateCell(int x, int y, int z, int zz) {
            if (!cells.containsKey(x)) {
                cells.put(x, new HashMap<>());
            }
            Map<Integer, Map<Integer, Set<Integer>>> row = cells.get(x);
            if (!row.containsKey(y)) {
                row.put(y, new HashMap<>());
            }
            Map<Integer, Set<Integer>> col = row.get(y);
            if (!col.containsKey(z)) {
                col.put(z, new HashSet<>());
            }
            col.get(z).add(zz);
        }

        public boolean isCellActive(Coord c) {
            return isCellActive(c.x, c.y, c.z, c.zz);
        }

        public boolean isCellActive(int x, int y, int z, int zz) {
            return cells.containsKey(x)
                    && cells.get(x).containsKey(y)
                    && cells.get(x).get(y).containsKey(z)
                    && cells.get(x).get(y).get(z).contains(zz);
        }

        public Set<Coord> neighbors(int x, int y, int z, int zz) {
            Set<Coord> neighbors = new HashSet<>();
            for (int i = x - 1; i <= (x + 1); i++) {
                for (int j = y - 1; j <= (y + 1); j++) {
                    for (int k = z - 1; k <= (z + 1); k++) {
                        for (int l = zz - 1; l <= (zz + 1); l++) {
                            if (!((i == x) && (j == y) && (k == z) && (l == zz))) {
                                neighbors.add(new Coord(i, j, k, l));
                            }
                        }
                    }
                }
            }
            if (neighbors.size() != 80) {
                throw new IllegalStateException();
            }
            return neighbors;
        }

        public int countActiveNeighbors(Coord c) {
            return countActiveNeighbors(c.x, c.y, c.z, c.zz);
        }

        public int countActiveNeighbors(int x, int y, int z, int zz) {
            return (int)
                    neighbors(x, y, z, zz).stream().filter(this::isCellActive).count();
        }

        public Set<Coord> getAllPossibleNeighborsAndCells() {
            Set<Coord> coords = new HashSet<>();
            for (int row : cells.keySet()) {
                for (int col : cells.get(row).keySet()) {
                    for (int z : cells.get(row).get(col).keySet()) {
                        for (int zz : cells.get(row).get(col).get(z)) {
                            coords.addAll(neighbors(row, col, z, zz));
                        }
                    }
                }
            }
            return coords;
        }

        public long numActiveCells() {
            long result = 0;
            for (int row : cells.keySet()) {
                for (int col : cells.get(row).keySet()) {
                    for (int z : cells.get(row).get(col).keySet()) {
                        final int size = cells.get(row).get(col).get(z).size();
                        //System.out.printf(
                        //        "(r, c, zs) %s %s %s: %s%n",
                        //        row, col, cells.get(row).get(col), size);
                        result += size;
                    }
                }
            }
            return result;
        }
    }

    static class Coord {
        final int x, y, z, zz;

        public Coord(int x, int y, int z, int zz) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.zz = zz;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Coord coord = (Coord) o;

            if (x != coord.x) return false;
            if (y != coord.y) return false;
            if (z != coord.z) return false;
            return zz == coord.zz;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            result = 31 * result + z;
            result = 31 * result + zz;
            return result;
        }
    }
}
