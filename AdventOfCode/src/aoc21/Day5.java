package aoc21;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day5 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<Line> lines = new ArrayList<>();
        while (in.hasNextLine()) {
            lines.add(Line.createLine(in.nextLine()));
        }

        int width = 1000;
        int[][] board = new int[width][];
        for (int i = 0; i < width; i++) {
            board[i] = new int[width];
        }

        for (Line line : lines) {
                addLineToBoard(line, board);
        }

        int count = 0;
        for (int r = 0; r < width; r++) {
            for (int c = 0; c < width; c++) {
                if (board[r][c] >= 2) {
                    count++;
                }
            }
        }

        System.out.println(count);
    }

    private static void addLineToBoard(Line line, int[][] board) {
        if (line.isHorizontal() || line.isVertical()) {
            int startR = Math.min(line.startR, line.endR);
            int endR = Math.max(line.startR, line.endR);
            int startC = Math.min(line.startC, line.endC);
            int endC = Math.max(line.startC, line.endC);
            for (int r = startR; r <= endR; r++) {
                for (int c = startC; c <= endC; c++) {
                    board[r][c]++;
                }
            }
        } else {
            List<Line.Point> points = line.pointsOnLine();
            for (Line.Point p : points) {
                board[p.r][p.c]++;
            }
        }
    }

    private static class Line {
        int startR;
        int startC;
        int endR;
        int endC;

        public Line(int startX, int startC, int endR, int endC) {
            this.startR = startX;
            this.startC = startC;
            this.endR = endR;
            this.endC = endC;
        }

        public Line(String s, String s1, String s2, String s3) {
            this(Integer.parseInt(s), Integer.parseInt(s1), Integer.parseInt(s2), Integer.parseInt(s3));
        }

        public List<Point> pointsOnLine() {
            int cChange = endC - startC;
            int rIncrement = (startR < endR) ? 1 : -1;
            int cIncrement = (startC < endC) ? 1 : -1;
            int row = startR;
            List<Point> points = new ArrayList<>();
            for (int i = 0; i <= Math.abs(cChange); i++) {
                points.add(new Point(row, (cIncrement * i) + startC));
                row += rIncrement;
            }
            // System.out.printf("%s,%s -> %s,%s: %s\n", startR, startC, endR, endC, points);
            return points;
        }


        public static Line createLine(String nextLine) {
            String[] points = nextLine.split(" -> ");
            return new Line(
                    points[0].split(",")[0],
                    points[0].split(",")[1],
                    points[1].split(",")[0],
                    points[1].split(",")[1]
            );
        }

        public boolean isVertical() {
            return this.startC == this.endC;
        }

        public boolean isHorizontal() {
            return this.startR == this.endR;
        }

        private static class Point {
            int r;
            int c;

            @Override
            public String toString() {
                return "(" + r + "," + c + ")";
            }

            public Point(int r, int c) {
                this.r = r;
                this.c = c;
            }

        }
    }
}
