package aoc21;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day14 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String pattern = in.nextLine();
        in.nextLine();
        Map<String, Character> rules = new HashMap<>();
        while (in.hasNextLine()) {
            String line = in.nextLine();
            rules.put(line.substring(0, 2), line.charAt(line.length() - 1));
        }
        int iterations = 40;
        for (int i = 1; i <= iterations; i++) {
            pattern = applyRules(pattern, rules);
            System.out.printf("%s: %s\n", i, pattern.length());
        }
        Map<Character, Long> frequencies = createFreqMap(pattern);
        List<Long> freqs = frequencies.values().stream().sorted().collect(Collectors.toList());
        System.out.println(freqs);
        System.out.println(freqs.get(freqs.size()-1) - freqs.get(0));


    }

    private static Map<Character, Long> createFreqMap(String pattern) {
        Map<Character, Long> result = new HashMap<>();
        for (char c : pattern.toCharArray()) {
            result.put(c, result.getOrDefault(c, 0L) + 1);
        }
        return result;
    }

    private static String applyRules(String pattern, Map<String, Character> rules) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; (i + 1) < pattern.length(); i++) {
            builder.append(pattern.charAt(i));
            if (rules.containsKey(pattern.substring(i, i + 2))) {
                builder.append(rules.get(pattern.substring(i, i + 2)));
            }
        }
        builder.append(pattern.charAt(pattern.length() - 1));
        return builder.toString();
    }

    private static class Rule {
        char leftChar;
        char rightChar;
        char produces;

        public Rule(char leftChar, char rightChar, char produces) {
            this.leftChar = leftChar;
            this.rightChar = rightChar;
            this.produces = produces;
        }
    }
}
