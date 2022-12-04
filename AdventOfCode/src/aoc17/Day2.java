package aoc17;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int checksum = 0;
        while (in.hasNextLine()) {
            String line = in.nextLine();
            Scanner lineScanner = new Scanner(line);
            List<Integer> linInts = new ArrayList<>();
            while (lineScanner.hasNextInt()) {
                int n = lineScanner.nextInt();
                linInts.add(n);
            }
            for (int i = 0; i < linInts.size(); i++) {
                for (int j = 0; j < linInts.size(); j++) {
                    int ii = linInts.get(i);
                    int jj = linInts.get(j);
                    if (ii > jj) {
                        if (ii % jj == 0) {
                            checksum += ii / jj;
                            break;
                        }
                    }
                }
            }
        }
        System.out.println(checksum);
    }
}
