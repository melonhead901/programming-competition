package adventofcode_2015;

import java.util.*;

/**
 * Created by kdonohue on 12/19/16.
 */
public class Day20 {
    public static void main(String[] args) {
        Day20 day20 = new Day20();
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String line = in.nextLine();
            String[] words = line.split("-");
            Restriction restriction = new Restriction(words[0], words[1]);
            day20.addRestriction(restriction);
            day20.processRestrictions();

        }
        day20.printMinVal();
    }

    private void processRestrictions() {
        Collections.sort(restrictions, Comparator.comparingLong(o -> o.min));
        System.out.println(restrictions);
        for (Restriction restriction : restrictions) {
            if ((restriction.min <= minVal) || ((restriction.min >= minVal) && (restriction.max <= minVal))) {
                minVal = Math.max(minVal, restriction.max + 1);
            }
        }
    }

    private void printMinVal() {
        System.out.print(minVal);
    }


    long minVal = 0;
    List<Restriction> restrictions = new ArrayList<>();

    private void addRestriction(Restriction restriction) {
        restrictions.add(restriction);
    }
}

class Restriction {
    public Restriction(String min, String max) {
        this.min = Long.valueOf(min);
        this.max = Long.valueOf(max);
    }


    long min;
    long max;

    @Override
    public String toString() {
        return "Restriction{" +
            "min=" + min +
            ", max=" + max +
            '}';
    }
}
