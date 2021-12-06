package aoc21;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Day6 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        Scanner numScanner = new Scanner(line).useDelimiter(",");
        List<Integer> values = new ArrayList<>();
        while (numScanner.hasNext()) {
            values.add(numScanner.nextInt());
        }
        Map<Integer, Long> freqs = convertToMap(values);
        for (int i = 1; i <= 256; i++) {
            freqs = createNewValues(freqs);
            // System.out.printf("%s: %s %s \n", i, values.size(), values);

            System.out.printf("%s: %s \n", i, freqs.values().stream().reduce(0L, Long::sum));
        }
    }

    private static Map<Integer, Long> createNewValues(Map<Integer, Long> values) {
        Map<Integer, Long> newFreqs = new HashMap<>();
        for (int key : values.keySet()) {
            if (key == 0) {
                newFreqs.put(8, values.get(0));
            } else {
                newFreqs.put(key - 1, values.get(key));
            }
        }
        newFreqs.put(6, newFreqs.getOrDefault(6, 0L) + newFreqs.getOrDefault(8, 0L));
        return newFreqs;
    }

    private static Map<Integer, Long> convertToMap(List<Integer> values) {
        Map<Integer, Long> result = new HashMap<>();
        for (int val : values) {
            if (result.containsKey(val)) {
                result.put(val, result.get(val) + 1);
            } else {
                result.put(val, 1L);
            }
        }
        return result;
    }
}
