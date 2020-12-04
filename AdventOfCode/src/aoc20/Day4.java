package aoc20;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        boolean valid = validateWords(words);
        return valid;
    }

    private static boolean part1(List<String> words) {
        Set<String> prefixes = new HashSet<>();
        for (String word : words) {
            prefixes.add(word.split(":")[0]);
        }
        return isSuccessful(prefixes);
    }

    private static boolean validateWords(List<String> words) {
        /*
        byr (Birth Year) - four digits; at least 1920 and at most 2002.
        iyr (Issue Year) - four digits; at least 2010 and at most 2020.
        eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
        hgt (Height) - a number followed by either cm or in:
        If cm, the number must be at least 150 and at most 193.
        If in, the number must be at least 59 and at most 76.
        hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
        ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
        pid (Passport ID) - a nine-digit number, including leading zeroes.
        cid (Country ID) - ignored, missing or not.
         */
        int countValid = 0;
        Map<String, String> mapping = new HashMap<>(words.size());
        for (String word : words) {
            String[] splits = word.split(":");
            mapping.put(splits[0], splits[1]);
        }
        for (Map.Entry<String, String> entry : mapping.entrySet()) {
            String value = entry.getValue();
            switch (entry.getKey()) {
                case "byr":
                    if (validateInt(value, 1920, 2002)) {
                        countValid++;
                    }
                    break;
                case "iyr":
                    if (validateInt(value, 2010, 2020)) {
                        countValid++;
                    }
                    break;
                case "eyr":
                    if (validateInt(value, 2020, 2030)) {
                        countValid++;
                    }
                    break;
                case "hgt":
                    if (value.endsWith("cm")) {
                        if (validateInt(value.substring(0, 3), 150, 193)) {
                            countValid++;
                        }
                    } else if (value.endsWith("in")) {
                        if (validateInt(value.substring(0, 2), 59, 76)) {
                            countValid++;
                        }
                    }
                    break;
                case "hcl":
                    if (regexMatch(value, "#[0-9a-f]{6}")) {
                        countValid++;
                    }
                    break;
                case "ecl":
                    if (ImmutableList.of("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
                            .contains(value)) {
                        countValid++;
                    }
                    break;
                case "pid":
                    if (regexMatch(value, "[0-9]{9}")) {
                        countValid++;
                    }
                    break;
            }
        }
        if (!(countValid >= 7)) {
            // System.err.println(String.format("%s: %s", countValid, words));
        }
        return countValid >= 7;
    }

    private static boolean regexMatch(String value, String regex) {
        Pattern pattern = Pattern.compile("^" + regex + "$");
        Matcher matcher = pattern.matcher(value);
        boolean result = matcher.find();
        return result;
    }

    private static boolean validateInt(String value, int min, int max) {
        try {
            int val = Integer.parseInt(value);
            return (val >= min) && (val <= max);
        } catch (NumberFormatException ignored) {
        }
        return false;
    }

    private static boolean isSuccessful(Set<String> prefixes) {
        Set<String> requiredPrefixes = new HashSet<>(ImmutableSet.of("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"));
        prefixes.retainAll(requiredPrefixes);
        return prefixes.size() >= 7;
    }
}
