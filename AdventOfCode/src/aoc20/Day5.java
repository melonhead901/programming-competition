package aoc20;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day5 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Set<Integer> seenNums = new HashSet<>();
        while (in.hasNextLine()) {
            String line = in.nextLine();
            if (line.isEmpty()) {
                break;
            }
            seenNums.add(getSeatId(line));
        }
        int min = seenNums.stream().min(Comparator.naturalOrder()).get();
        int max = seenNums.stream().max(Comparator.naturalOrder()).get();
        for (int i = min; i <= max; i++) {
            if (!seenNums.contains(i)) {
                System.out.println(i);
            }
        }
    }

    private static int getSeatId(String line) {
        String rowSection = line.substring(0, 7);
        String rowSectionAsBin = rowSection.replaceAll("F", "0").replaceAll("B", "1");
        int rowNumber = Integer.parseInt(rowSectionAsBin, 2);
        String columnSection = line.substring(7, 10);
        String columnSectionAsBin = columnSection.replaceAll("R", "1").replaceAll("L", "0");
        int columnNumber = Integer.parseInt(columnSectionAsBin, 2);
        return (rowNumber * 8) + columnNumber;
    }
}
