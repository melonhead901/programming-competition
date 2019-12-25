package aoc19;

import com.google.common.collect.ImmutableSet;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Thrusters {
    private static final int INITIAL_INPUT = 0;
    //private static final String input = "3,8,1001,8,10,8,105,1,0,0,21,46,63,76,97,118,199,280,361,442,99999,3,9,102,4,9,9,101,2,9,9,1002,9,5,9,101,4,9,9,102,2,9,9,4,9,99,3,9,101,5,9,9,102,3,9,9,101,3,9,9,4,9,99,3,9,1001,9,2,9,102,3,9,9,4,9,99,3,9,1002,9,5,9,101,4,9,9,1002,9,3,9,101,2,9,9,4,9,99,3,9,1002,9,5,9,101,3,9,9,1002,9,5,9,1001,9,5,9,4,9,99,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,1,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,99,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,102,2,9,9,4,9,99,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,101,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,1,9,9,4,9,99,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,2,9,9,4,9,99,3,9,101,1,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,101,2,9,9,4,9,99";
    private static final String input = "3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0";

    public static void main(String[] args) {
        recursiveHelper(ImmutableSet.of(0,1,2,3,4), new int[5], 0);
        System.out.println(MAX_VAL);
    }

    private static void recursiveHelper(Set<Integer> vals, int[] settings, int i) {
        if (i == 5) {
            runSimulation(settings);
            //System.out.println(Arrays.toString(settings));
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

    private static void runSimulation(int[] phaseSettings) {
        IntComputer[] computers = new IntComputer[phaseSettings.length];
        for (int i = 0; i < phaseSettings.length; i++) {
            computers[i] = new IntComputer(input, new LinkedList<>());
        }
        int lastInput = INITIAL_INPUT;
        //while (anyComputerIsStillRunning(computers)) {
            for (int i = 0; i < phaseSettings.length; i++) {
                IntComputer ic = computers[i];
                ic.addInput(phaseSettings[i]);
                ic.addInput(lastInput);
                ic.runComputer();
                lastInput = ic.getOutput();
            }
            MAX_VAL = Math.max(MAX_VAL, lastInput);
        //}
    }

    private static boolean anyComputerIsStillRunning(IntComputer[] computers) {
        for (IntComputer computer : computers) {
            if (computer.isRunning()) {
                return true;
            }
        }
        return false;
    }
}
