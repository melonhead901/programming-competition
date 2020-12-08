package aoc20;

class ProgramState {
    private int accumValue;
    int pointer;

    public ProgramState(int accumValue, int pointer) {
        this.accumValue = accumValue;
        this.pointer = pointer;
    }

    public ProgramState modifyAccumValue(int value) {
        return new ProgramState(this.accumValue + value, this.pointer);
    }

    public ProgramState movePointerForwardOne() {
        return new ProgramState(this.accumValue, this.pointer + 1);
    }

    public ProgramState movePointerRelative(int jump) {
        return new ProgramState(this.accumValue, this.pointer + jump);
    }


    @Override
    public String toString() {
        return "ProgramState{" + "value=" + accumValue + ", pointer=" + pointer + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProgramState that = (ProgramState) o;

        if (accumValue != that.accumValue) return false;
        return pointer == that.pointer;
    }

    @Override
    public int hashCode() {
        int result = accumValue;
        result = 31 * result + pointer;
        return result;
    }
}
