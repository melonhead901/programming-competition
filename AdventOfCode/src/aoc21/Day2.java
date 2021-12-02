package aoc21;

import java.util.Scanner;

public class Day2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Day2State state = new Day2State(0, 0, 0);
        while (in.hasNext()){
            String command = in.next();
            int num = in.nextInt();
            switch (command) {
                case "forward":
                    state = state.forward(num);
                    break;
                case "down":
                    state = state.down(num);
                    break;
                case "up":
                    state = state.up(num);
                    break;
                default:
                    throw new IllegalArgumentException(command);
            }
            state.printResult();
        }
    }

    static class Day2State {
        private final int xPos;
        private final int depth;
        private final int aim;

        public Day2State(int xPos, int depth, int aim) {
            this.xPos = xPos;
            this.depth = depth;
            this.aim = aim;
        }

        public Day2State down(int x) {
            return new Day2State(xPos, depth, aim + x);
        }

        public Day2State up(int x) {
            return new Day2State(xPos, depth, aim - x);
        }

        public Day2State forward(int x) {
            return new Day2State(xPos + x, depth + (aim * x), aim);
        }

        private void printResult() {
            System.out.println(depth * xPos);

        }
    }
}
