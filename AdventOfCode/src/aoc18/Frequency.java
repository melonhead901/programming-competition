package aoc18;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Frequency {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("C:/Users/Kellen/Downloads/day1.txt"));
        int currFreq = 0;
        Set<Integer> seenFreq = new HashSet<>();
        while (true) {
            for (String line : lines) {
                currFreq += Integer.parseInt(line);
                if (seenFreq.contains(currFreq)) {
                    System.out.println(currFreq);
                    return;
                }
                seenFreq.add(currFreq);
            }
        }
    }
}
