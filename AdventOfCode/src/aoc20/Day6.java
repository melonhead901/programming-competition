package aoc20;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Day6 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int sum = 0;
        while (in.hasNextLine()) {
            List<String> response = gatherResponses(in);
            int answers = countAnswers(response);
            sum += answers;
            System.out.println(sum);
        }
    }

    private static int countAnswers(List<String> response) {
        Set<Character> chars = new HashSet<>();
        for (String str : response) {
            for (char c : str.toCharArray()) {
                chars.add(c);
            }
        }
        return chars.size();
    }

    private static List<String> gatherResponses(Scanner in) {
        List<String> result = new ArrayList<>();
        String line = in.nextLine();
        while (!line.isEmpty()) {
            result.add(line);
            line = in.nextLine();
        }
        return result;
    }
}
