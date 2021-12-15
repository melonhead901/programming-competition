package aoc21;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Day15 {
    static Map<Point, Integer> board;
    static Point goalPoint;


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<String> lines = new ArrayList<>();
        while (in.hasNextLine()) {
            lines.add(in.nextLine());
        }
        board = new HashMap<>();
        int rows = lines.size();
        int cols = lines.get(rows - 1).length();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int value = Integer.parseInt(lines.get(i).charAt(j) + "");
                for (int ii = 0; ii < 5; ii++) {
                    for (int jj = 0; jj < 5; jj++) {
                        int thisValue = (ii + jj + value);
                        while (thisValue > 9) {
                            thisValue -= 9;
                        }
                        Point point = new Point((ii * rows) + i, (jj * cols) + j);
                        if (board.containsKey(point)) {
                            throw new RuntimeException(point.toString());
                        }
                        board.put(point, thisValue);
                    }
                }
            }
        }
        for (int i = 0; i < (5 * rows); i++) {
            for (int j = 0; j < (5 * cols); j++) {
                if (!board.containsKey(new Point(i, j))) {
                    throw new RuntimeException(new Point(i, j).toString());
                }
            }
        }
        goalPoint = new Point((rows * 5) - 1, (cols * 5) - 1);
        Map<Point, Integer> dist = new HashMap<>();
        Set<Point> points = new HashSet<>();
        for (Point p : board.keySet()) {
            dist.put(p, Integer.MAX_VALUE);
            points.add(p);
        }
        dist.put(new Point(0, 0), 0);

        while (!points.isEmpty()) {
            Point p = getMinPoint(dist, points);
            points.remove(p);
            if (p.equals(goalPoint)) {
                break;
            }
            for (Point neighbor : p.getPointNeighbors()) {
                if (points.contains(neighbor)) {
                    int alt = dist.get(p) + board.get(neighbor);
                    if (!dist.containsKey(neighbor)) {
                        throw new RuntimeException(neighbor.toString());
                    }
                    if (alt < dist.get(neighbor)) {
                        dist.put(neighbor, alt);
                    }
                }
            }
            if ((points.size() % 100) == 0) {
                System.out.printf("%s: %s\n", points.size(), dist.get(p));
                printProgress(points, rows, cols);
            }
        }

        System.out.println(dist.get(goalPoint));

    }

    private static void printProgress(Set<Point> points, int rows, int cols) {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                Point p = new Point(r, c);
                System.out.print(points.contains(p) ? "X" : ".");
            }
            System.out.println();
        }
    }

    private static Point getMinPoint(Map<Point, Integer> dist, Set<Point> points) {
        return points.stream().min(Comparator.comparingInt(dist::get)).get();
    }

    private static int calcCost(Point point, Map<Point, Integer> costs, Set<Point> seen) {
        if (costs.containsKey(point)) {
            return costs.get(point);
        }
        if (point.equals(goalPoint)) {
            return 0;
        }
        int cost = Integer.MAX_VALUE;
        HashSet<Point> newSeen = new HashSet<>(seen);
        newSeen.add(point);
        cost = Math.min(cost, getCost(costs, new Point(point.r, point.c + 1), newSeen));
        cost = Math.min(cost, getCost(costs, new Point(point.r, point.c - 1), newSeen));
        cost = Math.min(cost, getCost(costs, new Point(point.r + 1, point.c), newSeen));
        cost = Math.min(cost, getCost(costs, new Point(point.r - 1, point.c), newSeen));
        return cost;
    }

    private static int getCost(Map<Point, Integer> costs, Point right, Set<Point> seen) {
        if (board.containsKey(right) && !seen.contains(right)) {
            return board.get(right) + calcCost(right, costs, seen);
        }
        return Integer.MAX_VALUE;
    }

}
