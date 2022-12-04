package aoc22;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int sum = 0;
        while (in.hasNextLine()) {
            String line = in.nextLine();
            Set<Character> firstHalf = new HashSet<>();
            Set<Character> secondHalf = new HashSet<>();
            for (int i = 0; i < line.length() / 2; i++) {
                firstHalf.add(line.charAt(i));
            }
            for (int i = line.length() / 2; i < line.length(); i++) {
                secondHalf.add(line.charAt(i));
            }
            firstHalf.retainAll(secondHalf);
            char intersection = firstHalf.stream().findFirst().get();
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