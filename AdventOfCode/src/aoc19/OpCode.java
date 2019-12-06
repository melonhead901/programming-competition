package aoc19;

public enum OpCode {
    ADD(1, 3),
    MULTIPLY(2, 3),
    EXIT(99, 0);

    private final int val;
    public final int numParams;

    OpCode(int val, int numParams) {
        this.val = val;
        this.numParams = numParams;
    }

    public static OpCode getFrom(int nextInt) {
        for (OpCode match : OpCode.values()) {
            if (match.val == nextInt) {
                return match;
            }
        }
        throw new IllegalStateException("Unexpected val: " + nextInt);
    }
}
