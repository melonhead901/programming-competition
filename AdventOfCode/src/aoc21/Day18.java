package aoc21;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day18 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<Pair> pairs = new ArrayList<>();
        while (in.hasNextLine()) {
            pairs.add(parsePair(in.nextLine()));

        }

    }

    private static Pair parsePair(String nextLine) {
        int commaIndex = nextLine.indexOf(',');
        if (nextLine.charAt(0) == '[') {

        }
        return null;
    }

    static class Pair {
        int leftVal;
        Pair leftPair;
        int rightVal;
        Pair rightPair;

        @Override
        public String toString() {
            return String.format("[%s, %s]", (leftPair != null) ? leftPair.toString() : leftVal, (rightPair != null) ? rightPair.toString() : rightVal);
        }
    }

}
