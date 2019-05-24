import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class KnightsOnAChessboard {

    // Complete the knightlOnAChessboard function below.
    static int[][] knightlOnAChessboard(int n) {
        int[][] result = new int[n][];
        for (int i = 1; i < n; i++) {
            result[i-1] = new int[n];
            for (int j = 1; j < n; j++) {
                int solveSingle = solveSingle(n, i, j);
                System.out.println(String.format("Solved single (n, i, j) %s %s %s: %s", n, i, j, solveSingle));
                result[i-1][j-1] = solveSingle;
            }
        }

        return result;
    }

    private static int solveSingle(int n, int i, int j) {
        Queue<Coord> positions = new LinkedList<>();
        Set<Coord> seenPositions = new HashSet<>();
        positions.add(Coord.generateInitialCoord(n));
        while (!positions.isEmpty()) {
            Coord coord = positions.remove();
            if (seenPositions.contains(coord)) {
                continue;
            } else {
                seenPositions.add(coord);
            }
            if (coord.isWin()) {
                return coord.depth;
            }
            positions.addAll(coord.subsequentCoords(i, j));
        }
        return -1;
    }

    static class Coord {

        final int x;
        final int y;
        final int depth;
        final int n;

        static Coord generateInitialCoord(int n) {
            return new Coord(0, 0, 0, n);
        }

        List<Coord> subsequentCoords(int i, int j) {
            List<Coord> nextCoords = new ArrayList<>();
            nextCoords.add(new Coord(x + i, y + j, depth + 1, n));
            nextCoords.add(new Coord(x + i, y - j, depth + 1, n));
            nextCoords.add(new Coord(x - i, y + j, depth + 1, n));
            nextCoords.add(new Coord(x - i, y - j, depth + 1, n));
            nextCoords.add(new Coord(x + j, y + i, depth + 1, n));
            nextCoords.add(new Coord(x + j, y - i, depth + 1, n));
            nextCoords.add(new Coord(x - j, y + i, depth + 1, n));
            nextCoords.add(new Coord(x - j, y - i, depth + 1, n));
            return nextCoords.stream().filter(Coord::isOnBoard).collect(Collectors.toList());
        }

        boolean isOnBoard() {
            return (x >= 0) && (y >= 0) && (x < n) && (y < n);
        }

        boolean isWin() {
            return (x == (n - 1)) && (y == (n - 1));
        }


        Coord(int x, int y, int depth, int n) {
            this.x = x;
            this.y = y;
            this.depth = depth;
            this.n = n;
        }

        @Override
        public String toString() {
            return "Coord{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coord coord = (Coord) o;
            return x == coord.x &&
                    y == coord.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[][] result = knightlOnAChessboard(n);

        for (int i = 0; i < result.length-1; i++) {
            for (int j = 0; j < result[i].length-1; j++) {
                bufferedWriter.write(String.valueOf(result[i][j]));

                if (j != result[i].length - 2) {
                    bufferedWriter.write(" ");
                }
            }

            if (i != result.length - 2) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

