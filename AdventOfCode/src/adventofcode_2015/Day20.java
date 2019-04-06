package adventofcode_2015;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Day20 {
    private final List<Event> events;

    private Day20(List<Event> events) {
        this.events = events;
    }

    public static void main(String[] args) {
        Day20 day20 = new Day20(new ArrayList<>());
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
        vals = calculateUnblockedValues(vals);

        vals += events.get(events.size() - 1).distanceFromMaxVal();

        System.out.println(vals);
    }

    private long calculateUnblockedValues(long vals) {
        int activeRestrictions = 0;
        for (int i = 0; i < (events.size() - 1); i++) {
            Event e = events.get(i);
            if (e instanceof Start) {
                activeRestrictions++;
            }
            if (e instanceof End) {
                activeRestrictions--;
                if (activeRestrictions == 0) {
                    // We know the next one has to be start because there are no open intervals.
                    vals += Event.valsBetween(events.get(i + 1), e);
                }
            }
        }
        return vals;
    }


    private void addRestriction(Restriction restriction) {
        events.add(restriction.createEventFromStart());
        events.add(restriction.createEventFromEnd());
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
    private static final double MAX_VAL = Math.pow(2, 32) - 1;

    private final long loc;

    public Event(long loc) {
        this.loc = loc;
    }

    @Override
    public int compareTo(@NotNull Event o) {
        return Long.compare(this.loc, o.loc);
    }

    public static long valsBetween(Event a, Event b) {
        return Math.abs(a.loc - (b.loc + 1));
    }

    public long distanceFromMaxVal() {
        return (long) (MAX_VAL - loc);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }

        Event event = (Event) o;

        return loc == event.loc;
    }

    @Override
    public int hashCode() {
        return (int) (loc ^ (loc >>> 32));
    }
}


class Restriction {
    private final long min;
    private final long max;

    public Restriction(String min, String max) {
        this.min = Long.valueOf(min);
        this.max = Long.valueOf(max);
    }

    @Override
    public String toString() {
        return "Restriction{" +
            "min=" + min +
            ", max=" + max +
            '}';
    }

    public Event createEventFromStart() {
        return new Start(min);
    }

    public Event createEventFromEnd() {
        return new End(max);
    }
}
