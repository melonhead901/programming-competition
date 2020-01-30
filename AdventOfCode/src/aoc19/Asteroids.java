package aoc19;

import com.google.common.base.Joiner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Asteroids {
    public static void main(String[] args) throws IOException {
        String input = Joiner.on("\n").join(Files.readAllLines(Paths.get("/Users/kdonohue/Downloads/day10.txt")));

        List<Asteroid> asteroids = Asteroid.fromBoard(input);
        int maxR = asteroids.stream().max(Comparator.comparingInt(a -> a.position.r)).get().position.r;
        int maxC = asteroids.stream().max(Comparator.comparingInt(a -> a.position.c)).get().position.c;

        int max = Integer.MIN_VALUE;
        for (Asteroid asteroid : asteroids) {
            List<Asteroid> otherAsteroids = new ArrayList<>(asteroids);
            otherAsteroids.remove(asteroid);
            int observed = considerSlopes(asteroid, otherAsteroids);
            System.out.println(String.format("%s asteroid observed %s others of %s ", asteroid.position, observed, otherAsteroids.size()));
            max = Math.max(max, observed);
        }

        System.out.println("max is " + max);
    }

    private static int considerSlopes(Asteroid asteroid, List<Asteroid> otherAsteroids) {
        return otherAsteroids.stream().map(asteroid::slopeTo).collect(Collectors.toSet()).size();
    }

    private static List<Asteroid> considerAsteroid(Asteroid asteroid, List<Asteroid> otherAsteroids, Position furthestAway) {
        otherAsteroids.sort(Comparator.comparingInt(a -> a.manhattanDistanceToOther(asteroid)));
        List<Asteroid> observedAsteroids = new ArrayList<>();
        while (!otherAsteroids.isEmpty()) {
            Asteroid currentAsteroid = otherAsteroids.remove(0);
            observedAsteroids.add(currentAsteroid);
            Vector vector = asteroid.vectorTo(currentAsteroid);
            removeAsteroidsAlongVector(otherAsteroids, currentAsteroid.position, vector, furthestAway);
        }
        return observedAsteroids;
    }

    private static void removeAsteroidsAlongVector(List<Asteroid> otherAsteroids, Position currentPosition, Vector vector, Position furthestAway) {
        while ((currentPosition.c < furthestAway.c) && (currentPosition.r < furthestAway.r))  {
            currentPosition = vector.continueAlong(currentPosition);
            otherAsteroids.remove(new Asteroid(currentPosition.r, currentPosition.c));
        }
    }
}
