package adventofcode_2015;

import com.google.common.collect.ImmutableSet;

import java.util.Scanner;
import java.util.Set;

public class NiceString {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            int count = 0;
            while (in.hasNext()) {
                final String line = in.nextLine();
                boolean nice = processCase(line);
                System.out.println(line + " nice? " + nice);
                if (nice) {
                    count++;
                }
            }
            System.out.println(count);
        }
    }

    private static boolean processCase(String nextLine) {
//        return hasThreeVowels(nextLine) && hasTwoConsecutiveLetters(nextLine) && noIllegalSubstr(nextLine);
        return meetsRepeatSpaced(nextLine) && containsValidOverlap(nextLine);
    }

    private static boolean containsValidOverlap(String nextLine) {
        for (int i = 0; i < nextLine.length() - 2; i++) {
            String val = nextLine.substring(i, i+2);
            if (nextLine.substring(i+2).contains(val)) {
                return true;
            }
        }
        return false;
    }

    private static boolean meetsRepeatSpaced(String nextLine) {
        for (int i = 2; i < nextLine.length(); i++) {
            if (nextLine.charAt(i) == nextLine.charAt(i - 2)) {
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unused")
    private static boolean noIllegalSubstr(String nextLine) {
        return !nextLine.contains("ab") && !nextLine.contains("cd") && !nextLine.contains("pq")
                && !nextLine.contains("xy");
    }

    @SuppressWarnings("unused")
    private static boolean hasTwoConsecutiveLetters(String nextLine) {
        for (int i = 1; i < nextLine.length(); i++) {
            if (nextLine.charAt(i) == nextLine.charAt(i - 1)) {
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unused")
    private static boolean hasThreeVowels(String nextLine) {
        int count = 0;
        Set<Character> vowels = ImmutableSet.of('a', 'e', 'i', 'o', 'u');
        for (char c : nextLine.toCharArray()) {
            if (vowels.contains(c)) {
                count++;
            }
        }
        return count >= 3;
    }
}
