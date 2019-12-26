package aoc19;

public class RelativeBaseOffset {
    private int relativeBaseOffset;

    public RelativeBaseOffset() {
        this.relativeBaseOffset = 0;
    }

    public int getOffset() {
       return relativeBaseOffset;
    }

    public void adjustOffset(int adjustment) {
        this.relativeBaseOffset += adjustment;
    }
}
