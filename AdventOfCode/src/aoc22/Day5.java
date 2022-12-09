package aoc22;

import com.google.common.collect.Lists;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day5 {

    public static final int ROW_COUNT = 8;
    public static final int STACKS = 9;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<Stack<Character>> stacks = scanBoard(in);
        in.nextLine();
        System.out.println(stacks);
        while (in.hasNextLine()) {
            processLine(in.nextLine(), stacks);
            System.out.println(stacks);
        }
        for (Stack<Character> stack : stacks) {
            System.out.print(stack.peek());
        }
    }

    private static void processLine(String nextLine, List<Stack<Character>> stacks) {
        Pattern pattern = Pattern.compile("move (\\d+) from (\\d+) to (\\d+)");
        Matcher matcher = pattern.matcher(nextLine);
        matcher.find();
        int num = Integer.parseInt(matcher.group(1));
        int src = Integer.parseInt(matcher.group(2)) - 1;
        int dst = Integer.parseInt(matcher.group(3)) - 1;
        Stack<Character> moved = new Stack<>();
        for (int i = 0; i < num; i++) {
            moved.push(stacks.get(src).pop());
        }
        for (int i = 0; i < num; i++) {
            stacks.get(dst).push(moved.pop());
        }
    }

    private static List<Stack<Character>> scanBoard(Scanner in) {
        List<List<Character>> results = new ArrayList<>();
        for (int i = 0; i < STACKS; i++) {
            results.add(new ArrayList<>());
        }
        String line;
        for (int row = 0; row < ROW_COUNT; row++) {
            line = in.nextLine();
            for (int i = 0; i < STACKS; i++) {
                char c = line.charAt(i * 4 + 1);
                if (c != ' ') {
                    results.get(i).add(c);
                }
            }
        }
        in.nextLine();
        return convertListsToStacks(results);
    }

    private static List<Stack<Character>> convertListsToStacks(List<List<Character>> lists) {
        List<Stack<Character>> result = new ArrayList<>();
        for (int i = 0; i < STACKS; i++) {
            List<Character> lst = lists.get(i);
            lst = Lists.reverse(lst);
            Stack<Character> stack = new Stack<>();
            for (Character character : lst) {
                stack.push(character);
            }
            result.add(stack);
        }
        return result;
    }
}
