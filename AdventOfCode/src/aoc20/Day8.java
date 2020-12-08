package aoc20;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

