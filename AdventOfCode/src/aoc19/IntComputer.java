package aoc19;

import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class IntComputer implements Runnable {
    private final int[] memory;
    private final BlockingQueue<Integer> inputValues;
    private final BlockingQueue<Integer> outputValues;

    private int position;

    private boolean hasHalted;

    public IntComputer(int[] memory) {
        this(memory, new LinkedBlockingDeque<>(), new LinkedBlockingQueue<>());
    }

    public IntComputer(int[] memory, BlockingQueue<Integer> inputValues, BlockingQueue<Integer> outputValues) {
        this.memory = new int[memory.length];
        System.arraycopy(memory, 0, this.memory, 0, memory.length);
        this.inputValues = inputValues;
        this.outputValues = outputValues;
        this.hasHalted = false;
        this.position = 0;
    }

    public IntComputer(String memory, BlockingQueue<Integer> inputValues, BlockingQueue<Integer> outputValues) {
        this(parseStringToProgram(memory), inputValues, outputValues);
    }

    private static  int[] parseStringToProgram(String memory) {
        String[] sVals = memory.split(",");
        int[] vals = new int[sVals.length];
        for (int i = 0; i < vals.length; i++) {
            vals[i] = Integer.parseInt(sVals[i]);
        }
        return vals;
    }

    public void runComputer() throws InterruptedException {
        Instruction instruction = Instruction.createInstruction(memory, 0);
        while (!instruction.isExit()) {
            instruction.execute(memory, inputValues, outputValues);
            position = instruction.nextPosition;
            instruction = Instruction.createInstruction(memory, position);
        }
        this.hasHalted = true;
    }

    public void printMemory() {
        System.out.println(Arrays.toString(memory));
    }

    public int getOutput() throws InterruptedException {
        return this.outputValues.take();
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

    @Override
    public void run() {
        try {
            this.runComputer();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

