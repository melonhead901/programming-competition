package aoc19;

import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class IntComputer implements Runnable {
    private final long[] memory;
    private final BlockingQueue<Long> inputValues;
    private final BlockingQueue<Long> outputValues;

    private final RelativeBaseOffset relativeBaseOffset;

    private int position;

    private boolean hasHalted;

    public IntComputer(long[] memory) {
        this(memory, new LinkedBlockingDeque<>(), new LinkedBlockingQueue<>(), new RelativeBaseOffset());
    }

    public IntComputer(long[] memory, BlockingQueue<Long> inputValues, BlockingQueue<Long> outputValues, RelativeBaseOffset relativeBaseOffset) {
        this.memory = new long[100000000];
        System.arraycopy(memory, 0, this.memory, 0, memory.length);
        this.inputValues = inputValues;
        this.outputValues = outputValues;
        this.hasHalted = false;
        this.position = 0;
        this.relativeBaseOffset = relativeBaseOffset;
    }

    public IntComputer(String memory) {
        this(parseStringToProgram(memory));
    }

    public IntComputer(String memory, BlockingQueue<Long> inputValues, BlockingQueue<Long> outputValues) {
        this(parseStringToProgram(memory), inputValues, outputValues, new RelativeBaseOffset());
    }

    private static long[] parseStringToProgram(String memory) {
        String[] sVals = memory.split(",");
        long[] vals = new long[sVals.length];
        for (int i = 0; i < vals.length; i++) {
            vals[i] = Long.parseLong(sVals[i]);
        }
        return vals;
    }

    public void runComputer() throws InterruptedException {
        Instruction instruction = Instruction.createInstruction(memory, 0, relativeBaseOffset);
        while (!instruction.isExit()) {
            instruction.execute(memory, inputValues, outputValues);
            position = instruction.nextPosition;
            instruction = Instruction.createInstruction(memory, position, relativeBaseOffset);
        }
        this.hasHalted = true;
    }

    public void printMemory() {
        System.out.println(Arrays.toString(memory));
    }

    public boolean hasOutput() {
        return !this.outputValues.isEmpty();
    }

    public long getOutput() throws InterruptedException {
        return this.outputValues.take();
    }

    private void printOutputValues() {
        while (!outputValues.isEmpty()) {
            System.out.println(outputValues.poll());
        }
    }

    public boolean addInput(long val) {
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

