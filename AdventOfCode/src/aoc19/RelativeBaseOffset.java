package aoc19;

public class RelativeBaseOffset {
    private long relativeBaseOffset;

    public RelativeBaseOffset() {
        this.relativeBaseOffset = 0;
    }

    public long getOffset() {
       return relativeBaseOffset;
    }

    public void adjustOffset(long adjustment) {
        this.relativeBaseOffset += adjustment;
    }
}
