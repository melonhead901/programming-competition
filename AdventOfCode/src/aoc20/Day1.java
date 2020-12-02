package aoc20;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public class Day1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<Integer> seenNumbers = new ArrayList<>();
        while (in.hasNextInt()) {
            seenNumbers.add(in.nextInt());
        }
        Set<Pair> targets = new HashSet<>();
        for (int i = 0; i < seenNumbers.size(); i++) {
            for (int j = i + 1; j < seenNumbers.size(); j++) {
                targets.add(new Pair(seenNumbers.get(i), seenNumbers.get(j)));
            }
        }
        for (int val : seenNumbers) {
            for (Pair p : targets) {
                if (p.thirdMatches(val)) {
                    System.out.println(p.result(val));
                    return;
                }
            }

        }

    }
}

class Pair {
    int x;
    int y;


    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    boolean thirdMatches(int z) {
        return (2020 - x - y - z) == 0;
    }

    int result(int z) {
       return x * y * z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return x == pair.x &&
                y == pair.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}