import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ConnectedCell {

    // Complete the connectedCell function below.
    static int connectedCell(int[][] matrix) {
        int maxSize = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != 0) {
                    int size = sizeOfRegionStartingAt(new Board(matrix), new Coord(i, j));
                    maxSize = Math.max(size, maxSize);
                }
            }
        }
        return maxSize;

    }

    static class Coord {
        final int r;
        final int c;

        public Coord(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coord coord = (Coord) o;
            return r == coord.r &&
                    c == coord.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }

        public List<Coord> neighbors() {
            List<Coord> list = new ArrayList<>();
            list.add(new Coord(r - 1, c));
            list.add(new Coord(r - 1, c + 1));
            list.add(new Coord(r, c + 1));
            list.add(new Coord(r + 1, c + 1));
            list.add(new Coord(r + 1, c));
            list.add(new Coord(r + 1, c - 1));
            list.add(new Coord(r, c - 1));
            list.add(new Coord(r - 1, c - 1));
            return list;
        }
    }

    static class Board {
        final int rows;
        final int cols;
        final int[][] matrix;

        public Board(int[][] matrix) {
            this.rows = matrix.length;
            this.cols = matrix[0].length;
            this.matrix = matrix;
        }

        public boolean afloat(Coord c) {
            return matrix[c.r][c.c] != 0;
        }

        public List<Coord> neighborsOnBoard(Coord coord) {
            return coord.neighbors().stream().filter(
                    c -> (c.r >= 0) && (c.r < rows) && (c.c >= 0) && (c.c < cols)).collect(Collectors.toList());
        }

        public void sinkCoord(Coord coord) {
            matrix[coord.r][coord.c] = 0;
        }

        public List<Coord> afloatNeighborsOnBoard(Coord coord) {
            return neighborsOnBoard(coord).stream().filter(this::afloat).collect(Collectors.toList());
        }
    }

    private static int sizeOfRegionStartingAt(Board board, Coord startCoord) {
        int size = 0;
        Queue<Coord> coords = new LinkedList<>();
        coords.add(startCoord);
        while (!coords.isEmpty()) {
            Coord c = coords.remove();
            if (board.afloat(c)) {
                board.sinkCoord(c);
                size++;
                coords.addAll(board.afloatNeighborsOnBoard(c));
            }
        }
        return size;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int m = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[][] matrix = new int[n][m];

        for (int i = 0; i < n; i++) {
            String[] matrixRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < m; j++) {
                int matrixItem = Integer.parseInt(matrixRowItems[j]);
                matrix[i][j] = matrixItem;
            }
        }

        int result = connectedCell(matrix);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

