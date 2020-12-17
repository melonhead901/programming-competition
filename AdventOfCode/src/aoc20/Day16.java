package aoc20;

import com.google.common.collect.Iterables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Day16 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String line = in.nextLine();

        List<Field> fieldList = new ArrayList<>();
        while (!line.isEmpty()) {
            fieldList.add(Field.createFromStr(line));
            line = in.nextLine();
        }

        // your ticket
        in.nextLine();
        List<Integer> yourTicket;
        line = in.nextLine();
        yourTicket = Arrays.stream(line.split(",")).map(Integer::parseInt).collect(Collectors.toList());

        in.nextLine();
        in.nextLine();

        List<List<Integer>> nearbyTickets = new ArrayList<>();
        line = in.nextLine();
        while (!line.isEmpty()) {
            nearbyTickets.add(
                    Arrays.stream(line.split(",")).map(Integer::parseInt).collect(Collectors.toList()));
            line = in.nextLine();
        }

        Map<Integer, Set<Field>> fieldPossibilities = new HashMap<>();
        for (int i = 0; i < yourTicket.size(); i++) {
            fieldPossibilities.put(i, new HashSet<>(fieldList));
        }

        List<List<Integer>> validTickets = getValidTickets(fieldList, nearbyTickets);
        for (List<Integer> ticket : validTickets) {
            for (int i = 0; i < ticket.size(); i++) {
                int finalI = i;
                Set<Field> possibleFields = fieldList.stream().filter(f -> f.isValid(ticket.get(finalI))).collect(Collectors.toSet());
                fieldPossibilities.get(i).retainAll(possibleFields);
            }
        }


        Map<Integer, Field> simplified = simplify(fieldPossibilities);


        long factor = 1;
        for (Map.Entry<Integer, Field> x : simplified.entrySet()) {
            if (x.getValue().isDeparture()) {
                System.out.println(x.getValue());
                factor *= yourTicket.get(x.getKey());
            }
        }

        System.out.println(factor);

    }

    private static Map<Integer, Field> simplify(Map<Integer, Set<Field>> fieldPossibilities) {
        Map<Integer, Field> simplified = new HashMap<>();
        while (simplified.size() < fieldPossibilities.size()) {
            for (int i : fieldPossibilities.keySet()) {
                Set<Field> vals = fieldPossibilities.get(i);
                if (vals.size() == 1) {
                    Field val = Iterables.getOnlyElement(vals);
                    simplified.put(i, val);
                    fieldPossibilities.values().forEach(fp -> fp.remove(val));
                }
            }
        }

        return simplified;
    }

    private static List<List<Integer>> getValidTickets(List<Field> fieldList, List<List<Integer>> nearbyTickets) {
        List<List<Integer>> validTickets = new ArrayList<>();
        for (List<Integer> ticket : nearbyTickets) {
            boolean allValid = true;
            for (int fieldValue : ticket) {
                boolean valid = false;
                for (Field f : fieldList) {
                    if (f.isValid(fieldValue)) {
                        valid = true;
                        break;
                    }
                }
                if (!valid) {
                    System.err.println(fieldValue);
                    allValid = false;
                    break;
                }
            }
            if (allValid) {
                validTickets.add(ticket);
            }
        }
        return validTickets;
    }

    static class Field {
        final List<Range> ranges;
        final String name;

        public Field(List<Range> ranges, String name) {
            this.ranges = ranges;
            this.name = name;
        }

        public static Field createFromStr(String str) {
            String[] parts = str.split(": ");
            List<Range> ranges = Arrays.stream(parts[1].split(" or "))
                    .map(Range::createFromStr)
                    .collect(Collectors.toList());
            return new Field(ranges, parts[0]);
        }

        boolean isValid(int n) {
            return ranges.stream().anyMatch(r -> r.isInRange(n));
        }

        @Override
        public String toString() {
            return "Field{" +
                    "name='" + name + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Field field = (Field) o;

            if (!ranges.equals(field.ranges)) return false;
            return name.equals(field.name);
        }

        @Override
        public int hashCode() {
            int result = ranges.hashCode();
            result = 31 * result + name.hashCode();
            return result;
        }

        public boolean isDeparture() {
            return name.contains("departure");
        }
    }

    static class Range {
        final int min, max;

        public Range(int min, int max) {
            this.min = min;
            this.max = max;
        }

        public boolean isInRange(int n) {
            return n >= min && n <= max;
        }

        public static Range createFromStr(String str) {
            String[] splits = str.split("-");
            return new Range(Integer.parseInt(splits[0]), Integer.parseInt(splits[1]));
        }
    }
}
