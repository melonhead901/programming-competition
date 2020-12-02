package aoc20;

import java.util.Arrays;
import java.util.Scanner;

public class Day2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int count = 0;
        while (in.hasNextLine()) {
            String line = in.nextLine();
            if (line.isEmpty()) {
                break;
            }
            boolean valid = processCase(line);
            if (valid) {
                count++;
            }
        }
        System.out.println(count);
    }

    private static boolean processCase(String line) {
        Scanner wordScanner = new Scanner(line);
        String count = wordScanner.next();
        String[] countSplits = count.split("-");
        int min = Integer.parseInt(countSplits[0]);
        int max = Integer.parseInt(countSplits[1]);
        String character = wordScanner.next();
        char target = character.charAt(0);
        String password = wordScanner.next();
        int charCount = 0;
        for (char c : password.toCharArray()) {
            if (c == target) {
                charCount++;
            }
        }
        System.out.println(password);
        return (charCount >= min) && (charCount <= max);
    }
}
