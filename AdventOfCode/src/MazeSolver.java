import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.function.Function;
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

class StringAndPath {
    private final String initialString;
    private final String currentPath;

    private StringAndPath(String initialString, String currentPath) {
        this.initialString = initialString;
        this.currentPath = currentPath;
    }

    public static StringAndPath initialState(String initialString) {
        return new StringAndPath(initialString, "");
    }

    public StringAndPath moveUp() {
        return new StringAndPath(initialString, currentPath + 'U');
    }

    public StringAndPath moveDown() {
        return new StringAndPath(initialString, currentPath + 'D');
    }

    public StringAndPath moveLeft() {
        return new StringAndPath(initialString, currentPath + 'L');
    }

    public StringAndPath moveRight() {
        return new StringAndPath(initialString, currentPath + 'R');
    }

    public void printCurrentPath() {
        System.out.println(currentPath);
    }

    public int pathLength() {
        return currentPath.length();
    }

    public String getDirectionalHash() {
        return MazeSolver.calculateHash(initialString + currentPath).substring(0, 4);
    }
}

class GameState {
    private final Position position;
    private final StringAndPath stringAndPath;

    private GameState(Position position, StringAndPath stringAndPath) {
        this.position = position;
        this.stringAndPath = stringAndPath;
    }

    public static GameState initialGameState(String initialString) {
        return new GameState(new Position(0, 0), StringAndPath.initialState(initialString));
    }

    public boolean isGoal() {
        return position.isGoal();
    }

    public List<GameState> getSuccessors() {
        String currentHash = stringAndPath.getDirectionalHash();
        Collection<Optional<GameState>> successors = new ArrayList<>();
        if (currentHash.charAt(0) >= 'b') {
            successors.add(moveInDirectionIfPossible(Position::moveUp, StringAndPath::moveUp));
        }
        if (currentHash.charAt(1) >= 'b') {
            successors.add(moveInDirectionIfPossible(Position::moveDown, StringAndPath::moveDown));
        }
        if (currentHash.charAt(2) >= 'b') {
            successors.add(moveInDirectionIfPossible(Position::moveLeft, StringAndPath::moveLeft));
        }
        if (currentHash.charAt(3) >= 'b') {
            successors.add(moveInDirectionIfPossible(Position::moveRight, StringAndPath::moveRight));
        }
        return successors.stream().filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());
    }

    private Optional<GameState> moveInDirectionIfPossible(Function<Position, Optional<Position>> translation,
                                                          Function<StringAndPath, StringAndPath> directionTranslation) {
        Optional<Position> newPosition = translation.apply(position);
        return newPosition.isPresent() ?
            Optional.of(new GameState(newPosition.get(), directionTranslation.apply(stringAndPath))) :
            Optional.empty();
    }

    public void printSolutionPath() {
        stringAndPath.printCurrentPath();
    }

    public int solutionPathLength() {
        return this.stringAndPath.pathLength();
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