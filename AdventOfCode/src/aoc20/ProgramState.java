package aoc20;

class ProgramState {
    int accumValue;
    int pointer;

    public ProgramState(int accumValue, int pointer) {
        this.accumValue = accumValue;
        this.pointer = pointer;
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
