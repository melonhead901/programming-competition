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

    private static String runLengthEncoding(String s) {
        StringBuilder result = new StringBuilder();
        char prev = s.charAt(0);
        int length = 1;
        for (int i = 1; i < s.length(); i++) {
           if (s.charAt(i) == prev) {
               length++;
           } else {
               result.append(length);
               result.append(prev);
               prev = s.charAt(i);
               length = 1;
           }
        }
        result.append(length);
        result.append(prev);
        return result.toString();
    }

    private static boolean passesTest(int number) {
        boolean twoAdjSame = false;
        String strRep = number + "";
        char[] chars = strRep.toCharArray();
        for (int i = 0; i < (chars.length - 1); i++) {
            if (chars[i] > chars[i + 1]) {
                return false;
            }
        }
        String runLengthEncoding = runLengthEncoding(strRep);
        System.out.println(String.format("RLE of %s is %s", strRep, runLengthEncoding));
        for (int ii = 0; ii < runLengthEncoding.length(); ii += 2) {
            if (runLengthEncoding.charAt(ii) == '2') {
                twoAdjSame = true;
                break;
            }
        }
        if (twoAdjSame) {
            System.out.println(number + " valid");
        }
        return twoAdjSame;
    }
}
