package aoc20;

import com.google.common.collect.Iterables;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

public class Day8 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<Instruction> instructions = new ArrayList<>();
        parseInstructions(in, instructions);
        ProgramState state = new ProgramState(0, 0);
        for (int i = 0; i < instructions.size(); i++) {
            Instruction instrToChange = instructions.get(i);
            Instruction changed = instrToChange.swapCode();
            if (instrToChange.equals(changed)) {
                continue;
            }
            List<Instruction> cloned = new ArrayList<>(instructions);
            cloned.set(i, changed);
            boolean programTerminates = runProgram(cloned, state);
            if (programTerminates) {
                System.out.println(i + " terminates");
                break;
            } else {
                System.out.println(i + " loops");
            }
        }
    }

    private static boolean runProgram(List<Instruction> instructions, ProgramState state) {
        Set<Integer> seenLocations = new HashSet<>();
        int lastInstrPoint = instructions.size() - 1;
        while (!seenLocations.contains(state.pointer)) {
            if (state.pointer > lastInstrPoint) {
                return true;
            }
            seenLocations.add(state.pointer);
            Instruction currInstr = instructions.get(state.pointer);
            System.err.println(currInstr);
            state = currInstr.process(state);
            System.out.println(state);
        }
        return false;
    }

    private static void parseInstructions(Scanner in, List<Instruction> instructions) {
        while (in.hasNext()) {
            final String next = in.next();
            if (next.contains("end")) {
                break;
            }
            instructions.add(new Instruction(next, in.next()));
        }
    }
}

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
        }
        else {
            throw new IllegalStateException();
        }
    }
}
