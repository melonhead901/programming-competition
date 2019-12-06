package aoc19;

public enum ParamMode {
    POSITION(0),
    IMMEDIATE(1);

    private final int val;

    ParamMode(int val) {
        this.val = val;
    }
}
