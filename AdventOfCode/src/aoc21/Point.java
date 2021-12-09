package aoc21;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Point {
    final int r;
    final int c;

    public Point(int r, int c) {
        this.r = r;
        this.c = c;
    }

    List<Point> getPointNeighbors() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(r - 1, c));
        points.add(new Point(r + 1, c));
        points.add(new Point(r, c - 1));
        points.add(new Point(r, c + 1));
        return points;
    }

    static List<Point> getNeighbors(int r, int c) {
        List<Point> points = new ArrayList<>();
        points.add(new Point(r - 1, c));
        points.add(new Point(r + 1, c));
        points.add(new Point(r, c - 1));
        points.add(new Point(r, c + 1));
        return points;
    }

    boolean isInGrid(int[][] grid) {
        return (c >= 0) && (r >= 0) && (r < grid.length) && (c < grid[r].length);
    }

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
}
