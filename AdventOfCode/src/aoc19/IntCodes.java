package aoc19;

public class IntCodes {
    private static final String input = "1,12,2,3,1,1,2,3,1,3,4,3,1,5,0,3,2,13,1,19,1,19,9,23,1,5,23,27,1,27,9,31,1,6,31,35,2,35,9,39,1,39,6,43,2,9,43,47,1,47,6,51,2,51,9,55,1,5,55,59,2,59,6,63,1,9,63,67,1,67,10,71,1,71,13,75,2,13,75,79,1,6,79,83,2,9,83,87,1,87,6,91,2,10,91,95,2,13,95,99,1,9,99,103,1,5,103,107,2,9,107,111,1,111,5,115,1,115,5,119,1,10,119,123,1,13,123,127,1,2,127,131,1,131,13,0,99,2,14,0,0";
    private static final int EXIT = 99;
    private static final int ADD = 1;
    private static final int MULTIPLY = 2;

    public static void main(String[] args) {
        //String input = "2,4,4,5,99,0";
        String[] sVals = input.split(",");
        int[] vals = new int[sVals.length];
        for (int i = 0; i < vals.length; i++) {
            vals[i] = Integer.parseInt(sVals[i]);
        }
        int[] initialVals = new int[vals.length];
        System.arraycopy(vals, 0, initialVals, 0, vals.length);
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                System.arraycopy(initialVals, 0, vals, 0, vals.length);
                vals[1] = i;
                vals[2] = j;
                int answer = runProgram(vals);
                if (answer == 19690720) {
                    System.out.println(i * 100 + j);
                    return;
                }
            }
        }
    }

    private static int runProgram(int[] vals) {
        try {
            int position = 0;
            while (vals[position] != EXIT) {
                int val1 = vals[vals[position + 1]];
                int val2 = vals[vals[position + 2]];
                int storeLoc = vals[position + 3];
                int answer;
                switch (vals[position]) {
                    case ADD:
                        answer = val1 + val2;
                        break;
                    case MULTIPLY:
                        answer = val1 * val2;
                        break;
                    default:
                        throw new IllegalStateException(String.format("Illegal value %s at position %s", vals[position], position));
                }
                vals[storeLoc] = answer;
                position += 4;
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            return -1;
        }
        return vals[0];
    }
}
