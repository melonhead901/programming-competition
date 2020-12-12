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
            return "{" + degress + '}';
        }

        public Direction turnRight(int degress) {
            return new Direction(this.degress - degress);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Status status = new Status(0, 0, new WaypointPosition(10, 1, 0));
        while (in.hasNext()) {
            String line = in.nextLine();
            if (line.isBlank()) {
                break;
            }
            char c = line.charAt(0);
            int num = Integer.parseInt(line.substring(1));
            status = status.processMoveWaypoint(c, num);
            System.out.println(status);
            System.out.println(status.manhattanDistance());
        }
        System.out.println(status.manhattanDistance());
    }

    static class WaypointPosition {
        final int x, y;
        int deg;

        @Override
        public String toString() {
            return String.format("(%s,%s):%s", x, y, deg);
        }

        WaypointPosition(int x, int y, int deg) {
            this.x = x;
            this.y = y;
            this.deg = deg;
        }

        public WaypointPosition moveY(int dy) {
            return new WaypointPosition(x, y + dy, deg);
        }

        public WaypointPosition moveX(int dx) {
            return new WaypointPosition(x + dx, y, deg);
        }

        public WaypointPosition rotate(int num) {
            if (num < 0) {
                num += 360;
            }
            switch (num % 360) {
                case 90:
                    return new WaypointPosition(-y, x, 0);
                case 180:
                    return new WaypointPosition(-x, -y, 0);
                case 270:
                    return new WaypointPosition(y, -x, 0);
            }
            throw new IllegalStateException((num % 360) + "");
        }

        public int dx(int num) {
            return (int) (num * x);
        }

        public int dy(int num) {
            return (int) (num * y);
        }
    }

    static class Status {
        final int x, y;
        private final WaypointPosition waypointPosition;
        Direction direction;

        /*
        Status(int x, int y, Direction direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
        *        */

        Status(int x, int y, WaypointPosition waypointPosition) {
            this.x = x;
            this.y = y;
            this.waypointPosition = waypointPosition;
        }

        @Override
        public String toString() {
            return "Status{" + "x=" + x + ", y=" + y + ", waypoint=" + waypointPosition + '}';
        }

        /*
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
         */
        public Status processMoveWaypoint(char c, int num) {
            switch (c) {
                case 'N':
                    return new Status(x, y, waypointPosition.moveY(num));
                case 'E':
                    return new Status(x, y, waypointPosition.moveX(num));
                case 'S':
                    return new Status(x, y, waypointPosition.moveY(-num));
                case 'W':
                    return new Status(x, y, waypointPosition.moveX(-num));
                case 'L':
                    return new Status(x, y, waypointPosition.rotate(num));
                case 'R':
                    return new Status(x, y, waypointPosition.rotate(-num));
                case 'F':
                    return new Status(x + waypointPosition.dx(num), y + waypointPosition.dy(num), waypointPosition);
            }
            throw new IllegalStateException();
        }

        public int manhattanDistance() {
            return Math.abs(x) + Math.abs(y);
        }
    }
}
