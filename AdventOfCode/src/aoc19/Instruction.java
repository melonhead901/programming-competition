package aoc19;

import java.util.List;

class Instruction {
    final OpCode opCode;
    List<ParamMode> paramModes;

    int[] paramVals;
    int nextPosition;

    Instruction(OpCode opCode) {
        this.opCode = opCode;
    }

    public static Instruction createInstruction(int[] vals, int position) {
        OpCode opCode = OpCode.getFrom(vals[position]);
        Instruction result = new Instruction(opCode);
        result.paramVals = new int[opCode.numParams];
        int[] paramValues = result.paramVals;
        for (int i = 0; i < paramValues.length; i++) {
            paramValues[i] = vals[position + i + 1];
        }
        result.nextPosition = position + 1 + paramValues.length;
        return result;
    }

    public int length() {
        return 1 + opCode.numParams;
    }

    public void execute(int[] memoryVals) {
        switch (opCode) {
            case ADD:
                int a = memoryVals[this.paramVals[0]];
                int b = memoryVals[this.paramVals[1]];
                int storeLoc = this.paramVals[2];
                memoryVals[storeLoc] = a + b;
            case EXIT:
                break;
            case MULTIPLY:
                a = memoryVals[this.paramVals[0]];
                b = memoryVals[this.paramVals[1]];
                storeLoc = this.paramVals[2];
                memoryVals[storeLoc] = a * b;
        }
    }

    public boolean isExit() {
        return this.opCode == OpCode.EXIT;
    }
}
