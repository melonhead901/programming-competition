package aoc22;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Day1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<Integer> snacks = new ArrayList<>();
        while (in.hasNextLine()) {
            int elfTotal = 0;
            String next = "0";
            while (!next.equals("")) {
                int val = Integer.parseInt(next);
                elfTotal += val;
                next = in.nextLine();
            }
            snacks.add(elfTotal);
        }
        System.out.println(snacks.stream().sorted(Comparator.reverseOrder()).limit(3).reduce(0, Integer::sum));
    }
}
