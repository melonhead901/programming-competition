package aoc19;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;

class Instruction {

    private final OpCode opCode;
    private final Param[] params;
    int nextPosition;
    private final RelativeBaseOffset relativeBaseOffset;

    private Instruction(OpCode opCode, Param[] params, RelativeBaseOffset relativeBaseOffset) {
        this.opCode = opCode;
        this.params = params;
        this.relativeBaseOffset = relativeBaseOffset;
    }

    public static Instruction createInstruction(int[] vals, int position, RelativeBaseOffset offsetSupplier) {
        OpCode opCode = OpCode.getFrom(vals[position]);
        Param[] params = new Param[opCode.numParams];
        Instruction result = new Instruction(opCode, params, offsetSupplier);
        for (int i = 0; i < params.length; i++) {
            params[i] = new Param(ParamMode.getMode(vals[position] + "", i), vals[position + 1 + i], offsetSupplier);
        }
        result.nextPosition = position + 1 + params.length;
        return result;
    }

    public int length() {
        return 1 + opCode.numParams;
    }

    public void execute(int[] memoryVals, @NonNull BlockingQueue<Integer> inputValues, Queue<Integer> outputValues) throws InterruptedException {
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
                this.params[0].writeVal(memoryVals, inputValues.take());
                break;
            case OUTPUT:
                outputValues.add(this.params[0].getVal(memoryVals));
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
            case ADJUST_REL_BASE:
                relativeBaseOffset.adjustOffset(this.params[0].getVal(memoryVals));
                break;
            default:
                throw new IllegalStateException("unexpected opcode: " + opCode);
        }
    }

    public boolean isExit() {
        return this.opCode == OpCode.EXIT;
    }
}
