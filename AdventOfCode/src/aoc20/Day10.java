package aoc20;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Day10 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<Integer> ints = new ArrayList<>();
        while(in.hasNextLine()) {
            String line = in.nextLine();
            if (line.isEmpty()) {
                break;
            }
            ints.add(Integer.parseInt(line));
        }
        Collections.sort(ints);
        int oneJumps = 0;
        int threeJumps = 0;
        int curr = 0;
        for (int i = 0; i < ints.size(); i++) {
            int next = ints.get(i);
            int diff = next - curr;
            if (diff == 1) {
                System.out.printf("%s to %s is 1\n", curr, next);
                oneJumps++;
            } else if (diff == 3) {
                threeJumps++;
                System.out.printf("%s to %s is 3\n", curr, next);
            } else {
                throw new IllegalStateException();
            }
            curr = next;
        }
        System.out.println(oneJumps);
        System.out.println(threeJumps);
        System.out.println(oneJumps * (threeJumps + 1));
    }
}
