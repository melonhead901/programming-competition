package aoc19;

import com.google.common.collect.ImmutableSet;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Thrusters {
    private static final int INITIAL_INPUT = 0;
    private static final String input = "3,8,1001,8,10,8,105,1,0,0,21,46,63,76,97,118,199,280,361,442,99999,3,9,102,4,9,9,101,2,9,9,1002,9,5,9,101,4,9,9,102,2,9,9,4,9,99,3,9,101,5,9,9,102,3,9,9,101,3,9,9,4,9,99,3,9,1001,9,2,9,102,3,9,9,4,9,99,3,9,1002,9,5,9,101,4,9,9,1002,9,3,9,101,2,9,9,4,9,99,3,9,1002,9,5,9,101,3,9,9,1002,9,5,9,1001,9,5,9,4,9,99,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,1,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,99,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,102,2,9,9,4,9,99,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,101,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,1,9,9,4,9,99,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,2,9,9,4,9,99,3,9,101,1,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,101,2,9,9,4,9,99";
    //private static final String input = "3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5";

    public static void main(String[] args) throws InterruptedException {
        recursiveHelper(ImmutableSet.of(5, 6, 7, 8, 9), new int[5], 0);
        System.out.println(MAX_VAL);
    }

    private static void recursiveHelper(Set<Integer> vals, int[] settings, int i) throws InterruptedException {
        if (i == 5) {
            runSimulation(settings);
        }
        for (Integer setting : vals) {
            settings[i] = setting;
            Set<Integer> newVals = new HashSet<>(vals);
            newVals.remove(setting);
            int[] newSettings = new int[settings.length];
            System.arraycopy(settings, 0, newSettings, 0, settings.length);
            recursiveHelper(newVals, newSettings, i + 1);
        }
    }

    private static int MAX_VAL = Integer.MIN_VALUE;

    private static void runSimulation(int[] phaseSettings) throws InterruptedException {
        IntComputer[] computers = new IntComputer[phaseSettings.length];
        BlockingQueue<Integer>[] queues = new BlockingQueue[5];
        for (int i = 0; i < 5; i++) {
            queues[i] = new LinkedBlockingQueue<>();
        }
        for (int i = 0; i < phaseSettings.length; i++) {
            IntComputer computer = new IntComputer(input, queues[i], queues[(i+1) % 5]);
            computers[i] = computer;
            computer.addInput(phaseSettings[i]);
            new Thread(computer).start();
        }
        computers[0].addInput(INITIAL_INPUT);
        while (anyComputerIsStillRunning(computers)) {
            System.err.println("Loop completed. ");
        }
        int output = computers[4].getOutput();
        System.err.println(String.format("Val for %s is %s", Arrays.toString(phaseSettings), output));
        MAX_VAL = Math.max(MAX_VAL, output);
    }

    private static boolean anyComputerIsStillRunning(IntComputer[] computers) {
        return Arrays.stream(computers).anyMatch(IntComputer::isRunning);
    }
}
