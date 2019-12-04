package aoc19;

public class Numbers {
    public static void main(String[] args) {
        int lowerBound = 125730;
        int uppperBound = 579381;
        int count = 0;
        for (int i = lowerBound; i <= uppperBound; i++) {
            if (passesTest(i)) {
                count++;
            }

        }
        System.out.println(count);
    }

    private static boolean passesTest(int number) {
        boolean twoAdjSame = false;
        char[] chars = (number + "").toCharArray();
        for (int i = 0; i < (chars.length - 1); i++) {
            if (chars[i] == chars[i + 1]) {
                twoAdjSame = true;
            }
            if (chars[i] > chars[i + 1]) {
                return false;
            }
        }
        if (twoAdjSame) {
            System.out.println(number + " valid");
        }
        return twoAdjSame;
    }
}
