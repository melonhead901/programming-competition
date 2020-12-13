package aoc20;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day13 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int time = in.nextInt();
        in.nextLine();
        List<Integer> numsLine = Arrays.stream(in.nextLine().split(","))
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
