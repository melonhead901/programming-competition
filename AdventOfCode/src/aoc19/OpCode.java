package aoc19;

public enum OpCode {
    ADD(1, 3),
    MULTIPLY(2, 3),
    INPUT(3, 1),
    OUTPUT(4, 1),
    JUMP_IF_TRUE(5, 2),
    JUMP_IF_FALSE(6, 2),
    LESS_THAN(7, 3),
    EQUALS(8, 3),
    ADJUST_REL_BASE(9, 1),
    EXIT(99, 0);

    private final long val;
    public final int numParams;

    OpCode(long val, int numParams) {
        this.val = val;
        this.numParams = numParams;
    }

    public static OpCode getFrom(long nextInt) {
        for (OpCode match : OpCode.values()) {
            if (match.val == (nextInt % 100)) {
                return match;
            }
        }
        throw new IllegalStateException("Unexpected val: " + nextInt);
    }
}
