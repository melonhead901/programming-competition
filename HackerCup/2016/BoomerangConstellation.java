package hackercup2016;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class BoomerangConstellation {

    public static void main(String[] args) throws IOException {
        try (Scanner in = new Scanner(new File("boomerang.txt"))) {
            int cases = in.nextInt();
            for (int i = 0; i < cases; i++) {
                processCase(in, i + 1);
            }
        }
    }

    private static void processCase(Scanner in, int caseNum) {
        int numStars = in.nextInt();
        Star[] stars = new Star[numStars];
        for (int i = 0; i < numStars; i++) {
            stars[i] = new Star(in.nextInt(), in.nextInt());
        }

        double[][]  dists = new double[numStars][numStars];
        for (int i = 0; i < numStars; i++) {
            for (int j = i + 1 ; j < numStars; j++) {
                double dist = calcDist(stars[i], stars[j]);
                dists[i][j] = dist;
                dists[j][i] = dist;
            }
        }

        int numConstellations = 0;
        for (int i = 0; i < numStars; i++) {
            for (int j = i+1; j < numStars; j++) {
               for (int k = 0; k < numStars; k++) {
                   if (i == k || j == k) {continue;}
                   if (dists[i][j] == dists[j][k]  || dists[i][j] == dists[i][k]) {
                       numConstellations++;
                   }
               }
            }
        }

        System.out.println("Case #" + caseNum + ": " + numConstellations/2);

    }

    private static int calcDist(Star star, Star star2) {
        return (int) (Math.pow(star.x - star2.x, 2) + Math.pow(star.y - star2.y, 2));
    }

    private static class Star {
        int x;
        int y;

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + x;
            result = prime * result + y;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            Star other = (Star) obj;
            if (x != other.x) {
                return false;
            }
            if (y != other.y) {
                return false;
            }
            return true;
        }

        @Override
        public String toString() {
            return "Star [x=" + x + ", y=" + y + "]";
        }

        public Star(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }
}
