package aoc22;

import java.util.HashSet;
import java.util.Scanner;

public class Day9 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Coord head = new Coord(0,0);
        Coord tail = new Coord(0,0);
        HashSet<Coord> tailPositions = new HashSet<>();
        tailPositions.add(tail);
        while (in.hasNext()) {
            String direction = in.next();
            int magnitude = in.nextInt();
            for (int i = 0; i < magnitude; i++) {
                Coord newHead;
                switch (direction) {
                    case "U" -> newHead = head.up();
                    case "D" -> newHead = head.down();
                    case "L" -> newHead = head.left();
                    case "R" -> newHead = head.right();
                    default -> throw new IllegalStateException();
                }
                tail = updateTailPosition(newHead, head, tail);
                head = newHead;
                tailPositions.add(tail);
                System.out.printf("Head at %s, tail at %s\n", head, tail);
            }
        }
        System.out.println(tailPositions.size());
    }

    private static Coord updateTailPosition(Coord newHead, Coord head, Coord tail) {
        int xDiff = Math.abs(newHead.x - tail.x);
        int yDiff = Math.abs(newHead.y - tail.y);
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
