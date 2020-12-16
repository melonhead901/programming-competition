package aoc20;

import javax.xml.crypto.dsig.spec.XPathFilterParameterSpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
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
           nearbyTickets.add(Arrays.stream(line.split(",")).map(Integer::parseInt).collect(Collectors.toList()));
           line = in.nextLine();
       }

       long total = 0;
       for (List<Integer> ticket : nearbyTickets) {
           for (int fieldValue : ticket) {
               boolean valid = false;
               for (Field f : fieldList) {
                   if (f.isValid(fieldValue)) {
                       valid = true;
                       continue;
                   }
               }
               if (!valid) {
                   System.err.println(fieldValue);
                   total += fieldValue;
               }
           }
       }
       System.out.println(total);
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
