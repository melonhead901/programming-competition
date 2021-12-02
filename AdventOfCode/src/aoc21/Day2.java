package aoc21;

import java.util.Scanner;

public class Day2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long xPos = 0;
        long depth = 0;
        long aim = 0;
        while (in.hasNext()){
            String command = in.next();
            int num = in.nextInt();
            switch (command) {
                case "forward":
                    xPos += num;
                    depth += aim*num;
                    break;
                case "down":
                    aim += num;
                    break;
                case "up":
                    aim -= num;
                    break;
                default:
                    throw new IllegalArgumentException(command);
            }
            System.out.printf("x: %s depth: %s, times: %s\n", xPos, depth, xPos*depth);
        }
    }
}
