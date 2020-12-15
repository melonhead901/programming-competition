package aoc20;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day15 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Scanner lineScanner = new Scanner(in.nextLine());
        lineScanner.useDelimiter(",");
        List<Integer> ints = new ArrayList<>();
        while (lineScanner.hasNextInt()) {
            ints.add(lineScanner.nextInt());
        }
        Map<Long, Long> numbersSaid = new HashMap<>();
        long turn = 1;
        long last = -1;
        boolean wasSeen = false;
        long lastTurnSeen = -1;
        while (turn <= 2020) {
            long num;
            if (!ints.isEmpty()) {
                num = ints.get(0);
                ints = ints.subList(1, ints.size());
            } else {
                if (wasSeen) {
                    num = turn - 1 - lastTurnSeen;
                } else {
                    num = 0;
                }
                wasSeen = numbersSaid.containsKey(num);
                if (wasSeen) {
                    lastTurnSeen = numbersSaid.get(num);
                }
            }
            last = num;
            numbersSaid.put(num, turn);
            turn++;
        }
        System.out.println(last);
    }

    static class LimitedQueue<E> extends LinkedList<E> {
        private int limit;

        public LimitedQueue(int limit) {
            this.limit = limit;
        }

        @Override
        public boolean add(E o) {
            super.add(o);
            while (size() > limit) {
                super.remove();
            }
            return true;
        }
    }
}
