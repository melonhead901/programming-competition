import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.joining;

public class FreqQuery {

    private static class FreqCounter {
        private Map<Integer, Integer> freq;
        private FreqCounter freqCounter;

        private FreqCounter() {
            this.freq = new HashMap<>();
        }

        public static FreqCounter initialize() {
            FreqCounter result = new FreqCounter();
            result.freqCounter = new FreqCounter();
            return result;
        }

        public void replace(int old, int newVal) {
            if (freqCounter != null) {
                freqCounter.decrement(old);
                freqCounter.increment(newVal);
            }
        }

        public void increment(int i) {
            int oldVal = freq.getOrDefault(i, 0);
            int newVal = oldVal + 1;
            freq.put(i, newVal);
            replace(oldVal, newVal);
        }

        public void decrement(int i) {
            if (freq.containsKey(i) && (freq.get(i) > 0)) {
                int oldVal = freq.get(i);
                int newVal = oldVal-1;
                freq.put(i, newVal);
                replace(oldVal, newVal);
            }
        }

        public boolean containsValue(int freq) {
            return freqCounter.freq.getOrDefault(freq, 0) > 0;
        }
    }

    // Complete the freqQuery function below.
    static List<Integer> freqQuery(List<int[]> queries) {
        List<Integer> resultList = new ArrayList<>();
        FreqCounter counter = FreqCounter.initialize();
        for (int[] query : queries) {
            int first = query[0];
            int second = query[1];
            switch (first) {
                case 1:
                    counter.increment(second);
                    break;
                case 2:
                    counter.decrement(second);
                    break;
                case 3:
                    resultList.add(counter.containsValue(second) ? 1 : 0);
                    break;
                default:
                    throw new IllegalStateException("Unexpected state " + first);
            }
        }
        return resultList;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            int q = Integer.parseInt(bufferedReader.readLine().trim());
            List<int[]> queries = new ArrayList<>(q);
            Pattern p  = Pattern.compile("^(\\d+)\\s+(\\d+)\\s*$");
            for (int i = 0; i < q; i++) {
                int[] query = new int[2];
                Matcher m = p.matcher(bufferedReader.readLine());
                if (m.matches()) {
                    query[0] = Integer.parseInt(m.group(1));
                    query[1] = Integer.parseInt(m.group(2));
                    queries.add(query);
                }
            }
            List<Integer> ans = freqQuery(queries);
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")))) {
                bufferedWriter.write(
                        ans.stream()
                                .map(Object::toString)
                                .collect(joining("\n"))
                                + "\n");
            }
        }
    }
}

