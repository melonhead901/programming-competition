package aoc21;

import java.util.Objects;

public class Point {
    final int r;
    final int c;

    @Override
    public String toString() {
        return "{" + r + "," + c + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return r == point.r && c == point.c;
    }

    @Override
    public int hashCode() {
        return Objects.hash(r, c);
    }

    public Point(int r, int c) {
        this.r = r;
        this.c = c;
    }
}
