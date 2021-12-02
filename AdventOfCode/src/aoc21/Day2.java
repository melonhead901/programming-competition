package aoc21;

import java.util.Scanner;

public class Day2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int xPos = 0;
        int depth = 0;
        while (in.hasNext()){
            String command = in.next();
            int num = in.nextInt();
            switch (command) {
                case "forward":
                    xPos += num;
                    break;
                case "down":
                    depth += num;
                    break;
                case "up":
                    depth -= num;
                    break;
                default:
                    throw new IllegalArgumentException(command);
            }
            System.out.printf("x: %s depth: %s, times: %s\n", xPos, depth, xPos*depth);
        }
    }
}
