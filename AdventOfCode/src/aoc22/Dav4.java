package aoc22;

import java.util.Scanner;

public class Dav4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int containedCount = 0;

        while(in.hasNextLine()) {
            String line = in.nextLine();
            String[] pairs = line.split(",");
            String[] firstPair = pairs[0].split("-");
            int pair0Start = Integer.parseInt(firstPair[0]);
            int pair0End = Integer.parseInt(firstPair[1]);
            String[] secondPair = pairs[1].split("-");
            int pair1Start = Integer.parseInt(secondPair[0]);
            int pair1End = Integer.parseInt(secondPair[1]);
            if (pair0Start <= pair1Start && pair0End >= pair1End) {
                containedCount++;
            } else if (pair1Start <= pair0Start && pair1End >= pair0End) {
                containedCount++;
            }
        }
        System.out.println(containedCount);
    }
}
