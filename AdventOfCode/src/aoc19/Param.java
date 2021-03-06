package aoc19;

import java.util.function.Supplier;

public class Param {
    private final ParamMode mode;
    private final long val;
    private final RelativeBaseOffset relativeBaseOffset;

    public Param(ParamMode mode, long val, RelativeBaseOffset relativeBaseOffset) {
        this.mode = mode;
        this.val = val;
        this.relativeBaseOffset = relativeBaseOffset;
    }

    public long getVal(long[] memory) {
        switch (mode) {
            case POSITION:
                return memory[(int) val];
            case IMMEDIATE:
                return val;
            case RELATIVE:
                return memory[(int) (val + this.relativeBaseOffset.getOffset())];
            default:
                throw new IllegalStateException("unexpected param mode: " + mode);
        }
    }

    public void writeVal(long[] memory, long valToWrite) {
        switch (mode) {
            case POSITION:
                memory[(int) val] = valToWrite;
                break;
            case IMMEDIATE:
                throw new  IllegalStateException("cannot write to a param in immediate mode");
            case RELATIVE:
                memory[(int) (val + relativeBaseOffset.getOffset())] = valToWrite;
        }
    }

}
