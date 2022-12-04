package aoc15;

import java.util.Scanner;

public abstract class Solver<T> {
    private final Scanner scanner;

    public Solver(Scanner scanner) {
        this.scanner = scanner;
    }

    @SuppressWarnings("unused")
    public String getLine() {
        return scanner.nextLine();
    }

    public abstract String getInput(Scanner inputScanner);

    public abstract T solveCase(String s);

    public T processCase() {
        return solveCase(getInput(scanner));
    }

    public boolean isReady() {
        return scanner.hasNext();
    }

    @SuppressWarnings("unused")
    public void solveUntilDone() {
        while (isReady()) {
            System.out.println(processCase());
        }
    }
}
