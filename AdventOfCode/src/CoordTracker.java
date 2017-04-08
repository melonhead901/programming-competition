import java.util.Optional;
import java.util.Set;

/**
 * Created by kdonohue on 12/8/16.
 */
public class CoordTracker {
    private Coord location;
    private final Set<Coord> visitedCoords;

    private Coord firstRepeatedCoord;

    public CoordTracker(Coord location, Set<Coord> visitedCoords) {
        this.location = location;
        this.visitedCoords = visitedCoords;
        visitedCoords.add(location);
    }


    public int calculateDistanceFromOrigin() {
        return location.calculateDistanceFromOrigin();
    }

    @Override
    public String toString() {
        return "CoordTracker{" +
            "location=" + location +
            '}';
    }

    public void advance(Direction direction, int integer) {
        for (int i = 0; i < integer; i++) {
           location = location.advance(direction, 1);
           if ((firstRepeatedCoord == null) && visitedCoords.contains(location)) {
               firstRepeatedCoord = location;
           }
           visitedCoords.add(location);
        }
    }

    public Optional<Coord> getFirstRepeatedCoord() {
       return Optional.ofNullable(firstRepeatedCoord);
    }
}
