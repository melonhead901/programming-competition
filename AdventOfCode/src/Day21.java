import java.util.Objects;
import java.util.Scanner;

/**
 * Created by kdonohue on 12/20/16.
 */
public class Day21 {
    private Password password;

    public Day21(String initial) {
        this.password = new Password(initial);
    }

    public static void main(String[] args) {
        Day21 day21 = Day21.createDefault();
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            day21.addInstruction(in.nextLine());
            day21.printResult();
        }
    }

    private void printResult() {
        password.print();
    }

    private static Day21 createDefault() {
        //return new Day21("abdec");
        return new Day21("abcdefgh");
    }

    private void addInstruction(String s) {
        String[] words = s.split(" ");
        if (Objects.equals(words[0], "swap")) {
            if (Objects.equals(words[1], "position")) {
                password.swapPosition(words[2], words[5]);
            } else {
                password.swapLetter(words[2], words[5]);
            }
        } else if (Objects.equals(words[0], "reverse")) {
            password.reversePositions(words[2], words[4]);
        } else if (Objects.equals(words[0], "move")) {
            password.movePosition(words[2], words[5]);
        } else if (Objects.equals(words[0], "rotate")) {
            if (Objects.equals(words[1], "left")) {
                password.rotateLeft(words[2]);
            } else if (Objects.equals(words[1], "right")) {
                password.rotateRight(words[2]);
            } else {
                password.rotateBasedOnPosition(words[6]);
            }

        }
    }
}

class Password {
    private char[] chars;

    public Password(String str) {
        chars = str.toCharArray();
    }

    public void print() {
        System.out.println(new String(chars));
    }

    public void swapPosition(String word, String word1) {
        int first = Integer.valueOf(word);
        int second = Integer.valueOf(word1);
        swap(first, second);
    }

    private void swap(int first, int second) {
        char temp = chars[first];
        chars[first] = chars[second];
        chars[second] = temp;
    }

    public void swapLetter(String word, String word1) {
        char x = word.charAt(0);
        char y = word1.charAt(0);
        int xIndex = -1;
        int yIndex = -1;
        for (int i = 0; (xIndex == -1) || (yIndex == -1); i++) {
            if (chars[i] == x) {
                xIndex = i;
            }
            if (chars[i] == y) {
                yIndex = i;
            }
        }
        swap(xIndex, yIndex);
    }

    public void reversePositions(String word, String word1) {
        int first = Integer.valueOf(word);
        int second = Integer.valueOf(word1);
        while (first < second) {
            swap(first, second);
            first++;
            second--;
        }
    }

    public void movePosition(String word, String word1) {
        int first = Integer.valueOf(word);
        int second = Integer.valueOf(word1);
        if (first > second) {
            while (first > second) {
                swap(first, first - 1);
                first--;
            }
        } else {
            while (first < second) {
                swap(first, first + 1);
                first++;
            }
        }
    }

    public void rotateRight(String word) {
        int dist = Integer.valueOf(word);
        char[] newArr = new char[chars.length];
        for (int i = 0; i < newArr.length; i++) {
            newArr[i] = chars[(i - dist + newArr.length * 2) % newArr.length];
        }
        chars = newArr;
    }

    public void rotateLeft(String word) {
        int dist = Integer.valueOf(word);
        char[] newArr = new char[chars.length];
        for (int i = 0; i < newArr.length; i++) {
            newArr[i] = chars[(i + dist) % newArr.length];
        }
        chars = newArr;
    }

    public void rotateBasedOnPosition(String word) {
        char c = word.charAt(0);
        int loc = 0;
        while (chars[loc] != c) {
            loc++;
        }
        if (loc >= 4) {
            loc++;
        }
        loc += 1;
        char[] newArr = new char[chars.length];
        for (int i = 0; i < newArr.length; i++) {
            newArr[i] = chars[(i - loc + newArr.length * 2) % newArr.length];
        }
        chars = newArr;
    }
}
