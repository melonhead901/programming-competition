package aoc20;

import java.util.Scanner;
import java.util.Stack;

public class Day18 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        long sum = 0;
        while (!line.isBlank()) {
            long result = processLine(line);
            sum += result;
            System.err.println(result);
            line = in.nextLine();
        }
        System.out.println(sum);
    }

    private static long processLine(String line) {
        Stack<Long> values = new Stack<>();
        Stack<Character> ops = new Stack<>();
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == ' ') {
                continue;
            }
            if (Character.isDigit(c)) {
                values.push(Long.parseLong(c + ""));
            } else if ((c == '+') || (c == '*')) {
                while (!ops.isEmpty() && (ops.peek() != '(')) {
                    doProcess(values, ops.pop());
                }
                ops.push(c);
            } else if (c == '(') {
                ops.push(c);
            } else if (c == ')') {
                while (ops.peek() != '(') {
                    doProcess(values, ops.pop());
                }
                ops.pop();
            } else {
                throw new IllegalStateException();
            }
        }
        while (!ops.isEmpty()) {
            doProcess(values, ops.pop());
        }
        return values.pop();
    }

    private static void doProcess(Stack<Long> values, Character pop) {
        long a = values.pop();
        long b = values.pop();
        switch (pop) {
            case '+':
                values.push(a + b);
                return;
            case '*':
                values.push(a * b);
                return;
            default:
                throw new IllegalStateException(pop +"");

        }
    }
}
