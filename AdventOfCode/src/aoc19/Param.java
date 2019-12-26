package aoc19;

import java.util.function.Supplier;

public class Param {
    private final ParamMode mode;
    private final int val;
    private final RelativeBaseOffset relativeBaseOffset;

    public Param(ParamMode mode, int val, RelativeBaseOffset relativeBaseOffset) {
        this.mode = mode;
        this.val = val;
        this.relativeBaseOffset = relativeBaseOffset;
    }

    public int getVal(int[] memory) {
        switch (mode) {
            case POSITION:
                return memory[val];
            case IMMEDIATE:
                return val;
            case RELATIVE:
                return memory[val + this.relativeBaseOffset.getOffset()];
            default:
                throw new IllegalStateException("unexpected param mode: " + mode);
        }
    }

    public void writeVal(int[] memory, int valToWrite) {
        memory[val] = valToWrite;
    }

}
