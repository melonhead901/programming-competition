package adventofcode;

import java.util.Scanner;

/**
 * Day 6.
 */
public class ChristmasLights {
    public static void main(String[] args) {
        int[][] lights = new int[1000][];
        for (int i = 0; i < lights.length; i++) {
            lights[i] = new int[1000];
        }
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String line = in.nextLine();
            processLine(lights, line);
            System.out.println(countLights(lights));
        }

    }

    private static int countLights(int[][] lights) {
       int count = 0;
        for (int[] lightArray : lights) {
            for (int light : lightArray) {
                    count+=light;
            }
        }
        return count;
    }

    private static void processLine(int[][] lights, String line) {
        String[] split = line.split(" ");
        switch (split[0]) {
            case "turn":
                int delta;
                switch (split[1]) {
                    case "on":
                        delta = 1;
                        break;
                    case "off":
                        delta = -1;
                        break;
                    default:
                        throw new IllegalStateException("Invalid instruction turn " + split[1]);
                }
                Coord start = new Coord(split[2]);
                Coord end = new Coord(split[4]);
                for (int x = start.getX(); x <= end.getX(); x++) {
                    for (int y = start.getY(); y <= end.getY(); y++) {
                        lights[x][y] += delta;
                        lights[x][y] = Math.max(0, lights[x][y]);
                    }
                }

                break;
            case "toggle":
                start = new Coord(split[1]);
                end = new Coord(split[3]);
                for (int x = start.getX(); x <= end.getX(); x++) {
                    for (int y = start.getY(); y <= end.getY(); y++) {
                        lights[x][y] += 2;
                    }
                }
                break;
            default:
                throw new IllegalStateException("Invalid instruction" + split[0]);
        }
    }

    private static class Coord {
        private final int x,y;
        public Coord(String commaSeparatedCoords) {
            String[] coords = commaSeparatedCoords.split(",");
            x = Integer.parseInt(coords[0]);
            y = Integer.parseInt(coords[1]);
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
