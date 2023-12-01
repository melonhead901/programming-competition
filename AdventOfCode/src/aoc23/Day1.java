package aoc23;

import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

public class Day1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int total = 0;
        while (in.hasNextLine()) {
            int lineVal = processLine(in.nextLine());
            System.out.print("Line val " + lineVal + " ");
            total += lineVal;
            System.out.println("Total val " + total);
        }
    }

    static int processLine(String nextLine) {
        int first= 0;
        int last = 0;
        nextLine = wordProcess(nextLine);
        for (char c : nextLine.toCharArray() ) {
            if (Character.isDigit(c)) {
                first = Integer.parseInt(c + "");
                break;
            }
        }
        for (char c : StringUtils.reverse(nextLine).toCharArray()) {
            if (Character.isDigit(c)) {
                last = Integer.parseInt(c + "");
                break;
            }
        }
        return first * 10 + last;
    }

    static String wordProcess(String line) {
        String words[] = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "zero"};
        String digits[] = {"o1e", "t2o", "t3e", "f4r", "f5r", "s6x", "s7n", "e8t", "n9e", "z0o"};
        String oldline = "";
        while (!line.equals(oldline)) {
            for (int i = 0; i < words.length; i++) {
                line = line.replaceAll(words[i], digits[i]);
            }
            oldline = line;
        }
        return line;
    }
}
