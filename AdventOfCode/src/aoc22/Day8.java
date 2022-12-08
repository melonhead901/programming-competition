package aoc22;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day8 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<List<Integer>> board = new ArrayList<>();
        while (in.hasNext()) {
            String line = in.nextLine();
            List<Integer> row = new ArrayList<>();
            char[] chars = line.toCharArray();
            for (Character c : chars) {
                row.add(Integer.parseInt(c + ""));
            }
            board.add(row);
        }
        int count = 0;
        for (int r = 0; r < board.size(); r++) {
            for (int c = 0; c < board.get(r).size(); c++) {
                if (isTreeVisible(board, r, c)) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    private static boolean isTreeVisible(List<List<Integer>> board, int r, int c) {
        int height = board.get(r).get(c);
        boolean visibleToLeft = true;
        for (int xr = r - 1; xr >= 0; xr--) {
            if (board.get(xr).get(c) >= height) {
                visibleToLeft = false;
                break;
            }
        }
        boolean visibleToRight = true;
        for (int xr = r + 1; xr < board.size(); xr++) {
            if (board.get(xr).get(c) >= height) {
                visibleToRight = false;
                break;
            }
        }
        boolean visibleToTop = true;
        for (int xc = c - 1; xc >= 0; xc--) {
            if (board.get(r).get(xc) >= height) {
                visibleToTop = false;
                break;
            }
        }
        boolean visibleToBtm = true;
        for (int xc = c + 1; xc < board.get(r).size(); xc++) {
            if (board.get(r).get(xc) >= height) {
                visibleToBtm = false;
                break;
            }
        }
        return visibleToLeft || visibleToRight || visibleToTop || visibleToBtm;
    }
}
