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
        int max = 0;
        for (int r = 0; r < board.size(); r++) {
            for (int c = 0; c < board.get(r).size(); c++) {
                int visible = isTreeVisible(board, r, c);
                max = Math.max(max, visible);
            }
        }
        System.out.println(max);
    }

    private static int isTreeVisible(List<List<Integer>> board, int r, int c) {
        int height = board.get(r).get(c);
        int countUP = 0;
        for (int xr = r - 1; xr >= 0; xr--) {
            countUP++;
            if (board.get(xr).get(c) >= height) {
                break;
            }
        }
        int countDOWN = 0;
        for (int xr = r + 1; xr < board.size(); xr++) {
            countDOWN++;
            if (board.get(xr).get(c) >= height) {
                break;
            }
        }
        int countLEFT = 0;
        for (int xc = c - 1; xc >= 0; xc--) {
            countLEFT++;
            if (board.get(r).get(xc) >= height) {
                break;
            }
        }
        int countRIGHT = 0;
        for (int xc = c + 1; xc < board.get(r).size(); xc++) {
            countRIGHT++;
            if (board.get(r).get(xc) >= height) {
                break;
            }
        }
        return countUP * countDOWN * countLEFT * countRIGHT;
    }
}
