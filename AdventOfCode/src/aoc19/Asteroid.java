package aoc19;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Asteroid {
    Position position;

    public Asteroid(int r, int c) {
        this.position = new Position(r, c);
    }

    public static List<Asteroid> fromBoard(String input) {
        List<Asteroid> asteroids = new ArrayList<>();
        Scanner scanner = new Scanner(input);
        int row = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            for (int c = 0; c < line.length(); c++) {
                if (line.charAt(c) == '#') {
                    asteroids.add(new Asteroid(row, c));
                }
            }
            row++;
        }
        return asteroids;
    }

    public int manhattanDistanceToOther(Asteroid a) {
        return this.position.manhattanDistanceAway(a.position);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Asteroid asteroid = (Asteroid) o;
        return position.equals(asteroid.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }

    public Vector vectorTo(Asteroid currentAsteroid) {
        int dr = currentAsteroid.position.r - this.position.r;
        int dc = currentAsteroid.position.c - this.position.c;
        return new Vector(dr, dc);
    }

    public Slope slopeTo(Asteroid o) {
        return new Slope(this.position.r - o.position.r, this.position.c - o.position.c);
    }
}
