package aoc21;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Day10 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<Long> scores = new ArrayList<>();
        while (in.hasNextLine()) {
            String line = in.nextLine();
            if (processLine(line) == 0) {
                scores.add(finishLine(line));
            }
        }
        Collections.sort(scores);
        System.out.println(scores.get(scores.size()/2));
    }

    private static long finishLine(String line) {
        Stack<Character> chars = new Stack<>();
        for (char c : line.toCharArray()) {
            switch (c) {
                case '[':
                case '(':
                case '{':
                case '<':
                    chars.push(c);
                    break;
                case ')':
                case ']':
                case '}':
                case '>':
                    chars.pop();
                    break;
            }
        }
        long sum = 0;
        while (!chars.empty()) {
            sum *= 5;
            switch (chars.pop()) {
                case '(':
                    sum += 1;
                    break;
                case '[':
                    sum += 2;
                    break;
                case '{':
                    sum += 3;
                    break;
                case '<':
                    sum += 4;
                    break;
            }
        }
        return sum;
    }

    private static int processLine(String nextLine) {
        Stack<Character> chars = new Stack<>();
        for (char c : nextLine.toCharArray()) {
            switch (c) {
                case '[':
                case '(':
                case '{':
                case '<':
                    chars.push(c);
                    break;
                case ')':
                    char popped = chars.pop();
                    if (popped != '(') {
                        return 3;
                    }
                    break;
                case ']':
                    popped = chars.pop();
                    if (popped != '[') {
                        return 57;
                    }
                    break;
                case '}':
                    popped = chars.pop();
                    if (popped != '{') {
                        return 1197;
                    }
                    break;
                case '>':
                    popped = chars.pop();
                    if (popped != '<') {
                        return 25137;
                    }
                    break;
            }
        }
        return 0;
    }
}
