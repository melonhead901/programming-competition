package aoc20;

import com.google.common.collect.ImmutableSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Day4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int count = 0;
        while (in.hasNextLine()) {
            if (processCase(in)) {
                count++;
            }
            System.out.println(count);
        }
    }

    private static boolean processCase(Scanner in) {
        List<String> words = new ArrayList<>();
        String line = in.nextLine();
        while (!line.isEmpty()) {
            words.addAll(Arrays.asList(line.split(" ")));
            line = in.nextLine();
        }
        Set<String> prefixes = new HashSet<>();
        for (String word : words) {
            prefixes.add(word.split(":")[0]);
        }
        return isSuccessful(prefixes);
    }

    private static boolean isSuccessful(Set<String> prefixes) {
        Set<String> requiredPrefixes = new HashSet<>(ImmutableSet.of("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"));
        prefixes.retainAll(requiredPrefixes);
        System.err.println(prefixes);
        return prefixes.size() >= 7;
    }
}
