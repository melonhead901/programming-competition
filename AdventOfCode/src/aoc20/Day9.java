package aoc20;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Day9 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<Long> allNumbers = new ArrayList<>();
        Queue<Long> queue = new LinkedList<>();
        Set<Long> set = new HashSet<>();
        for (int i = 0; i <= 25; i++) {
            long l = in.nextLong();
            queue.add(l);
            set.add(l);
            allNumbers.add(l);
        }
        long target = doPart1(in, queue, set, allNumbers);
        for (int i = 0; i < allNumbers.size(); i++) {
            long cumSum = allNumbers.get(i);
            int j = i + 1;
            while (cumSum < target) {
                cumSum += allNumbers.get(j);
                j++;
            }
            if (cumSum == target) {
                long min = allNumbers.get(i);
                long max = allNumbers.get(i);
                for (int ii = i; ii < j; ii++) {
                    min = Math.min(min, allNumbers.get(ii));
                    max = Math.max(max, allNumbers.get(ii));
                }
                System.out.println(min + max);
            }
        }

    }

    private static long doPart1(Scanner in, Queue<Long> queue, Set<Long> set, List<Long> allNumbers) {
        while (true) {
            long next = in.nextLong();
            if (canAdd(set, next)) {
                long removed = queue.remove();
                set.remove(removed);
                queue.add(next);
                set.add(next);
                allNumbers.add(next);
            } else {
                System.out.println(next);
                return next;
            }
        }
    }

    private static boolean canAdd(Set<Long> set, long next) {
        for (long first : set) {
            long second = next - first;
            if ((second != first) && set.contains(second)) {
                return true;
            }
        }
        return false;
    }
}
