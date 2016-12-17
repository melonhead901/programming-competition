import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;

public class MazeSolver {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        MazeSolver mazeSolver = new MazeSolver();
        Scanner in = new Scanner(System.in);
        mazeSolver.solveWithInitialString(in.nextLine());
    }

    static String calculateHash(String toEnc) {
        try {
            MessageDigest mdEnc = MessageDigest.getInstance("MD5");
            mdEnc.update(toEnc.getBytes(), 0, toEnc.length());
            StringBuilder hex = new StringBuilder();
            for (byte b : mdEnc.digest()) {
                hex.append(String.format("%02x", b));
            }
            return hex.toString();
        } catch (NoSuchAlgorithmException ex) {
            throw new IllegalStateException();
        }
    }

    private void solveWithInitialString(String s) {
        GameState initialState = GameState.initialGameState(s);
        Queue<GameState> queue = new LinkedList<>();
        queue.add(initialState);
        int maxLength = -1;
        while (!queue.isEmpty()) {
            GameState top = queue.poll();
            if (top.isGoal()) {
                top.printSolutionPath();
                maxLength = Math.max(maxLength, top.solutionPathLength());
                continue;
            }
            queue.addAll(top.getSuccessors());
        }
        System.out.print(maxLength);
    }


}

class GameState {
    private GameState(Position position, String initialString, String currentPath) {
        this.position = position;
        this.initialString = initialString;
        this.currentPath = currentPath;
    }

    public static GameState initialGameState(String initialString) {
        return new GameState(new Position(0, 0), initialString, "");
    }

    public boolean isGoal() {
        return position.isGoal();
    }

    public List<GameState> getSuccessors() {
        String currentHash = MazeSolver.calculateHash(initialString + currentPath);
        System.out.println(String.format("Hashing %s to get %s ", initialString + currentPath, currentHash
            .substring(0, 4)));
        Collection<Optional<GameState>> successors = new ArrayList<>();
        if (currentHash.charAt(0) >= 'b') {
            successors.add(this.moveUp());
        }
        if (currentHash.charAt(1) >= 'b') {
            successors.add(this.moveDown());
        }
        if (currentHash.charAt(2) >= 'b') {
            successors.add(this.moveLeft());
        }
        if (currentHash.charAt(3) >= 'b') {
            successors.add(this.moveRight());
        }
        return successors.stream().filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());
    }

    private Optional<GameState> moveRight() {
        Optional<Position> position = this.position.moveRight();
        return position.isPresent() ? Optional.of(new GameState(position.get(), this.initialString, this.currentPath
            + 'R')) : Optional.empty();
    }

    private  Optional<GameState> moveLeft() {
        Optional<Position> position = this.position.moveLeft();
        return position.isPresent() ? Optional.of(new GameState(position.get(), this.initialString, this.currentPath
            + 'L')) : Optional.empty();
    }

    private Optional<GameState> moveDown() {
        Optional<Position> position = this.position.moveDown();
        return position.isPresent() ? Optional.of(new GameState(position.get(), this.initialString, this.currentPath
            + 'D')) : Optional.empty();
    }

    private Optional<GameState> moveUp() {
        Optional<Position> position = this.position.moveUp();
        return position.isPresent() ? Optional.of(new GameState(position.get(), this.initialString, this.currentPath
            + 'U')) : Optional.empty();
    }


    private final Position position;
    private final String initialString;
    private final String currentPath;

    public void printSolutionPath() {
        System.out.println(currentPath);
    }

    public int solutionPathLength() {
        return this.currentPath.length();
    }
}


class Position {
    private final int BOUNDARY = 3;

    public Position(int r, int c) {
        this.r = r;
        this.c = c;
    }

    private final int r;
    private final int c;

    boolean isGoal() {
        return (r == BOUNDARY) && (c == BOUNDARY);
    }

    Optional<Position> moveLeft() {
        return (c == 0) ? Optional.empty() : Optional.of(new Position(r, c - 1));
    }

    Optional<Position> moveRight() {
        return (c == BOUNDARY) ? Optional.empty() : Optional.of(new Position(r, c + 1));
    }

    Optional<Position> moveUp() {
        return (r == 0) ? Optional.empty() : Optional.of(new Position(r - 1, c));
    }

    Optional<Position> moveDown() {
        return (r == BOUNDARY) ? Optional.empty() : Optional.of(new Position(r + 1, c));
    }

}