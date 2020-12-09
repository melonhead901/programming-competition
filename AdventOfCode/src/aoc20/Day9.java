package aoc20;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Day9 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Queue<Long> queue = new LinkedList<>();
        Set<Long> set = new HashSet<>();
        for (int i = 0; i <= 25; i++) {
            long l = in.nextLong();
            queue.add(l);
            set.add(l);
        }
        while (true) {
            long next = in.nextLong();
            if (canAdd(set, next)) {
                long removed = queue.remove();
                set.remove(removed);
                queue.add(next);
                set.add(next);
            } else {
                System.out.println(next);
                break;
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
