package aoc21;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day13 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Collection<Point> points = new ArrayList<>();
        while (true) {
            String line = in.nextLine();
            if (line.isBlank()) {
                break;
            }
            Scanner lineScanner = new Scanner(line).useDelimiter(",");
            int c = lineScanner.nextInt();
            int r = lineScanner.nextInt();
            points.add(new Point(r, c));
        }
        System.out.println(points);
        List<Folds> folds = new ArrayList<>();
        while (in.hasNextLine()) {
            folds.add(Folds.createFold(in.nextLine()));
        }
        System.out.println(folds);

        printPoints(points);
        for (Folds f : folds) {
            points = points.stream().map(p -> foldAcross(p, f)).collect(Collectors.toSet());
            points = points.stream().filter(p -> (p.r >= 0) && (p.c >= 0)).collect(Collectors.toSet());
            System.out.println(points.size());
            printPoints(points);
        }
    }

    private static void printPoints(Collection<Point> points) {
        int maxR = points.stream().map(p -> p.r).max(Comparator.naturalOrder()).get();
        int maxC = points.stream().map(p -> p.c).max(Comparator.naturalOrder()).get();
        for (int r = 0; r <= maxR; r++) {
            for (int c = 0; c <= maxC; c++) {
                if (points.contains(new Point(r, c))) {
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }

    public static Point foldAcross(Point p, Folds fold) {
        switch (fold.axis) {
            case Y:
                if (p.r > fold.val) {
                    int dist = p.r - fold.val;
                    return new Point(fold.val - dist, p.c);
                }
                break;
            case X:
                if (p.c > fold.val) {
                    int dist = p.c - fold.val;
                    return new Point(p.r, fold.val - dist);
                }
                break;
        }
        return p;
    }

    private static class Folds {
        public static Folds createFold(String nextLine) {
            String[] parts = nextLine.split("=");
            String first = parts[0];
            Axis axis = (first.charAt(first.length() - 1) == 'x') ? Axis.X : Axis.Y;
            int val = Integer.parseInt(parts[1]);
            return new Folds(axis, val);
        }

        enum Axis {
            X, Y
        }

        final Axis axis;
        final int val;

        public Folds(Axis axis, int val) {
            this.axis = axis;
            this.val = val;
        }

        @Override
        public String toString() {
            return "Folds{" +
                    "axis=" + axis +
                    ", val=" + val +
                    '}';
        }
    }
}
