package aoc21;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Day7 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        Scanner numScanner = new Scanner(line).useDelimiter(",");
        List<Integer> nums = new ArrayList<>();
        while (numScanner.hasNext()) {
            nums.add(numScanner.nextInt());
        }
        int min = nums.stream().min(Comparator.naturalOrder()).get();
        int max = nums.stream().max(Comparator.naturalOrder()).get();
        int minVal = min;
        int minDist = Integer.MAX_VALUE;
        for (int i = min; i <= max; i++) {
            int distanceSum = distanceToPosition(i, nums);
            if (distanceSum < minDist) {
                minDist = distanceSum;
                minVal = i;
            }
        }
        System.out.printf("%s:  %s\n", minVal, minDist);
    }

    private static int distanceToPosition(int i, List<Integer> nums) {
        return nums.stream().map(n -> {
            int distance = Math.abs(n - i);
            return (distance * (distance + 1)) / 2;
        }).reduce(0, Integer::sum);
    }
}
