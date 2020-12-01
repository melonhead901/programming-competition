package aoc20;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Set<Integer> seenNumbers = new HashSet<>();
        while (in.hasNextInt()) {
            int n = in.nextInt();
            if (seenNumbers.contains(2020 - n)) {
                System.out.println(n);
                System.out.println((2020 - n) * n);
            }
            seenNumbers.add(n);
        }
    }
}
