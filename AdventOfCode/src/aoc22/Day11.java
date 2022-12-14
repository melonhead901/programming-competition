package aoc22;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class Day11 {
    public static void main(String[] args) {
        List<Monkey> monkeyList = realMonkeyList();
        int ROUNDS = 20;
        for (int i = 0; i < ROUNDS; i++) {
            for (int m = 0; m < monkeyList.size(); m++) {
                Monkey monkey = monkeyList.get(m);
                List<MonkeyResult> results = monkey.process();
                for (MonkeyResult result : results) {
                    monkeyList.get(result.targetMonkey).acceptItems(result.item);
                }
                printMonkeyContents(monkeyList);
            }
        }
        monkeyList.sort(Comparator.comparingInt(Monkey::getInspectionCount).reversed());
        System.out.println(monkeyList.get(0).inspectionCount * monkeyList.get(1).inspectionCount);
    }

    private static void printMonkeyContents(List<Monkey> monkeyList) {
        for (Monkey m : monkeyList) {
            System.out.println(m);
        }
        System.out.println();
    }

    private static class MonkeyResult {
        final int item;
        final int targetMonkey;

        public MonkeyResult(int item, int targetMonkey) {
            this.item = item;
            this.targetMonkey = targetMonkey;
        }
    }

    private static List<Monkey> testMonkeyList() {
        List<Monkey> monkies = new ArrayList<>();
        monkies.add(new Monkey(Lists.newArrayList(79, 98), n -> n * 19, 23, 2, 3));
        monkies.add(new Monkey(Lists.newArrayList(54, 65, 75, 74), n -> n + 6, 19, 2, 0));
        monkies.add(new Monkey(Lists.newArrayList(79, 60, 97), n -> n * n, 13, 1, 3));
        monkies.add(new Monkey(Lists.newArrayList(74), n -> n + 3, 17, 0, 1));
        return monkies;
    }

    private static List<Monkey> realMonkeyList() {
        List<Monkey> monkies = new ArrayList<>();
        monkies.add(new Monkey(Lists.newArrayList(75, 75, 98, 97, 79, 97, 64), n -> n * 13, 19, 2, 7));
        monkies.add(new Monkey(Lists.newArrayList(50, 99, 80, 84, 65, 95), n -> n + 2, 3, 4, 5));
        monkies.add(new Monkey(Lists.newArrayList(96, 74, 68, 96, 56, 71, 75, 53), n -> n + 1, 11, 7, 3));
        monkies.add(new Monkey(Lists.newArrayList(83, 96, 86, 58, 92), n -> n + 8, 17, 6, 1));
        monkies.add(new Monkey(Lists.newArrayList(99), n -> n * n, 5, 0, 5));
        monkies.add(new Monkey(Lists.newArrayList(60, 54, 83), n -> n + 4, 2, 2, 0));
        monkies.add(new Monkey(Lists.newArrayList(77, 67), n -> n * 17, 13, 4, 1));
        monkies.add(new Monkey(Lists.newArrayList(95, 65, 58, 76), n -> n + 5, 7, 3, 6));
        return monkies;
    }

    static class Monkey {
        final List<Integer> items;
        final Function<Integer, Integer> operation;
        final int testDivide;
        final int trueTarget;
        final int falseTarget;
        public int inspectionCount;

        public int getInspectionCount() {
            return this.inspectionCount;
        }

        public Monkey(
                List<Integer> staringItems,
                Function<Integer, Integer> operation,
                int testDivide,
                int trueTarget,
                int falseTarget) {
            this.items = staringItems;
            this.operation = operation;
            this.testDivide = testDivide;
            this.trueTarget = trueTarget;
            this.falseTarget = falseTarget;
            this.inspectionCount = 0;
        }

        public List<MonkeyResult> process() {
            List<MonkeyResult> resuts = new ArrayList<>();
            for (int item : items) {
                int result = this.operation.apply(item);
                result /= 3;
                if (result % testDivide == 0) {
                    resuts.add(new MonkeyResult(result, trueTarget));
                } else {
                    resuts.add(new MonkeyResult(result, falseTarget));
                }
                inspectionCount++;
            }
            this.items.clear();
            return resuts;
        }

        public void acceptItems(Integer item) {
            this.items.add(item);
        }

        @Override
        public String toString() {
            return "Monkey{" + "items=" + items + ", inspectionCount=" + inspectionCount + '}';
        }
    }
}
