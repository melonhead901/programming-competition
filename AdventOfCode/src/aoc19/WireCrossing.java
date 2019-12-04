package aoc19;

import com.google.common.collect.ImmutableList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class WireCrossing {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("/Users/kdonohue/Downloads/day3.txt"));
          //lines = ImmutableList.of( "R75,D30,R83,U83,L12,D49,R71,U7,L72",
        //"U62,R66,U55,R34,D71,R55,D58,R83");
        List<Path> line1Dirs = parseDirs(lines.get(0));
        Set<Position> positions = new HashSet<>();
        Map<Position, Integer> wire1Steps = new HashMap<>();
        Position position = Position.CENTRAL_PORT;
        int steps1 = 0;
        for (Path path : line1Dirs) {
            //System.out.println("Starting pos " + position);
            List<Position> positionsAlongPath = position.positionsAlongPath(path);
            positions.addAll(positionsAlongPath);
            for (Position p : positionsAlongPath) {
                if (!wire1Steps.containsKey(p)) {
                    wire1Steps.put(p, steps1);
                }
                steps1++;
            }
            System.out.println(positionsAlongPath);
            position = position.moveAlongPath(path);
        }
        List<Path> line2Dirs = parseDirs(lines.get(1));
        positions.remove(Position.CENTRAL_PORT);
        position = Position.CENTRAL_PORT;
        Map<Position, Integer> combinedSteps = new HashMap<>();
        int steps2 = 0;
        List<Position> matchingPositions = new ArrayList<>();
        for (Path p : line2Dirs) {
            for (Position pathPos : position.positionsAlongPath(p)) {
                if (positions.contains(pathPos)) {
                    matchingPositions.add(pathPos);
                    if (!combinedSteps.containsKey(pathPos)) {
                        combinedSteps.put(pathPos, wire1Steps.get(pathPos) + steps2);
                    }
                }
                steps2++;
            }
            position = position.moveAlongPath(p);
        }

        Map.Entry<Position, Integer> bestPos = combinedSteps.entrySet().stream().min(Comparator.comparingInt(Map.Entry::getValue)).get();
        System.out.println(bestPos.getValue());

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

class Match {
    final Position position;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return wire1Steps == match.wire1Steps &&
                wire2Steps == match.wire2Steps &&
                position.equals(match.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, wire1Steps, wire2Steps);
    }

    public Match(Position position, int wire1Steps, int wire2Steps) {
        this.position = position;
        this.wire1Steps = wire1Steps;
        this.wire2Steps = wire2Steps;
    }

    final int wire1Steps;
    int wire2Steps;

    final int totalDistance() {
        return wire1Steps + wire2Steps;
    }

}

class Path {
    final Direction direction;
    final int distance;

    Path(Direction d, int distance) {
        this.direction = d;
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
    static final Position CENTRAL_PORT = new Position(0, 0);

    @Override
    public String toString() {
        return "Position{" +
                "r=" + r +
                ", c=" + c +
                '}';
    }

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

    List<Position> positionsAlongPath(Path p) {
        List<Position> result = new ArrayList<>();
        for (int i = 0; i < p.distance; i++) {
            int newR = i * p.direction.dr + r;
            int newC = i * p.direction.dc + c;
            result.add(new Position(newR, newC));
        }
        return result;
    }

    Position moveAlongPath(Path p) {
        int newR = p.distance * p.direction.dr + r;
        int newC = p.distance * p.direction.dc + c;
        return new Position(newR, newC);
    }

    int manhattanDistance() {
        return Math.abs(r) + Math.abs(c);
    }
}

