package aoc19;

import java.util.Arrays;

public class IntCodes {
    private static final String input = "1,9,10,3,2,3,11,0,99,30,40,50";

    public static void main(String[] args) {
        //String input = "2,4,4,5,99,0";
        String[] sVals = input.split(",");
        int[] vals = new int[sVals.length];
        for (int i = 0; i < vals.length; i++) {
            vals[i] = Integer.parseInt(sVals[i]);
        }

        int position = 0;
        Instruction instruction = Instruction.createInstruction(vals, 0);
        while (!instruction.isExit()) {
            instruction = Instruction.createInstruction(vals, position);
            instruction.execute(vals);
            position = instruction.nextPosition;
        }
        System.out.println(Arrays.toString(vals));
    }
}

