package adventofcode;

import java.util.Scanner;

public class NotQuiteLisp {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            while (in.hasNext()) {
                String str = in.next();
                int pos = 1;
                long level = 0;
                for (char c : str.toCharArray()) {
                    switch (c) {
                    case '(':
                        level++;
                        break;
                    case ')':
                        level--;
                        break;
                    default:
                        throw new IllegalArgumentException("Unexpected char: " + c);
                    }
                    if (level == -1) {
                        System.out.print(pos);
                        break;
                    }
                    pos++;
                }
                // System.out.println(level);
            }
        }
    }
}
