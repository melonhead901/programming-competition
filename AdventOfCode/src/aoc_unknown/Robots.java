package aoc_unknown;

import java.util.*;

public class Robots {
    private final List<Robot> robots;
    private final List<GiveInstruction> giveInstructions;

    private Robots() {
        robots = new ArrayList<>();
        giveInstructions = new ArrayList<>();
    }

    public static void main(String[] args) {
        Robots robots = new Robots();
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            robots.addLine(in.nextLine());
        }
        while (robots.canProceed()) {
            robots.proceed();
        }
        //robots.printLefotvers();
    }

    private boolean canProceed() {
        return robots.stream().anyMatch(Robot::readyToGive);
    }

    private void proceed() {
        Robot giver = robots.stream().filter(Robot::readyToGive).findFirst().get();
        GiveInstruction giveInstruction = giveInstructions.stream().filter(x -> x.giver == giver).findFirst().get();
        giver.processInstruction(giveInstruction);
        giveInstructions.remove(giveInstruction);
    }

    private void printSize() {
        Collections.sort(robots, Comparator.comparingInt(x -> x.number));
        for (Robot r : robots) {
            System.out.println(r);
        }
    }

    private void addLine(String s) {
        if (s.startsWith("value")) {
            String[] words = s.split(" ");
            Chip chip = new Chip(words[1]);
            Robot robot = getOrCreate(words[5], false);
            robot.takeChip(chip);
        } else {
            String[] words = s.split(" ");
            Robot giver = getOrCreate(words[1], false);
            Robot lowRecip = getOrCreate(words[6], Objects.equals(words[5], "output"));
            Robot highRecip = getOrCreate(words[11], Objects.equals(words[10], "output"));
            GiveInstruction giveInstruction = new GiveInstruction(giver, highRecip, lowRecip);
            giveInstructions.add(giveInstruction);
        }
    }

    private Robot getOrCreate(String str, boolean isOutput) {
        int number = Integer.valueOf((isOutput ? "10000" : "") + str);
        if (robots.stream().anyMatch(x -> x.number == number)) {
            return robots.stream().filter(x -> x.number == number).findFirst().get();
        } else {
            Robot robot = new Robot((isOutput ? "10000" : "") + str);
            robots.add(robot);
            return robot;
        }
    }

}

class GiveInstruction {
    final Robot giver;
    final Robot highRecip;
    final Robot lowRecip;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GiveInstruction that = (GiveInstruction) o;

        if (giver != null ? !giver.equals(that.giver) : that.giver != null) return false;
        if (highRecip != null ? !highRecip.equals(that.highRecip) : that.highRecip != null) return false;
        return lowRecip != null ? lowRecip.equals(that.lowRecip) : that.lowRecip == null;
    }

    @Override
    public int hashCode() {
        int result = giver != null ? giver.hashCode() : 0;
        result = 31 * result + (highRecip != null ? highRecip.hashCode() : 0);
        result = 31 * result + (lowRecip != null ? lowRecip.hashCode() : 0);
        return result;
    }

    public GiveInstruction(Robot giver, Robot highRecip, Robot lowRecip) {
        this.giver = giver;
        this.highRecip = highRecip;
        this.lowRecip = lowRecip;
    }

}

class Robot {
    private Chip chip1;
    private Chip chip2;
    final int number;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Robot robot = (Robot) o;

        if (number != robot.number) return false;
        if (chip1 != null ? !chip1.equals(robot.chip1) : robot.chip1 != null) return false;
        return chip2 != null ? chip2.equals(robot.chip2) : robot.chip2 == null;
    }

    @Override
    public int hashCode() {
        int result = chip1 != null ? chip1.hashCode() : 0;
        result = 31 * result + (chip2 != null ? chip2.hashCode() : 0);
        result = 31 * result + number;
        return result;
    }

    public Robot(String number) {
        this.chip1 = null;
        this.chip2 = null;
        this.number = Integer.valueOf(number);
    }

    public void takeChip(Chip chip) {
        if ((this.number > 1000) && (number < 100003)) {
            System.err.println(this.number + " " + chip.val);
        }
        if (this.chip1 == null) {
            this.chip1 = chip;
        } else if (this.chip2 == null) {
            this.chip2 = chip;
        } else {
            throw new IllegalStateException();
        }
    }

    public boolean readyToGive() {
        return (this.chip1 != null) && (this.chip2 != null);
    }

    @Override
    public String toString() {
        String c1 = (this.chip1 != null) ? (this.chip1.val + "") : "";
        String c2 = (this.chip2 != null) ? (this.chip2.val + "") : "";
        return "n: " + number + " " + c1 + "," + c2;
    }

    public void processInstruction(GiveInstruction giveInstruction) {
        giveInstruction.lowRecip.takeChip(this.lowerChip());
        giveInstruction.highRecip.takeChip(this.higherChip());
        this.chip1 = null;
        this.chip2 = null;
    }

    private Chip lowerChip() {
        return (this.chip1.val < this.chip2.val) ? this.chip1 : this.chip2;
    }

    private Chip higherChip() {
        return (this.chip1.val < this.chip2.val) ? this.chip2 : this.chip1;
    }
}

class Chip {
    public Chip(String str) {
        this.val = Integer.valueOf(str);
    }

    final int val;
}
