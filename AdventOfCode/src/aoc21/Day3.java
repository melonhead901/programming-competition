package aoc21;

import com.google.common.collect.Iterables;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
         String first = "000000011010";
        //String first = "00000";
        List<String> itemsSeen = new ArrayList<>();
        int[] onesCounts = new int[first.length()];
        while (in.hasNext()) {
            String line = in.next();
            if (line.startsWith("Z")) {
                break;
            }
            itemsSeen.add(line);
            if (Objects.equals(line, "Z")) {
                break;
            }
            for (int i =0; i < line.length(); i++) {
                if (line.charAt(i) == '1') {
                    onesCounts[i]++;
                }
            }
        }
        List<String> oxygenList = new ArrayList<>(itemsSeen);
        for (int i = 0; (i < first.length()) && (oxygenList.size() > 1); i++) {
            char matchBit = (countOnes(oxygenList, i) >= (oxygenList.size() / 2.0)) ? '1' : '0';
            final int finalI = i;
            oxygenList = oxygenList.stream().filter(li -> li.charAt(finalI) == matchBit).collect(Collectors.toList());
        }
        List<String> carbonList = new ArrayList<>(itemsSeen);
        for (int i = 0; (i < first.length()) && (carbonList.size() > 1); i++) {
            char matchBit = (countOnes(carbonList, i) >= (carbonList.size() / 2.0)) ? '0' : '1';
            final int finalI = i;
            carbonList = carbonList.stream().filter(li -> li.charAt(finalI) == matchBit).collect(Collectors.toList());
        }
        System.out.println(oxygenList);
        int oxy = Integer.parseInt(Iterables.getOnlyElement(oxygenList), 2);
        System.out.println(oxy);
        int carb = Integer.parseInt(Iterables.getOnlyElement(carbonList), 2);
        System.out.println(carbonList);
        System.out.println(carb);

        System.out.println(oxy*carb);

    }

    private static int countOnes(List<String> oxygenMatch, int i) {
        return (int) oxygenMatch.stream().filter(s -> s.charAt(i) == '1').count();
    }

}
