package aoc22;

import java.util.LinkedList;
import java.util.Scanner;

public class Day1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int elfMax = 0;
        while (in.hasNextLine()) {
            int elfTotal = 0;
            String next = "0";
            while (!next.equals("")) {
                int val = Integer.parseInt(next);
                elfTotal += val;
                next = in.nextLine();
            }
            elfMax = Math.max(elfMax, elfTotal);
        }
        System.out.println(elfMax);
    }
}
