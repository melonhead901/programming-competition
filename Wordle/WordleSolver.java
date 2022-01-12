package Wordle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class WordleSolver {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("/Users/kdonohue/Downloads/Collins Scrabble Words (2019).txt"));
        Set<String> words = new LinkedHashSet<>();
        while (scanner.hasNext()) {
            String word = scanner.next();
            if (word.length() == 5) {
                words.add(word.toLowerCase(Locale.ROOT));
            }
        }
        printState(words);
        Scanner in = new Scanner(System.in);
        for (int guessNum = 1; guessNum <= 6; guessNum++) {
            String guess = in.nextLine();
            String result = in.nextLine();

            for (int i = 0; i < guess.length(); i++) {
                char c = guess.charAt(i);
                Constraint constraint = switch (result.charAt(i)) {
                    case '0' -> new NotPresentConstraint(c);
                    case '1' -> new PresentNotAtPositionConstraint(c, i);
                    case '2' -> new PositionConstraint(c, i);
                    default -> throw new IllegalStateException();
                };
                words = words.stream().filter(constraint::allows).collect(Collectors.toSet());
                printState(words);
            }
        }
    }

    private static void printState(Set<String> words) {
        System.out.print(words.size() + " words. Sample guesses: " );
        words.stream().limit(10).forEach(w -> System.out.print(w + " "));
        System.out.println();
    }

    abstract static class Constraint {
        abstract boolean allows(String word);
    }

    static class NotPresentConstraint extends Constraint {

        private final char c;

        public NotPresentConstraint(char c) {
            this.c = c;
        }

        @Override
        boolean allows(String word) {
            return !word.contains(c + "");
        }
    }

    static class PresentNotAtPositionConstraint extends Constraint {

        private final char c;
        private final int pos;

        public PresentNotAtPositionConstraint(char c, int pos) {
            this.c = c;
            this.pos = pos;
        }

        @Override
        boolean allows(String word) {
            return word.contains(c + "") && !(word.charAt(pos) == c);
        }
    }

    static class PositionConstraint extends Constraint {

        private final char c;
        private final int pos;

        public PositionConstraint(char c, int pos) {
            this.c = c;
            this.pos = pos;
        }

        @Override
        boolean allows(String word) {
            return word.charAt(pos) == c;
        }
    }
}
