package adventofcode;

import java.util.Scanner;

/**
 * Day 6.
 */
public class ChristmasLights {
    public static void main(String[] args) {
        boolean[][] lights = new boolean[1000][];
        for (int i = 0; i < lights.length; i++) {
            lights[i] = new boolean[1000];
        }
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String line = in.nextLine();
            processLine(lights, line);
            System.out.println(countLights(lights));
        }

    }

    private static int countLights(boolean[][] lights) {
       int count = 0;
        for (boolean[] lightArray : lights) {
            for (boolean light : lightArray) {
                if (light) {
                    count++;
                }
            }
        }
        return count;
    }

    private static void processLine(boolean[][] lights, String line) {
        String[] split = line.split(" ");
        switch (split[0]) {
            case "turn":
                boolean action;
                switch (split[1]) {
                    case "on":
                        action = true;
                        break;
                    case "off":
                        action = false;
                        break;
                    default:
                        throw new IllegalStateException("Invalid instruction turn " + split[1]);
                }
                Coord start = new Coord(split[2]);
                Coord end = new Coord(split[4]);
                for (int x = start.getX(); x <= end.getX(); x++) {
                    for (int y = start.getY(); y <= end.getY(); y++) {
                        lights[x][y] = action;
                    }
                }

                break;
            case "toggle":
                start = new Coord(split[1]);
                end = new Coord(split[3]);
                for (int x = start.getX(); x <= end.getX(); x++) {
                    for (int y = start.getY(); y <= end.getY(); y++) {
                        lights[x][y] = !lights[x][y];
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
