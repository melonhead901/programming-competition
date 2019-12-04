package aoc19;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class WireCrossing {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("/Users/kdonohue/Downloads/day3.txt"));
        String line1 = lines.get(0);
        List<Path> line1Dirs = parseDirs(line1);
        String line2 = lines.get(1);
        List<Path> line2Dirs = parseDirs(line2);
        Set<Position> positions = new HashSet<>();
        Position position = new Position(0, 0);
        for (Path p : line1Dirs) {
            position = position.moveAlongPath(p);
            positions.add(position);
        }
        List<Position> matchingPositions = new ArrayList<>();
        position = new Position(0, 0);
        for (Path p : line2Dirs) {
            position = position.moveAlongPath(p);
            if (positions.contains(position)) {
                matchingPositions.add(position);
            }
        }

        Position bestPos = matchingPositions.stream().min(Comparator.comparingInt(pos -> Math.abs(pos.c) + Math.abs(pos.r))).get();
        System.out.println(Math.abs(bestPos.c) + Math.abs(bestPos.r));

    }

    private static List<Path> parseDirs(String line) {
        String[] dirs = line.split(",");
        List<Path> result = new ArrayList<>(dirs.length);
        for (String path : dirs) {
            Direction d = Direction.fromChar(path.charAt(0));
            int distance = Integer.parseInt(path.substring(1));
            result.add(new Path(d, distance));
        }
        return result;
    }
}

class Path {
    final Direction d;
    final int distance;

    Path(Direction d, int distance) {
        this.d = d;
        this.distance = distance;
    }
}

enum Direction {
    UP(-1, 0), DOWN(1, 0), LEFT(0, -1), RIGHT(0, 1);

    final int dr;
    final int dc;

    Direction(int dr, int dc) {
        this.dr = dr;
        this.dc = dc;
    }

    static Direction fromChar(char s) {
        switch (s) {
            case 'D':
                return DOWN;
            case 'U':
                return UP;
            case 'L':
                return LEFT;
            case 'R':
                return RIGHT;
            default:
                throw new IllegalStateException("Illegal direction: " + s);
        }
    }

}

class Position {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return r == position.r &&
                c == position.c;
    }

    @Override
    public int hashCode() {
        return Objects.hash(r, c);
    }

    final int r, c;

    Position(int r, int c) {
        this.r = r;
        this.c = c;
    }

    Position moveAlongPath(Path p) {
        int newR = p.distance * p.d.dr;
        int newC = p.distance * p.d.dc;
        return new Position(newR, newC);
    }
}

