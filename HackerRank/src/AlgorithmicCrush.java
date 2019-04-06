import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public final class AlgorithmicCrush {

    private AlgorithmicCrush() { }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // Consume N, which we don't use.
        in.nextInt();
        int m = in.nextInt();
        List<Event> events = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            int k = in.nextInt();
            events.add(new StartEvent(a, k));
            events.add(new EndEvent(b + 1, k));
        }
        events.sort(Comparator.comparing(Event::getLocation).thenComparing(Event::endComesFirst));
        events.forEach(System.err::println);
        long val = 0;
        long maxVal = Integer.MIN_VALUE;
        for (Event e : events) {
            maxVal = Math.max(maxVal, val);
            if (e instanceof StartEvent) {
                val += e.value;
            } else {
                val -= e.value;
            }
        }
        System.out.print(maxVal);

    }
}

abstract class Event {
    protected final long location;
    protected final long value;

    public Event(long location, long value) {
        this.location = location;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (location != event.location) return false;
        return value == event.value;
    }

    @Override
    public int hashCode() {
        int result = (int) (location ^ (location >>> 32));
        result = 31 * result + (int) (value ^ (value >>> 32));
        return result;
    }

    public long getLocation() {
        return this.location;
    }

    abstract int endComesFirst();
}

class StartEvent extends Event {

    public StartEvent(long location, long value) {
        super(location, value);
    }

    @Override
    int endComesFirst() {
        return 1;
    }

    @Override
    public String toString() {
        return "Start: " + value + " at " + location;
    }
}

class EndEvent extends Event {

    public EndEvent(long location, long value) {
        super(location, value);
    }

    @Override
    int endComesFirst() {
        return 0;
    }

    @Override
    public String toString() {
        return "End: " + value + " at " + location;
    }
}
