package aoc21;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day8 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in).useDelimiter("\n");
        List<Entry> entries = new ArrayList<>();
        while (in.hasNextLine()) {
            entries.add(new Entry(in.nextLine()));
        }
        long count = entries.stream().map(Entry::countUniqueDigits).reduce(0L, Long::sum);
        System.out.println(count);

    }

    private static class Entry {
        private String part1;
        private List<String> part2;

        public Entry(String line) {
            Scanner scanner = new Scanner(line).useDelimiter(" \\| ");
            this.part1 = scanner.next();
            Scanner part2Scanner = new Scanner(scanner.next());
            part2 = new ArrayList<>();
            while (part2Scanner.hasNext()) {
                part2.add(part2Scanner.next());
            }
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "part1='" + part1 + '\'' +
                    ", part2='" + part2 + '\'' +
                    '}';
        }

        public long countUniqueDigits() {
            return part2.stream().filter(s -> {
                int length = s.length();
                return (length == 2) || (length == 4) || (length == 7) || (length == 3);
            }).count();
        }
    }
}
