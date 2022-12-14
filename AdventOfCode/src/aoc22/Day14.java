package aoc22;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Day14 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<StoneSegment> segments = new ArrayList<>();
        while(in.hasNext()) {
            String line = in.nextLine();
            String[] wallPoints = line.split(" -> ");
            Point prevPoint = null;
            for (String strPr : wallPoints) {
                String[] coords = strPr.split(",");
                Point p = new Point(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]));
                if (prevPoint != null) {
                    segments.add(new StoneSegment(prevPoint, p));
                }
                prevPoint = p;
            }
        }
        for (StoneSegment ss : segments) {
            System.out.print(ss.pointsOfSegment());
        }
    }

    static class StoneSegment {

        final Point start;
        final Point end;

        public StoneSegment(Point start, Point end) {
            this.start = start;
            this.end = end;
        }

        public List<Point> pointsOfSegment() {
            boolean horizontal = this.start.r == this.end.r;
            List<Point> result = new ArrayList<>();
            if (horizontal) {
                for (int c = Math.min(start.c, end.c); c <= Math.max(start.c, end.c); c++) {
                    result.add(new Point(start.r, c));
                }
            } else {
                for (int r = Math.min(start.r, end.r); r <= Math.max(start.r, end.r); r++) {
                    result.add(new Point(r, start.c));
                }
            }
            return result;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            StoneSegment that = (StoneSegment) o;

            if (!Objects.equals(start, that.start)) return false;
            return Objects.equals(end, that.end);
        }

        @Override
        public int hashCode() {
            int result = start != null ? start.hashCode() : 0;
            result = 31 * result + (end != null ? end.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "StoneSegment{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

    static class Point {
        final int r, c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return String.format("(%s,%s)", r, c);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Point point = (Point) o;

            if (r != point.r) return false;
            return c == point.c;
        }

        @Override
        public int hashCode() {
            int result = r;
            result = 31 * result + c;
            return result;
        }
    }
}
