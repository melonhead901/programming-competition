package aoc21;

import com.google.common.collect.Iterables;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

public class Day8 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in).useDelimiter("\n");
        List<Entry> entries = new ArrayList<>();
        int sum = 0;
        while (in.hasNextLine()) {
            Entry e = new Entry(in.nextLine());
            entries.add(e);
            sum += e.digits();
        }
        System.out.println(sum);

    }

    private static class Entry {
        private List<String> displayed;
        private List<String> digits;
        private List<String> all;

        public Entry(String line) {
            Scanner scanner = new Scanner(line).useDelimiter(" \\| ");
            displayed = new ArrayList<>();
            Scanner displayedScanner = new Scanner(scanner.next());
            while (displayedScanner.hasNext()) {
                displayed.add(displayedScanner.next());
            }
            Scanner part2Scanner = new Scanner(scanner.next());
            digits = new ArrayList<>();
            while (part2Scanner.hasNext()) {
                digits.add(part2Scanner.next());
            }
            all = new ArrayList<>(displayed);
            all.addAll(digits);
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "part1='" + displayed + '\'' +
                    ", part2='" + digits + '\'' +
                    '}';
        }

        public String rightSideDigits() {
            return all.stream().filter(s -> s.length() == 2).findAny().get();
        }

        public String seven() {
            return all.stream().filter(s -> s.length() == 7).findAny().get();
        }

        public String getN(int n) {
            return all.stream().filter(s -> s.length() == n).findAny().get();
        }

        public String topDigit() {
            String one = rightSideDigits();
            String seven = seven();
            Set<Character> ones = new HashSet<>();
            for (char c : one.toCharArray()) {
                ones.add(c);
            }
            Set<Character> sevens = new HashSet<>();
            for (char c : seven.toCharArray()) {
                sevens.add(c);
            }
            sevens.removeAll(ones);
            return Iterables.getOnlyElement(sevens) + "";
        }

        public Optional<Integer> decodeDigit(int pos) {
            String digit = digits.get(pos);
            if (digit.length() == 2) {
                return Optional.of(1);
            } else if (digit.length() == 3) {
                return Optional.of(7);
            } else if (digit.length() == 4) {
                return Optional.of(4);
            } else if (digit.length() == 5) {
                if (stringContains(digit, this.rightSideDigits())) {
                    return Optional.of(3);
                }
                String four = getN(4);
                int digitsInCommon = digitsInCommon(digit, four);
                if (digitsInCommon == 2) {
                    return Optional.of(2);
                } else if (digitsInCommon == 3) {
                    return Optional.of(5);
                }
                return Optional.empty();
            } else if (digit.length() == 6) {
                if (!stringContains(digit, this.rightSideDigits())) {
                    return Optional.of(6);
                }
                // 0, 9
                String four = getN(4);
                int digitsInCommon = digitsInCommon(digit, four);
                if (digitsInCommon == 4) {
                    return Optional.of(9);
                } else if (digitsInCommon == 3) {
                    return Optional.of(0);
                }
                return Optional.empty();
            } else if (digit.length() == 7) {
                return Optional.of(8);
            }
            return Optional.empty();
        }

        private int digitsInCommon(String digit, String four) {
            Set<Character> digitSet = makeSet(digit);
            Set<Character> fourSet = makeSet(four);
            digitSet.retainAll(fourSet);
            return digitSet.size();
        }

        private Set<Character> makeSet(String digit) {
            Set<Character> set = new HashSet<>();
            for (Character c : digit.toCharArray()) {
                set.add(c);
            }
            return set;
        }

        private boolean stringContains(String digit, String rightSideDigits) {
            Set<Character> digitsSet = new HashSet<>();
            char[] digits = digit.toCharArray();
            for (char c : digits) {
                digitsSet.add(c);
            }
            for (char c : rightSideDigits.toCharArray()) {
                if (!digitsSet.contains(c)) {
                    return false;
                }
            }
            return true;
        }

        public long countUniqueDigits() {
            return digits.stream().filter(s -> {
                int length = s.length();
                return (length == 2) || (length == 4) || (length == 7) || (length == 3);
            }).count();
        }

        public int digits() {
            int result = 0;
            for (int i = 0; i < this.digits.size(); i++) {
                result *= 10;
                result += this.decodeDigit(i).get();
            }
            return result;
        }
    }
}
