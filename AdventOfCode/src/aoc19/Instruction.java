package aoc19;

import com.google.common.collect.ImmutableList;

import java.util.LinkedList;
import java.util.Queue;

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
            params[i] = new Param(ParamMode.getMode(vals[position] + "", i), vals[position + 1 + i]);
        }
        result.nextPosition = position + 1 + params.length;
        return result;
    }

    public int length() {
        return 1 + opCode.numParams;
    }

    public void execute(int[] memoryVals, Queue<Integer> inputValues) {
        switch (opCode) {
            case ADD:
                int a = this.params[0].getVal(memoryVals);
                int b = this.params[1].getVal(memoryVals);
                this.params[2].writeVal(memoryVals, a + b);
                break;
            case EXIT:
                break;
            case MULTIPLY:
                a = this.params[0].getVal(memoryVals);
                b = this.params[1].getVal(memoryVals);
                this.params[2].writeVal(memoryVals, a * b);
                break;
            case INPUT:
                if (inputValues.isEmpty()) {
                    throw new IllegalStateException("Trying to get too many input values");
                }
                this.params[0].writeVal(memoryVals, inputValues.poll());
                break;
            case OUTPUT:
                System.out.println(this.params[0].getVal(memoryVals));
                break;
            case LESS_THAN:
                a = this.params[0].getVal(memoryVals);
                b = this.params[1].getVal(memoryVals);
                this.params[2].writeVal(memoryVals, (a < b) ? 1 : 0);
                break;
            case JUMP_IF_TRUE:
                a = this.params[0].getVal(memoryVals);
                if (a != 0) {
                    this.nextPosition = this.params[1].getVal(memoryVals);
                }
                break;
            case JUMP_IF_FALSE:
                a = this.params[0].getVal(memoryVals);
                if (a == 0) {
                    this.nextPosition = this.params[1].getVal(memoryVals);
                }
                break;
            case EQUALS:
                a = this.params[0].getVal(memoryVals);
                b = this.params[1].getVal(memoryVals);
                this.params[2].writeVal(memoryVals, (a == b) ? 1 : 0);
                break;
            default:
                throw new IllegalStateException("unexpected opcode: " + opCode);
        }
    }

    public boolean isExit() {
        return this.opCode == OpCode.EXIT;
    }
}
