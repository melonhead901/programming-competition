package aoc21;


import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

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
        CharCounter counter = new CharCounter();
        Map<String, Long> patternCounts = new HashMap<>();
        for (int i = 0; (i + 1) < pattern.length(); i++) {
            String key = pattern.substring(i, i + 2);
            increment(patternCounts, key, 1);
        }
        int iterations = 40;
        for (int i = 0; i < iterations; i++) {
            Map<String, Long> newCounts = new HashMap<>();
            for (Map.Entry<String, Long> entry : patternCounts.entrySet()) {
                char newChar = rules.get(entry.getKey());
                String useFirst = String.format("%s%s", entry.getKey().charAt(0), newChar);
                increment(newCounts, useFirst, entry.getValue());
                String useSecond = String.format("%s%s", newChar, entry.getKey().charAt(1));
                increment(newCounts, useSecond, entry.getValue());
            }
            patternCounts = newCounts;
            System.out.println(patternCounts);
        }
        patternCounts.forEach((k, v) -> counter.increment(k.charAt(0), v));
        patternCounts.forEach((k, v) -> counter.increment(k.charAt(1), v));
        counter.divideBy2();
        counter.increment(pattern.charAt(0));
        counter.increment(pattern.charAt(pattern.length() - 1));
        System.out.println(counter);
        System.out.println(counter.difference());

    }

    static <K> void increment(Map<K, Long> counts, K key, long incrementAmount) {
        counts.put(key, counts.getOrDefault(key, 0L) + incrementAmount);
    }

    private static void queueApproach(String pattern, Map<String, Character> rules, CharCounter counter) {
        LinkedList<Character> linkedList = new LinkedList<>();
        for (char c : pattern.toCharArray()) {
            linkedList.addLast(c);
        }
        int iterDepth = 0;
        int iterations = 2;
        while (linkedList.size() >= 2) {
            char first = linkedList.removeFirst();
            counter.increment(first);
            char second = linkedList.peekFirst();
            Character next = rules.get(String.format("%s%s", first, second));
            linkedList.addFirst(next);
            iterDepth++;
            if (iterDepth > iterations) {
                linkedList.removeFirst();
                iterDepth = 0;
            }
        }
        counter.increment(linkedList.removeFirst());
    }

    private static char exceuteDescension(int iterations, char last, char curr, CharCounter counter, Map<String, Character> rules) {
        counter.increment(last);
        if (iterations <= 0) {
            return curr;
        }
        char next = rules.get(String.format("%s%s", last, curr));
        return exceuteDescension(iterations - 1, next, curr, counter, rules);
    }

    static class CharCounter {
        public Map<Character, Long> counter;

        public CharCounter() {
            this(new HashMap<>());
        }

        public CharCounter(HashMap<Character, Long> newF) {
            this.counter = newF;
        }


        void increment(char c, long l) {
            System.out.print(c);
            this.counter.put(c, counter.getOrDefault(c, 0L) + l);
        }

        void increment(char c) {
            increment(c, 1L);
        }

        @Override
        public String toString() {
            return counter.toString();
        }

        public long difference() {
            long min = counter.values().stream().min(Comparator.naturalOrder()).get();
            long max = counter.values().stream().max(Comparator.naturalOrder()).get();
            return max - min;
        }

        public void divideBy2() {
            Set<Character> chars = this.counter.keySet();
            chars.forEach(c -> counter.put(c, counter.get(c) / 2));
        }
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
