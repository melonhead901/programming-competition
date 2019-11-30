import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class JavaList {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        List<Integer> ints = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            ints.add(in.nextInt());
        }
        int numQueries = in.nextInt();
        for (int i = 0; i < numQueries; i++) {
            processQuery(in, ints);
        }
        StringJoiner stringJoiner = new StringJoiner(" ");
        ints.forEach(x -> stringJoiner.add(String.valueOf(x)));
        System.out.println(stringJoiner.toString());
    }

    private static void processQuery(Scanner in, List<Integer> ints) {
        String op = in.next();
        switch (op) {
            case "Insert":
                ints.add(in.nextInt(), in.nextInt());
                break;
            case "Delete":
                ints.remove(in.nextInt());
                break;
            default:
                throw new IllegalStateException("unexpected state" + op);
        }
    }
}


