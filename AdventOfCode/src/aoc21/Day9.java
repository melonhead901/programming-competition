package aoc21;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day9 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<String> lines = new ArrayList<>();
        while (in.hasNextLine()) {
            lines.add(in.nextLine());
        }
        int[][] grid = new int[lines.size()][];
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            grid[i] = new int[line.length()];
            for (int j = 0; j < line.length(); j++) {
                grid[i][j] = Integer.parseInt(line.charAt(j) + "");
            }
        }
        int sum = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                if (isLowPoint(grid, r, c)) {
                    System.out.printf("Low point %s %s\n", r, c);
                    sum += grid[r][c] + 1;
                }
            }
        }
        System.out.println(sum);
    }

    private static boolean isLowPoint(int[][] grid, int r, int c) {
        List<Point> neighbors = getNeighbors(r, c);
        neighbors = neighbors.stream().filter(p -> isPointInGrid(p, grid)).collect(Collectors.toList());
        for (Point p : neighbors) {
            if (grid[p.r][p.c] <= grid[r][c]) {
                return false;
            }
        }
        return true;
    }

    private static boolean isPointInGrid(Point p, int[][] grid) {
        return (p.c >= 0) && (p.r >= 0) && (p.r < grid.length) && (p.c < grid[p.r].length);
    }

    private static List<Point> getNeighbors(int r, int c) {
        List<Point> points = new ArrayList<>();
        points.add(new Point(r - 1, c));
        points.add(new Point(r + 1, c));
        points.add(new Point(r, c - 1));
        points.add(new Point(r, c + 1));
        return points;
    }
}
