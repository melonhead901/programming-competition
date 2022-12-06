package aoc22;

import java.util.HashSet;
import java.util.Scanner;

public class Day6 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String line = in.nextLine();
            for (int i = 0; i < line.length() - 4; i++) {
                String sub = line.substring(i, i + 4);
                HashSet<Character> set = new HashSet<>();
                for (int ii = 0; ii < 4; ii++) {
                    set.add(sub.charAt(ii));
                }
                if (set.size() == 4) {
                    System.out.println(i + 4);
                    break;
                }
            }
            System.out.println(line);
        }
    }
}
