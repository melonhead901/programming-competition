package aoc21;

import java.util.LinkedList;
import java.util.Scanner;

public class Day1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        LinkedList<Integer> nums = new LinkedList<>();
        nums.add(in.nextInt());
        nums.add(in.nextInt());
        nums.add(in.nextInt());
        int sum = nums.stream().reduce(0, Integer::sum);
        int count = 0;
        while (in.hasNextInt()) {
            int val = in.nextInt();
            int removed = nums.remove(0);
            int newSum = sum + val - removed;
            if (newSum > sum) {
                count++;
            }
            sum = newSum;
            nums.add(val);
            System.out.println(count);
        }
    }
}
