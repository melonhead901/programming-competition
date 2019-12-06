package aoc19;

class Instruction {
    final OpCode opCode;
    Param[] params;
    int nextPosition;

    Instruction(OpCode opCode) {
        this.opCode = opCode;
    }

    public static Instruction createInstruction(int[] vals, int position) {
        OpCode opCode = OpCode.getFrom(vals[position]);
        Instruction result = new Instruction(opCode);
        Param[] params = new Param[opCode.numParams];
        result.params = params;
        for (int i = 0; i < params.length; i++) {
            params[i] = new Param(ParamMode.POSITION, vals[position + 1 + i]);
        }
        result.nextPosition = position + 1 + params.length;
        return result;
    }

    public int length() {
        return 1 + opCode.numParams;
    }

    public void execute(int[] memoryVals) {
        switch (opCode) {
            case ADD:
                int a = this.params[0].getVal(memoryVals);
                int b = this.params[1].getVal(memoryVals);
                int storeLoc = this.params[2].val;
                memoryVals[storeLoc] = a + b;
            case EXIT:
                break;
            case MULTIPLY:
                a = this.params[0].getVal(memoryVals);
                b = this.params[1].getVal(memoryVals);
                storeLoc = this.params[2].val;
                memoryVals[storeLoc] = a * b;

        }
    }

    public boolean isExit() {
        return this.opCode == OpCode.EXIT;
    }
}
