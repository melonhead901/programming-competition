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

        }
        day20.processRestrictions();
    }

    private void processRestrictions() {
        Collections.sort(events);
        long vals = 0;
        int activeRestrictions = 0;
        for (int i = 0; i < (events.size() - 1); i++) {
            Event e = events.get(i);
            if (e instanceof Start) {
                activeRestrictions++;
            } else {
                activeRestrictions--;
                if (activeRestrictions == 0) {
                    vals += (events.get(i + 1).loc) - (e.loc + 1);
                }
            }
        }

        vals += Math.pow(2, 32) - 1 - events.get(events.size() - 1).loc;

        System.out.println(vals);

    }


    List<Event> events = new ArrayList<>();

    private void addRestriction(Restriction restriction) {
        events.add(new Start(restriction.min));
        events.add(new End(restriction.max));
    }
}

class Start extends Event {
    public Start(long loc) {
        super(loc);
    }
}

class End extends Event {
    public End(long loc) {
        super(loc);
    }
}

abstract class Event implements Comparable<Event> {
    long loc;

    public Event(long loc) {
        this.loc = loc;
    }

    @Override
    public int compareTo(Event o) {
        return Long.compare(this.loc, o.loc);
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
