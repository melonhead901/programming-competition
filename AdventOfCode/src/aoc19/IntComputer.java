package aoc19;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class IntComputer {
    private static final String input = "3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0";

    private final int[] memory;
    private final Queue<Integer> inputValues;
    private final Queue<Integer> outputValues;

    private boolean hasHalted;

    public IntComputer(int[] memory) {
        this(memory, new LinkedList<>());
    }

    public IntComputer(int[] memory, Queue<Integer> inputValues) {
        this.memory = new int[memory.length];
        System.arraycopy(memory, 0, this.memory, 0, memory.length);
        this.inputValues = inputValues;
        this.outputValues = new LinkedList<>();
        this.hasHalted = false;
    }

    public IntComputer(String memory, Queue<Integer> inputValues) {
        this(parseStringToProgram(memory), inputValues);
    }

    private static  int[] parseStringToProgram(String memory) {
        String[] sVals = memory.split(",");
        int[] vals = new int[sVals.length];
        for (int i = 0; i < vals.length; i++) {
            vals[i] = Integer.parseInt(sVals[i]);
        }
        return vals;
    }

    public void runComputer() {
        int position = 0;
        Instruction instruction = Instruction.createInstruction(memory, 0);
        while (!instruction.isExit()) {
            instruction.execute(memory, inputValues, outputValues);
            position = instruction.nextPosition;
            instruction = Instruction.createInstruction(memory, position);
        }
    }

    public void printMemory() {
        System.out.println(Arrays.toString(memory));
    }

    public int getOutput() {
        if (this.outputValues.isEmpty()) {
            throw new IllegalStateException("No output values");
        }
        return this.outputValues.poll();
    }

    public static void main(String[] args) {
        LinkedList<Integer> ll = new LinkedList<>();
        ll.push(5);
        IntComputer computer = new IntComputer(input, ll);

        computer.runComputer();

        computer.printOutputValues();
        computer.printMemory();
    }

    private void printOutputValues() {
        while (!outputValues.isEmpty()) {
            System.out.println(outputValues.poll());
        }
    }

    public boolean addInput(int val) {
        return this.inputValues.add(val);
    }

    public boolean isRunning() {
        return !this.hasHalted;
    }
}

