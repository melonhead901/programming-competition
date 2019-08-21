import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class State {
    public final int n;
    public final List<List<Integer>> sequences;
    private int lastAnswer;
    private final List<Integer> answers;


    State(int n) {
        this.n = n;
        this.sequences = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            this.sequences.add(new ArrayList<>());
        }
        this.lastAnswer = 0;
        this.answers = new ArrayList<>();
    }

    public List<Integer> getAnswers() {
        return Collections.unmodifiableList(answers);
    }

    public void insert(int x, int y) {
        int index = (x ^ this.lastAnswer) % n;
        this.sequences.get(index).add(y);
    }

    public void process(int x, int y) {
        int index = (x ^ this.lastAnswer) % n;
        List<Integer> sequence = this.sequences.get(index);
        this.lastAnswer = sequence.get(y % sequence.size());
        answers.add(lastAnswer);
    }
}

class Result {

    /*
     * Complete the 'dynamicArray' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. 2D_INTEGER_ARRAY queries
     */

    public static List<Integer> dynamicArray(int n, List<List<Integer>> queries) {
        State state = new State(n);
        for (List<Integer> query : queries) {
            runQuery(state, query);
        }

        return state.getAnswers();
    }

    private static void runQuery(State state, List<Integer> query) {
        switch (query.get(0)) {
            case 1:
                runQuery1(state, query);
                break;
            case 2:
                runQuery2(state, query);
                break;
        }
    }

    private static void runQuery2(State state, List<Integer> query) {
        System.out.println("Process query " + query);
        state.process(query.get(1), query.get(2));
    }

    private static void runQuery1(State state, List<Integer> query) {
        System.out.println("Insert query " + query);
        state.insert(query.get(1), query.get(2));
    }



}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int q = Integer.parseInt(firstMultipleInput[1]);

        List<List<Integer>> queries = new ArrayList<>();

        IntStream.range(0, q).forEach(i -> {
            try {
                queries.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> result = Result.dynamicArray(n, queries);

        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}

