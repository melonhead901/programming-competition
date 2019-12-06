package aoc19;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Orbit {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("/Users/kdonohue/Downloads/day6.txt"));
        Map<String, String> orbits = new HashMap<>();
        for (String line : lines) {
            String[] split = line.split("\\)");
            String a = split[0];
            String b = split[1];
            orbits.put(b, a);
        }

        Map<String, Integer> vals = new HashMap<>();
        vals.put("COM", 0);
        for (String key : orbits.keySet()) {
            calculateOrbit(vals, orbits, key);
        }

        System.out.println(vals.values().stream().reduce(Integer::sum).orElse(0));
    }

    private static int calculateOrbit(Map<String, Integer> vals, Map<String, String> orbits, String key) {
        if (vals.containsKey(key)) {
            return vals.get(key);
        }
        int val = 1 + calculateOrbit(vals, orbits, orbits.get(key));
        vals.put(key, val);
        return val;
    }
}
