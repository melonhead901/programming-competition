package aoc21;

public class Packet {
    int version;
    int packetId;
    long value;

    public Packet() {
    }

    void parseVersion(BitString bs) {
        this.version = bs.consume(3);
    }

    void parsePacketId(BitString bs) {
        this.packetId = bs.consume(3);
    }

    public long parseConstant(BitString bs) {
        StringBuilder bits = new StringBuilder();
        boolean shouldContinue = true;
        while (shouldContinue) {
            int lead = bs.consume(1);
            shouldContinue = lead == 1;
            bits.append(bs.consumeStr(4));
        }
        value = Long.valueOf(bits.toString(), 2);
        return value;
    }
}
