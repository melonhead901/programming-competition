package aoc22;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day10 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int regVal = 1;
        int cycle = 1;
        Map<Integer, Integer> historicalRegVals = new HashMap<>();
        while (in.hasNextLine()) {
            printAndStoreCycle(regVal, cycle, historicalRegVals);
            String line = in.nextLine();
            String[] vals = line.split(" ");
            if (vals[0].equals("noop")) {
                cycle++;
            } else {
                cycle++;
                printAndStoreCycle(regVal, cycle, historicalRegVals);
                cycle++;
                regVal += Integer.parseInt(vals[1]);
            }
        }
        printAndStoreCycle(regVal, cycle, historicalRegVals);
        int total = 0;
        for (int i = 20; historicalRegVals.containsKey(i); i += 40) {
            int val = i * historicalRegVals.get(i);
            System.out.printf("%s * %s = %s\n", i, historicalRegVals.get(i), val);
            total += val;
        }
        System.out.println(total);
    }

    private static void printAndStoreCycle(int regVal, int cycle, Map<Integer, Integer> historicalRegVals) {
        System.out.printf("%s: %s\n", cycle, regVal);
        historicalRegVals.put(cycle, regVal);
    }
}
