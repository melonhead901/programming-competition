package aoc21;

import java.util.Objects;
import java.util.Scanner;

public class Day3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String first = "000000011010";
        // String first = "00000";
        int[] onesCounts = new int[first.length()];
        int numLines = 0;
        while (in.hasNext()) {
            String line = in.next();
            if (line.startsWith("Z")) {
                break;
            }
            if (Objects.equals(line, "Z")) {
                break;
            }
            for (int i =0; i < line.length(); i++) {
                if (line.charAt(i) == '1') {
                    onesCounts[i]++;
                }
            }
            numLines++;
        }
        int gamma = 0;
        int epsilon = 0;
        for (int i = 0; i < onesCounts.length; i++) {
            gamma *= 2;
            epsilon *= 2;
            if (onesCounts[i] > (numLines / 2)) {
                gamma++;
            } else  {
                epsilon++;
            }
        }
        System.out.printf("gamma: %s, epsilon: %s, result: %s\n", gamma, epsilon, gamma*epsilon);
    }

}
