package aoc19;

public class Param {
    private final ParamMode mode;
    private final int val;

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

    public void writeVal(int[] memory, int valToWrite) {
        memory[val] = valToWrite;
    }

}
