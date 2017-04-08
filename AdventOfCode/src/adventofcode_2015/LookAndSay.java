package adventofcode_2015;

import java.util.Scanner;

/**
 * Created by kdonohue on 12/19/16.
 */
public class LookAndSay {
    private String currentString;

    public static final int NUM_ITERATIONS = 50;

    public LookAndSay(String s) {
        this.currentString = s;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        LookAndSay lookAndSay = new LookAndSay(in.nextLine());
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            lookAndSay.iterate();
        }
        lookAndSay.printResultLength();
    }

    private void printResultLength() {
        System.out.print(currentString.length());
    }

    private void iterate() {
        int currentPosition = 0;
        char lastChar = currentString.charAt(currentPosition);
        int runLength = 0;
        StringBuilder newString = new StringBuilder();
        while (currentPosition < currentString.length()) {
            if (currentString.charAt(currentPosition) != lastChar) {
                newString.append(runLength);
                newString.append(lastChar);
                runLength = 0;
            }
            lastChar = currentString.charAt(currentPosition);
            runLength++;
            currentPosition++;
        }
        newString.append(runLength);
        newString.append(lastChar);
        currentString = newString.toString();
        System.out.println(currentString);
    }

}
