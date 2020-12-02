package aoc20;

import java.util.Arrays;
import java.util.Scanner;

public class Day2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int count = 0;
        while (in.hasNextLine()) {
            String line = in.nextLine();
            if (line.isEmpty()) {
                break;
            }
            boolean valid = processCase2(line);
            if (valid) {
                count++;
            }
        }
        System.out.println(count);
    }

    private static boolean processCase(String line) {
        State state = new State(line);
        int charCount = 0;
        for (char c : state.password.toCharArray()) {
            if (c == state.target) {
                charCount++;
            }
        }
        System.out.println(state.password);
        return (charCount >= state.firstNum) && (charCount <= state.secondNum);
    }

    private static boolean processCase2(String line) {
        State state = new State(line);
        return (state.password.charAt(state.firstNum - 1) == state.target)
                ^ (state.password.charAt(state.secondNum - 1) == state.target);
    }
}

class State {
    final int firstNum;
    final int secondNum;
    final char target;
    final String password;

    public State(String line) {
        Scanner wordScanner = new Scanner(line);
        String[] countSplits = getCountSplits(wordScanner);
        this.firstNum = Integer.parseInt(countSplits[0]);
        this.secondNum = Integer.parseInt(countSplits[1]);
        String character = wordScanner.next();
        this.target = character.charAt(0);
        this.password = wordScanner.next();
    }

    private static String[] getCountSplits(Scanner wordScanner) {
        String count = wordScanner.next();
        return count.split("-");
    }
}
