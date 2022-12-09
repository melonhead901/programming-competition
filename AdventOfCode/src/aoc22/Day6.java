package aoc22;

import java.util.HashSet;
import java.util.Scanner;

public class Day6 {

    public static final int LEN = 14;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String line = in.nextLine();
            for (int i = 0; i < line.length() - LEN; i++) {
                String sub = line.substring(i, i + LEN);
                HashSet<Character> set = new HashSet<>();
                for (int ii = 0; ii < LEN; ii++) {
                    set.add(sub.charAt(ii));
                }
                if (set.size() == LEN) {
                    System.out.println(i + LEN);
                    break;
                }
            }
        }
    }
}
