package aoc19;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class MassCalc {
    public static void main(String[] args) throws IOException {
        List<String>  lines = Files.readAllLines(Path.of("/Users/kdonohue/Downloads/day1.txt"));
        long sum = 0;
        for (String line : lines) {
            long val = Long.parseLong(line);
            long fuel = (val / 3) - 2;
            while (fuel > 0) {
                sum += fuel;
                fuel = (fuel / 3) - 2;
            }
        }
        System.out.println(sum);
    }
}
