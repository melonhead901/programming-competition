package adventofcode;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class SantaDeliveries {

    public static void main(String[] args) {
        Solver<Integer> solver = new Solver<Integer>(new Scanner(System.in)) {

            @Override
            public String getInput(Scanner inputScanner) {
                return inputScanner.nextLine();
            }

            @Override
            public Integer solveCase(String s) {
                final Set<Coord> coords = new HashSet<>();
                Coord currentPos = new Coord(0,0);
                Coord roboSantaPos = new Coord(0,0);
                int turnCount = 0;
                coords.add(currentPos);
                for (char c : s.toCharArray()) {
                    switch (c) {
                    case '^':
                        if (turnCount % 2 == 0) {
                            currentPos = currentPos.moveUp();
                        } else {
                            roboSantaPos = roboSantaPos.moveUp();
                        }
                        break;
                    case 'v':
                        if (turnCount % 2 == 0) {
                            currentPos = currentPos.moveDown();
                        } else {
                            roboSantaPos = roboSantaPos.moveDown();
                        }
                        break;
                    case '>':
                        if (turnCount % 2 == 0) {
                            currentPos = currentPos.moveRight();
                        } else {
                            roboSantaPos = roboSantaPos.moveRight();
                        }
                        break;
                    case '<':
                        if (turnCount % 2 == 0) {
                            currentPos = currentPos.moveLeft();
                        } else {
                            roboSantaPos = roboSantaPos.moveLeft();
                        }
                        break;
                    default:
                        throw new IllegalStateException();
                    }
                    if (turnCount % 2 == 0) {
                        coords.add(currentPos);
                    } else {
                        coords.add(roboSantaPos);
                    }
                    turnCount++;
                }
                return coords.size();
            }
        };

        while (solver.isReady()) {
            System.out.println(solver.processCase());
        }

    }

    private static class Coord {
        final int x, y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Coord moveLeft() {
            return new Coord(x - 1, y);
        }

        public Coord moveRight() {
            return new Coord(x + 1, y);
        }

        public Coord moveUp() {
            return new Coord(x, y + 1);
        }

        public Coord moveDown() {
            return new Coord(x, y - 1);
        }

        @Override
        public String toString() {
            return "Coord [x=" + x + ", y=" + y + "]";
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + x;
            result = prime * result + y;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (!(obj instanceof Coord)) {
                return false;
            }
            Coord other = (Coord) obj;
            return x == other.x && y == other.y;
        }
    }

}
