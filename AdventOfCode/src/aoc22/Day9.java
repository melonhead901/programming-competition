package aoc22;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Scanner;

public class Day9 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        HashSet<Coord> tailPositions = new HashSet<>();
        tailPositions.add(new Coord(0,0));
        Coord[] knots = new Coord[10];
        for (int i = 0; i < knots.length; i++) {
            knots[i] = new Coord(0, 0);
        }
        while (in.hasNext()) {
            String direction = in.next();
            int magnitude = in.nextInt();
            for (int i = 0; i < magnitude; i++) {
                Coord head = knots[0];
                Coord newHead;
                switch (direction) {
                    case "U" -> newHead = head.up();
                    case "D" -> newHead = head.down();
                    case "L" -> newHead = head.left();
                    case "R" -> newHead = head.right();
                    default -> throw new IllegalStateException();
                }
                Coord[] newKnots = new Coord[10];
                newKnots[0] = newHead;

                for (int t = 1; t < 10; t++) {
                    newKnots[t] = updateTailPosition(newKnots[t-1], knots[t-1], knots[t]);
                }
                knots = newKnots;
                printBoard(knots);

                tailPositions.add(knots[9]);
            }
        }
        System.out.println(tailPositions.size());
    }

    private static void printBoard(Coord[] knots) {
        int SIZE = 10;
        int[][] board = new int[SIZE][];
        for (int i = 0; i < SIZE; i++) {
            int[] row = new int[SIZE];
            for (int j = 0; j < SIZE; j++) {
                row[j] = -1;
            }
            board[i] = row;
        }
        for (int c = 0; c < knots.length; c++) {
            int row = SIZE/2-knots[c].y-1;
            int col = knots[c].x + SIZE/2;
            if (board[row][col] == -1) {
                board[row][col] = c;
            }
        }
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                int val = board[i][j];
                if (val == -1) {
                    if (i == SIZE/2 && j == SIZE/2) {
                        System.out.print("s");
                    } else {
                        System.out.print(".");
                    }
                } else {
                    System.out.print(val);
                }
            }
            System.out.println();
        }
        System.out.println("--------------------------------");

    }

    private static void oldPrintBoard(Coord[] knots) {
        for (int i = 0; i < 10; i++) {
            System.out.printf("%s: %s ", i, knots[i]);
        }
        System.out.println();
    }

    private static Coord updateTailPosition(@Nonnull Coord newHead, Coord head, Coord tail) {
        int xDiff = Math.abs(newHead.x - tail.x);
        int yDiff = Math.abs(newHead.y - tail.y);
        if (xDiff + yDiff == 3) {
            int newX, newY;
            if (xDiff == 2) {
                newX = (int) (0.5*(newHead.x+tail.x));
                newY = newHead.y;
            } else {
                newX = newHead.x;
                newY = (int) (0.5*(newHead.y+tail.y));
            }
            return new Coord(newX, newY);
        }
        if (xDiff == 2 || yDiff == 2) {
            return head;
        }
        return tail;

    }

    static class Coord {
        final int x;
        final int y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Coord right() {
            return new Coord(this.x + 1, y);
        }

        public Coord left() {
            return new Coord(this.x - 1, y);
        }
        public Coord up() {
            return new Coord(this.x , y+ 1);
        }
        public Coord down() {
            return new Coord(this.x , y-1);
        }

        @Override
        public String toString() {
            return "{" +
                     + x +
                    "," + y +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Coord coord = (Coord) o;

            if (x != coord.x) return false;
            return y == coord.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }
}
