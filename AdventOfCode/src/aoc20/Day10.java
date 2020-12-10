package aoc20;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Day10 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<Integer> ints = new ArrayList<>();
        while (in.hasNextLine()) {
            String line = in.nextLine();
            if (line.isEmpty()) {
                break;
            }
            ints.add(Integer.parseInt(line));
        }
        Collections.sort(ints);
        // part1(ints);
        int max = ints.stream().max(Comparator.naturalOrder()).get();
        ints.add(0);
        long count = recur(new HashSet<>(ints), max + 3);
        System.out.println(count);
    }

    static Map<Integer, Long> memo = new HashMap<>();

    private static long recur(Set<Integer> ints, int soFar) {
        if (memo.containsKey(soFar)) {
            return memo.get(soFar);
        }
        if (soFar == 0) {
            return 1;
        }
        if (soFar < 0) {
            return 0;
        }
        long count = 0;
        if (ints.contains(soFar - 1)) {
            count += recur(ints, soFar - 1);
        }
        if (ints.contains(soFar - 2)) {
            count += recur(ints, soFar - 2);
        }
        if (ints.contains(soFar - 3)) {
            count += recur(ints, soFar - 3);
        }
        memo.put(soFar, count);
        return count;
    }

    private static void part1(List<Integer> ints) {
        int oneJumps = 0;
        int threeJumps = 0;
        int curr = 0;
        for (int next : ints) {
            int diff = next - curr;
            if (diff == 1) {
                System.out.printf("%s to %s is 1\n", curr, next);
                oneJumps++;
            } else if (diff == 3) {
                threeJumps++;
                System.out.printf("%s to %s is 3\n", curr, next);
            } else {
                throw new IllegalStateException();
            }
            curr = next;
        }
        System.out.println(oneJumps);
        System.out.println(threeJumps);
        System.out.println(oneJumps * threeJumps);
    }
}
