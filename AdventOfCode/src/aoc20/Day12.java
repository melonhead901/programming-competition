package aoc20;

import java.util.Scanner;

public class Day12 {

    static class Direction {
        int degress;

        public Direction(int def) {
            this.degress = def;
        }

        public int dx() {
            return (int) Math.cos(Math.toRadians(degress));
        }

        public int dy() {
            return (int) Math.sin(Math.toRadians(degress));
        }

        public Direction turnLeft(int degress) {
            return new Direction(this.degress + degress);
        }

        @Override
        public String toString() {
            return "{" + degress +
                    '}';
        }

        public Direction turnRight(int degress) {
            return new Direction(this.degress - degress);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int x = 0;
        int y = 0;
        Direction direction = new Direction(0);
        Status status = new Status(x, y, direction);
        while (in.hasNext()) {
            String line = in.nextLine();
            if (line.isBlank()) {
                break;
            }
            char c = line.charAt(0);
            int num = Integer.parseInt(line.substring(1));
            status = status.processMove(c, num);
            System.out.println(status);
            System.out.println(status.manhattanDistance());
        }
        System.out.println(status.manhattanDistance());
    }

    static class Status {
        final int x, y;
        final Direction direction;

        Status(int x, int y, Direction direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }

        @Override
        public String toString() {
            return "Status{" + "x=" + x + ", y=" + y + ", direction=" + direction + '}';
        }

        public Status processMove(char c, int num) {
            switch (c) {
                case 'N':
                    return new Status(x, y + num, direction);
                case 'E':
                    return new Status(x + num, y, direction);
                case 'S':
                    return new Status(x, y - num, direction);
                case 'W':
                    return new Status(x - num, y, direction);
                case 'L':
                    return new Status(x, y, direction.turnLeft(num));
                case 'R':
                    return new Status(x, y, direction.turnRight(num));
                case 'F':
                    return new Status(x + (direction.dx() * num), y + (direction.dy() * num), direction);
            }
            throw new IllegalStateException();
        }

        public int manhattanDistance() {
            return Math.abs(x) + Math.abs(y);
        }
    }
}
