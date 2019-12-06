package aoc19;

public class Param {
    final ParamMode mode;
    final int val;

    public Param(ParamMode mode, int val) {
        this.mode = mode;
        this.val = val;
    }

    public int getVal(int[] memory) {
        switch (mode) {
            case POSITION:
                return memory[val];
            case IMMEDIATE:
                return val;
            default:
                throw new IllegalStateException("unexpected param mode: " + mode);
        }
    }

}
