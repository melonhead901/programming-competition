package aoc20;

import java.util.Locale;

class Instruction {
    public ProgramState process(ProgramState state) {
        switch (opcode) {
            case NOP:
                return new ProgramState(state.accumValue, state.pointer + 1);
            case JMP:
                return new ProgramState(state.accumValue, state.pointer + this.value);
            case ACC:
                return new ProgramState(state.accumValue + value, state.pointer + 1);
        }
        throw new IllegalStateException();
    }

    enum Opcode {
        NOP,
        JMP,
        ACC,
    }

    private final Opcode opcode;
    private final int value;

    public Instruction(String instr, String val) {
        this.opcode = Opcode.valueOf(instr.toUpperCase(Locale.ROOT));
        this.value = Integer.parseInt(val);
    }

    public Instruction(Opcode opcode, int value) {
        this.opcode = opcode;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Instruction{" + "opcode=" + opcode + ", value=" + value + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Instruction that = (Instruction) o;

        if (value != that.value) return false;
        return opcode == that.opcode;
    }

    @Override
    public int hashCode() {
        int result = opcode.hashCode();
        result = 31 * result + value;
        return result;
    }

    public Instruction swapCode() {
        if (this.opcode == Opcode.ACC) {
            return this;
        } else if (this.opcode == Opcode.JMP) {
            return new Instruction(Opcode.NOP, this.value);
        } else if (this.opcode == Opcode.NOP) {
            return new Instruction(Opcode.JMP, this.value);
        } else {
            throw new IllegalStateException();
        }
    }
}
