package aoc20;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day13 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int time = in.nextInt();
        in.nextLine();
        final String[] values = in.nextLine().split(",");
        Map<Integer, Integer> schedule = new LinkedHashMap<>();
        for (int i = 0; i < values.length; i++) {
            if (!values[i].equals("x")) {
                schedule.put(Integer.parseInt(values[i]), i);
            }
        }
        System.out.println(schedule);

        part2(schedule);

        // part1(time, values);
    }

    private static void part2(Map<Integer, Integer> schedule) {
        List<Integer> ids = new ArrayList<>(schedule.keySet());
        long multFactor = ids.get(0);
        long soFar = ids.get(0);
        for (int i = 1; i < ids.size(); i++) {
            int mult = ids.get(i);
            System.out.println("mult of " + mult);
            int desiredMod = mult - schedule.get(ids.get(i));
            while (desiredMod < 0) {
                desiredMod += mult;
            }
            System.out.println("desired mod of " + desiredMod);
            while ((soFar % mult) != desiredMod) {
                soFar += multFactor;
            }
            System.out.println("satisfied at " + soFar);
            multFactor *= mult;
            System.out.println("mult factor to " + multFactor);
        }
        System.out.println(soFar);
    }

    private static boolean isDone(long computedVal, Map<Integer, Integer> schedule) {
        for (Map.Entry<Integer, Integer> entry : schedule.entrySet()) {
            if ((computedVal % entry.getKey()) != entry.getValue()) {
                return false;
            }
        }
        return true;
    }

    private static long computeVal(long[] multiples, List<Integer> schedule) {
        long result = 0;
        for (int i = 0; i < multiples.length; i++) {
            result += multiples[i] * schedule.get(i);
        }
        return result;
    }

    private static void part1(int time, String[] values) {
        List<Integer> numsLine = Arrays.stream(values)
                .filter(s -> !"x".equals(s))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        final List<Integer> waits = numsLine.stream().map(n -> n - (time % n)).collect(Collectors.toList());
        int minIndex = 0;
        int minVal = waits.get(0);
        for (int i = 0; i < waits.size(); i++) {
            if (waits.get(i) < minVal) {
                minIndex = i;
                minVal = waits.get(i);
            }
        }
        System.out.println(numsLine.get(minIndex));
        System.out.println(waits.get(minIndex));
        System.out.println(numsLine.get(minIndex) * waits.get(minIndex));
    }
}
