package aoc21;

public class Day11Point extends Point {
    public Day11Point(int r, int c) {
        super(r, c);
    }

    public Day11Point(int row, int col, int val) {
        super(row, col, val);
    }

    public void increment() {
        this.value++;
    }

    public void reset() {
        this.value = 0;
    }
}
