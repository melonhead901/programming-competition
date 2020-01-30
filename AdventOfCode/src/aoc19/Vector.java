package aoc19;

public class Vector {
    final int dr;
    final int dc;

    public Vector(int dr, int dc) {
        this.dr = dr;
        this.dc = dc;
    }

    public Position continueAlong(Position currentPosition) {
        return new Position(currentPosition.r + dr, currentPosition.c + dc);
    }
}
