package aoc15;

import java.util.Arrays;
import java.util.Scanner;

public class WrappingPaper {

    public static void main(String[] args) {
        @SuppressWarnings("resource")
        Solver<Integer> wrappingSolver = new Solver<Integer>(new Scanner(System.in)) {

            @Override
            public Integer solveCase(String s) {
                String[] strDims = s.split("x");
                int[] dims = new int[] { Integer.parseInt(strDims[0]), Integer.parseInt(strDims[1]),
                        Integer.parseInt(strDims[2]) };
                System.out.println(Arrays.toString(dims));
                int l = dims[0];
                int w = dims[1];
                int h = dims[2];
                int face = 2* Math.min(l+w, Math.min(h+w, h+l));
                int volume = l * w * h;
                return volume + face;
            }

            @Override
            public String getInput(Scanner inputScanner) {
                return inputScanner.nextLine();
            }
        };

        long total = 0;
        while (wrappingSolver.isReady()) {
            total += wrappingSolver.processCase();
            System.out.println(total);
        }

    }

}
