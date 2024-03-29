package aoc22;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Day12 {
    static class PointComparator implements Comparator<List<Point>> {

        final Board board;

        public PointComparator(Board board) {
            this.board = board;
        }

        @Override
        public int compare(List<Point> o1, List<Point> o2) {
            Point p1 = o1.get(o1.size() - 1);
            Point p2 = o2.get(o2.size() - 1);
            char c1 = board.grid[p1.r][p1.c];
            char c2 = board.grid[p2.r][p2.c];
            int diff = (int) c2 - (int) c1;
            if (diff != 0) {
                return diff;
            }
            int d1 = board.distToJump[p1.r][p1.c];
            int d2 = board.distToJump[p2.r][p2.c];
            return d1 - d2;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Board board = Board.createBoard(in);

        Queue<List<Point>> paths = new LinkedList<>();
        Set<Point> visited = new HashSet<>();
        paths.add(Collections.singletonList(board.start));
        int pathsSeen = 0;
        while (!paths.isEmpty()) {
            List<Point> path = paths.remove();
            Point last = path.get(path.size() - 1);
            visited.add(last);
            /*
            paths = paths.stream()
                    .filter(p -> !p.get(p.size() - 1).equals(last))
                    .collect(Collectors.toCollection(LinkedList::new));
             */
            pathsSeen++;
            if (pathsSeen % 100000 == 0) {
                System.out.println(pathsSeen);
                System.out.println(last);
                printBoard(board, visited);
            }
            if (last.equals(board.end)) {
                System.out.println(path.size() - 1);
                break;
            }
            for (Point next : board.possibleNeighbors(last)) {
                if (!visited.contains(next)) {
                    List<Point> newPath = new ArrayList<>(path);
                    newPath.add(next);
                    paths.add(newPath);
                }
            }
        }
    }

    private static void printBoard(Board board, Set<Point> visited) {
        for (int r = 0; r < board.grid.length; r++) {
            for (int c = 0; c < board.grid[0].length; c++) {
                if (visited.contains(new Point(r, c))) {
                    System.out.print("X");
                } else {
                    System.out.print(board.grid[r][c]);
                }
            }
            System.out.println();
        }
    }

    static class Board {
        final char[][] grid;
        final Point start;
        final Point end;
        final int[][] distToNext;
        final int[][] distToJump;

        private Board(char[][] grid) {
            Point end1;
            Point start1;
            this.grid = grid;
            start1 = null;
            end1 = null;
            for (int r = 0; r < grid.length; r++) {
                for (int c = 0; c < grid[0].length; c++) {
                    if (grid[r][c] == 'S') {
                        start1 = new Point(r, c);
                        grid[r][c] = 'a';
                    } else if (grid[r][c] == 'E') {
                        end1 = new Point(r, c);
                        grid[r][c] = 'z';
                    }
                }
            }
            end = end1;
            start = start1;
            if (end == null || start == null) {
                throw new IllegalStateException("null start or end");
            }
            Map<Character, List<Point>> charMap = new HashMap<>();
            char start = 'a';
            char end = 'z';
            Map<Character, List<Point>> jumpSquares = new HashMap<>();
            for (int i = (int) start; i <= (int) end; i++) {
                charMap.put((char) i, new ArrayList<>());
                jumpSquares.put((char) i, new ArrayList<>());
            }
            for (int r = 0; r < grid.length; r++) {
                for (int c = 0; c < grid[0].length; c++) {
                    charMap.get(grid[r][c]).add(new Point(r, c));
                }
            }
            distToNext = new int[grid.length][];
            distToJump = new int[grid.length][];
            for (int r = 0; r < grid.length; r++) {
                distToNext[r] = new int[grid[0].length];
                distToJump[r] = new int[grid[0].length];
            }
            for (int r = 0; r < grid.length; r++) {
                for (int c = 0; c < grid[0].length; c++) {
                    char target = (char) ((int) grid[r][c] + 1);
                    int minDist = Integer.MAX_VALUE;
                    Point minPoint = null;
                    for (Point p : charMap.getOrDefault(target, new ArrayList<>())) {
                        int dist = Math.abs(p.r - r) + Math.abs(p.c - c);
                        minDist = Math.min(dist, minDist);
                        if (dist == minDist) {
                            minPoint = p;
                        }
                    }
                    distToNext[r][c] = minDist;
                    if (minDist == 1) {
                        jumpSquares.get(grid[r][c]).add(minPoint);
                    }
                }
            }
            for (int r = 0; r < grid.length; r++) {
                for (int c = 0; c < grid[0].length; c++) {
                    int minDist = Integer.MAX_VALUE;
                    for (Point p : jumpSquares.get(grid[r][c])) {
                        int dist = Math.abs(p.r - r) + Math.abs(p.c - c);
                        minDist = Math.min(dist, minDist);
                    }
                    distToJump[r][c] = minDist;
                }
            }
        }

        static Board createBoard(Scanner in) {
            List<char[]> lines = new ArrayList<>();
            while (in.hasNext()) {
                String line = in.next();
                char[] chars = line.toCharArray();
                lines.add(chars);
            }
            char[][] board = new char[lines.size()][];
            for (int i = 0; i < lines.size(); i++) {
                board[i] = lines.get(i);
            }
            return new Board(board);
        }

        public List<Point> possibleNeighbors(Point p) {
            List<Point> nextSteps = new ArrayList<>();
            int pElev = this.grid[p.r][p.c];
            for (Point possibleStep : p.neighbors()) {
                if (onBoard(possibleStep) && canStep(pElev, possibleStep)) {
                    nextSteps.add(possibleStep);
                }
            }
            return nextSteps;
        }

        private boolean canStep(int pElev, Point point) {
            int diff = ((int) grid[point.r][point.c] - pElev);
            return diff == 1 || diff == 0;
        }

        private boolean onBoard(Point p) {
            return p.r >= 0 && p.r < this.grid.length && p.c >= 0 && p.c < this.grid[0].length;
        }
    }

    static class Point {
        final int r;
        final int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public List<Point> neighbors() {
            List<Point> result = new ArrayList<>();
            result.add(new Point(this.r - 1, this.c));
            result.add(new Point(this.r + 1, this.c));
            result.add(new Point(this.r, this.c - 1));
            result.add(new Point(this.r, this.c + 1));
            return result;
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

        @Override
        public String toString() {
            return "(" + r + "," + c + ')';
        }
    }
}
