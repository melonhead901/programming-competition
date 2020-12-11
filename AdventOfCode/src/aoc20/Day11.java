package aoc20;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day11 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<String> lines = new ArrayList<>();
        while (in.hasNextLine()) {
            String line = in.nextLine();
            if (line.isEmpty()) {
                break;
            }
            lines.add(line);
        }
        Board b = new Board(lines);
        int neighbors = b.countOccupiedCells();
        while (true) {
            b = b.executeTurn();
            b.print();
            System.out.println(neighbors);
            if (neighbors == b.countOccupiedCells()) {
                break;
            }
            neighbors = b.countOccupiedCells();
        }
        System.out.println(neighbors);
    }

    static class Board {
        final Cell[][] cells;

        public Board(Cell[][] newCells) {
            this.cells = newCells;
        }

        private Board executeTurn() {
            Cell[][] newCells = new Cell[cells.length][];
            for (int r = 0; r < cells.length; r++) {
                newCells[r] = new Cell[cells[r].length];
                for (int c = 0; c < cells[r].length; c++) {
                    newCells[r][c] = cells[r][c].turn(numNeighbors(r, c));
                }
            }
            return new Board(newCells);
        }

        private int numNeighbors(int r, int c) {
            int neighbors = 0;
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if ((i == 0) && (j == 0)) {
                        continue;
                    }
                    if (existsAndOccupuied(r+i, c+j)) {
                        neighbors++;
                    }
                }
            }
            return neighbors;
        }

        private boolean existsAndOccupuied(int r, int c) {
            return (r >= 0)
                    && (r < cells.length)
                    && (c >= 0)
                    && (c < cells[r].length)
                    && (cells[r][c].state == Cell.State.OCCUPIED);
        }

        public Board(List<String> lines) {
            cells = new Cell[lines.size()][];
            for (int r = 0; r < cells.length; r++) {
                String line = lines.get(r);
                cells[r] = new Cell[line.length()];
                for (int c = 0; c < line.length(); c++) {
                    cells[r][c] = new Cell(line.charAt(c));
                }
            }
        }

        public void print() {
            for (Cell[] row : this.cells) {
                System.out.println(Arrays.toString(row));
            }
        }

        public int countOccupiedCells() {
            int count = 0;
            for (Cell[] row : cells) {
                for (int j = 0; j < row.length; j++) {
                    if (row[j].state == Cell.State.OCCUPIED) {
                        count++;
                    }
                }
            }
            return count;
        }
    }

    static class Cell {
        final State state;

        Cell(State state) {
            this.state = state;
        }

        public Cell(char charAt) {
            switch (charAt) {
                case 'L':
                    this.state = State.EMPTY;
                    break;
                case '#':
                    this.state = State.OCCUPIED;
                    break;
                case '.':
                    this.state = State.FLOOR;
                    break;
                default:
                    throw new IllegalStateException("char: " + charAt);
            }
        }

        public Cell turn(int numNeighbors) {
            if ((this.state == State.EMPTY) && (numNeighbors == 0)) {
                return new Cell(State.OCCUPIED);
            } else if ((this.state == State.OCCUPIED) && (numNeighbors >= 4)) {
                return new Cell(State.EMPTY);
            }
            return this;
        }

        enum State {
            EMPTY,
            OCCUPIED,
            FLOOR
        }

        @Override
        public String toString() {
            if (state == State.EMPTY) {
                return "L";
            }
            else if (state == State.OCCUPIED) {
                return "#";
            }
            else if (state == State.FLOOR) {
                return ".";
            }
            throw new IllegalStateException();
        }
    }
}
