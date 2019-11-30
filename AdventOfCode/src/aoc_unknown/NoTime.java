package aoc_unknown;

import java.util.HashSet;
import java.util.Scanner;

/**
 * Bunnies.
 */
public class NoTime {
    private Direction direction;
    private CoordTracker coord;


    private NoTime() {
        direction = Direction.NORTH;
        coord = new CoordTracker(new Coord(new XPos(0), new YPos(0)), new HashSet<>());
    }

    public static void main(String[] args) {
        NoTime noTime = new NoTime();
        Scanner in = new Scanner(System.in);
        noTime.processInstructions(in.nextLine().split(", "));
        //noTime.printDistanceFromOrigin();
        noTime.printFirstRepeatedCoord();
    }

    private void printFirstRepeatedCoord() {
        System.out.println(coord.getFirstRepeatedCoord().get());
    }

    private void printDistanceFromOrigin() {
        System.out.println(coord.calculateDistanceFromOrigin());
    }

    private void processInstructions(String[] instructions) {
        for (String instruction : instructions) {
            direction = direction.turnInDir(instruction.charAt(0));
            coord.advance(direction, Integer.valueOf(instruction.substring(1)));
            System.out.println(coord + " " + coord
                .calculateDistanceFromOrigin());
        }
    }
}

enum Direction {
    NORTH,
    WEST,
    EAST,
    SOUTH;

    Direction turnLeft() {
        switch (this) {
            case NORTH:
                return WEST;
            case WEST:
                return SOUTH;
            case EAST:
                return NORTH;
            case SOUTH:
                return EAST;
            default:
                throw new IllegalArgumentException();
        }
    }

    Direction turnRight() {
        switch (this) {
            case NORTH:
                return EAST;
            case WEST:
                return NORTH;
            case EAST:
                return SOUTH;
            case SOUTH:
                return WEST;
            default:
                throw new IllegalArgumentException();
        }
    }


    public Direction turnInDir(char c) {
        switch (c) {
            case 'L':
                return this.turnLeft();
            case 'R':
                return this.turnRight();
            default:
                throw new IllegalStateException();
        }
    }
}

class XPos {
    public static final int MAX = 50;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }

        XPos xPos = (XPos) o;

        return xPosition == xPos.xPosition;
    }

    @Override
    public int hashCode() {
        return xPosition;
    }

    public XPos(int xPosition) {
        this.xPosition = xPosition;
    }

    final int xPosition;

    public XPos moveIn(Direction direction, int numSteps) {
        switch (direction) {
            case EAST:
                return new XPos(this.xPosition + numSteps);
            case WEST:
                return new XPos(this.xPosition - numSteps);
            case NORTH:
            case SOUTH:
                return this;
            default:
                throw new IllegalStateException();
        }
    }

    public XPos rotateCol(TwoFactor.MoveAmount moveAmount) {
        return new XPos((this.xPosition + moveAmount.moveAmount) % MAX);
    }
}

class YPos {
    static final int MAX = 6;
    final int yPosition;

    public YPos(int yPosition) {
        this.yPosition = yPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }

        YPos yPos = (YPos) o;

        return yPosition == yPos.yPosition;

    }

    @Override
    public int hashCode() {
        return yPosition;
    }

    public YPos moveIn(Direction direction, int numSteps) {
        switch (direction) {
            case NORTH:
                return new YPos(this.yPosition - numSteps);
            case SOUTH:
                return new YPos(this.yPosition + numSteps);
            case EAST:
            case WEST:
                return this;
            default:
                throw new IllegalStateException();
        }
    }

    public YPos rotateRow(TwoFactor.MoveAmount moveAmount) {
        return new YPos((yPosition + moveAmount.moveAmount) % MAX);
    }
}
