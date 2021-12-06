package aoc21;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day6 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        Scanner numScanner = new Scanner(line).useDelimiter(",");
        List<Integer> values = new ArrayList<>();
        while (numScanner.hasNext()) {
            values.add(numScanner.nextInt());
        }
        for (int i = 1; i <= 256; i++) {
            values = createNewValues(values);
            // System.out.printf("%s: %s %s \n", i, values.size(), values);
            System.out.printf("%s: %s\n", i, values.size());
        }
    }

    private static List<Integer> createNewValues(List<Integer> values) {
        List<Integer> result = new ArrayList<>();
        for (int i : values) {
            if (i == 0) {
                result.add(8);
                result.add(6);
            } else {
                result.add(i-1);
            }
        }
        return result;
    }
}
