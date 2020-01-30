package aoc19;

import java.util.Objects;

public class Slope {
    final int dy;
    final int dx;

    public Slope(int dy, int dx) {
        this.dy = dy / findGCD(dy, dx);
        this.dx = dx / findGCD(dy, dx);
    }

    private static int findGCD(int number1, int number2) {
        //base case
        if (number2 == 0) {
            return number1;
        }
        return findGCD(number2, number1 % number2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Slope slope = (Slope) o;
        return dy == slope.dy &&
                dx == slope.dx;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dy, dx);
    }
}
