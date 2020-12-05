package aoc20;

import java.util.Scanner;

public class Day5 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int maxSeen = 0;
        while (in.hasNextLine()) {
            String line = in.nextLine();
            if (line.isEmpty()) {
                break;
            }
            int id = getSeatId(line);
            maxSeen = Math.max(id, maxSeen);
        }
        System.out.println(maxSeen);
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
