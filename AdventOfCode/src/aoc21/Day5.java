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
            if (line.isVertical() || line.isHorizontal()) {
                addLineToBoard(line, board);
            }
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
        int startR = Math.min(line.startR, line.endR);
        int endR = Math.max(line.startR, line.endR);
        int startC = Math.min(line.startC, line.endC);
        int endC = Math.max(line.startC, line.endC);
        for (int r = startR; r <= endR; r++) {
            for (int c = startC; c <= endC; c++) {
                board[r][c]++;
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


        public static Line createLine(String nextLine) {
            String[] points = nextLine.split( " -> ");
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
    }
}
