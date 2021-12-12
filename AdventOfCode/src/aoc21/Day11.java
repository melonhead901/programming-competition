package aoc21;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Day11 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Board board = Board.createBoard(in);
        int count = 1;
        System.out.println(board);
        while (true) {
            board.executeStep();
            if (board.didAllFlash()) {
                break;
            }
            board.countFlashesAndContinue();
            System.out.println(board);
            count++;
        }
        System.out.println(count);
    }

    static class Board {
        final Set<Day11Point> points;
        private final int maxR;
        private final int maxC;
        Set<Day11Point> flashedThisRound;

        public Board(Set<Day11Point> points, int maxR, int maxC) {
            this.points = points;
            this.flashedThisRound = new HashSet<>();
            this.maxR = maxR;
            this.maxC = maxC;
        }

        public static Board createBoard(Scanner in) {
            int row = 0;
            int maxCol = 0;
            Set<Day11Point> points = new HashSet<>();
            while (in.hasNextLine()) {
                int col = 0;
                String line = in.nextLine();
                for (char c : line.toCharArray()) {
                    int val = Integer.parseInt(c + "");
                    points.add(new Day11Point(row, col, val));
                    col++;
                    maxCol = Math.max(col, maxCol);
                }
                row++;
            }
            return new Board(points, row, maxCol);
        }

        public List<Day11Point> neighbors(Point p) {
            List<Day11Point> results = new ArrayList<>();
            for (int r = -1; r <= 1; r++) {
                for (int c = -1; c <= 1; c++) {
                    if ((r == 0) && (c == 0)) {
                        continue;
                    }
                    Optional<Day11Point> newPoint = findPoint(p.r + r, p.c + c);
                    newPoint.ifPresent(results::add);
                }
            }
            return results;
        }

        private Optional<Day11Point> findPoint(int r, int c) {
            return this.points.stream().filter(p -> (p.r == r) && (p.c == c)).findAny();
        }

        public void executeStep() {
            points.forEach(Day11Point::increment);
            Queue<Day11Point> pointsToConsider = new LinkedList<>(points);
            while (!pointsToConsider.isEmpty()) {
                Day11Point p = pointsToConsider.remove();
                if ((p.value > 9) && !flashedThisRound.contains(p)) {
                    List<Day11Point> neighbors = this.neighbors(p);
                    neighbors.forEach(Day11Point::increment);
                    pointsToConsider.addAll(neighbors);
                    flashedThisRound.add(p);
                }
            }
        }

        public int countFlashesAndContinue() {
            int result = flashedThisRound.size();
            for (Day11Point p : flashedThisRound) {
                p.reset();
            }
            flashedThisRound.clear();
            return result;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            for (int r = 0; r < maxR; r++) {
                for (int c = 0; c < maxC; c++) {
                    builder.append(getPointValue(r, c));
                }
                builder.append('\n');
            }
            return builder.toString();
        }

        public int getPointValue(int finalR, int finalC) {
            return findPoint(finalR, finalC).get().value;
        }

        public boolean didAllFlash() {
            return this.flashedThisRound.size() == this.points.size();
        }
    }
}
