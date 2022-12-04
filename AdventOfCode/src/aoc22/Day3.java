package aoc22;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int sum = 0;
        while (in.hasNextLine()) {
            String line1 = in.nextLine();
            String line2 = in.nextLine();
            String line3 = in.nextLine();

            Set<Character> bag1 = new HashSet<>();
            for (char c : line1.toCharArray()) {
                bag1.add(c);
            }
            Set<Character> bag2 = new HashSet<>();
            for (char c : line2.toCharArray()) {
                bag2.add(c);
            }
            Set<Character> bag3 = new HashSet<>();
            for (char c : line3.toCharArray()) {
                bag3.add(c);
            }
            bag1.retainAll(bag2);
            bag1.retainAll(bag3);
            char intersection = bag1.stream().findFirst().get();
            int val = (int) intersection - 'a' + 1;
            if (Character.isUpperCase(intersection)) {
                val += 58;
            }
            System.out.println(intersection + " " + val);
            sum += val;
        }
        System.out.println(sum);
    }
}