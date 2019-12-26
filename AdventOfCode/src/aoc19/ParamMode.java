package aoc19;

public enum ParamMode {
    POSITION(0),
    IMMEDIATE(1),
    RELATIVE(2);

    private final int val;

    ParamMode(int val) {
        this.val = val;
    }

    public static ParamMode getMode(String s, int i) {
        //0123
        //1002
        int sPos = s.length() - 2 - (i + 1);
        if (sPos < 0) {
            return POSITION;
        }
        String val = s.substring(sPos, sPos + 1);
        int num = Integer.parseInt(val);
        for (ParamMode paramMode : ParamMode.values()) {
            if (paramMode.val == num) {
                return paramMode;
            }
        }
        throw new IllegalStateException("Unexpected param mode" + num);
    }
}
