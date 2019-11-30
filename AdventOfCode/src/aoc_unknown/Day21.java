package aoc_unknown;

import java.util.*;

public class Day21 {
    private final Password password;

    private Day21(String initial) {
        this.password = new Password(initial);
    }

    public static void main(String[] args) {
        List<String> possibleInputs = permuteString("abcdefgh");
        Map<String, Day21> encodings = new HashMap<>();
        for (String str : possibleInputs) {
            encodings.put(str, Day21.createWithInput(str));
        }
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String instruction = in.nextLine();
            for (Day21 day21 : encodings.values()) {
                day21.addInstruction(instruction);
            }
        }
        for (Map.Entry<String, Day21> entry : encodings.entrySet()) {
            if (entry.getValue().encodedMatches("fbgdceah")) {
                System.out.println(entry.getKey());
            }
        }
    }

    private boolean encodedMatches(String s) {
        return password.matches(s);
    }

    private static Day21 createWithInput(String str) {
        return new Day21(str);
    }

    private static List<String> permuteString(String str) {
        Set<Character> characters = new HashSet<>();
        for (Character c : str.toCharArray()) {
            characters.add(c);
        }
        List<String> result = new ArrayList<>();
        permuteStringHelper(result, "", characters);
        return result;
    }

    private static void permuteStringHelper(List<String> result, String s, Set<Character> characters) {
        if (characters.isEmpty()) {
            result.add(s);
            return;
        }
        for (Character c : characters) {
            Set<Character> newChars = new HashSet<>(characters);
            newChars.remove(c);
            permuteStringHelper(result, s + c, newChars);
        }
    }

    private void printResult() {
        password.print();
    }

    private static Day21 createDefault() {
        //return new aoc_unknown.Day21("abdec");
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
            if (i >= chars.length) {
                System.err.print(String.format("ERR swapping %s with %s in %s", word, word1, new String(chars)));

            }
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
        chars = rotateRightByChars(dist);
    }

    private char[] rotateRightByChars(int dist) {
        char[] newArr = new char[chars.length];
        for (int i = 0; i < newArr.length; i++) {
            newArr[i] = chars[((i - dist) + (newArr.length * 2)) % newArr.length];
        }
        return newArr;
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
        loc++;
        chars = rotateRightByChars(loc);
    }

    public boolean matches(String s) {
        return Objects.equals(new String(chars), s);
    }
}
