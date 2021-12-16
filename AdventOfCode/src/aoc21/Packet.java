package aoc21;

import java.util.ArrayList;
import java.util.List;

public class Packet {
    enum PacketId {
        CONSTANT,
        OPERATOR,
        SUM,
        MULT,
        MIN,
        MAX,
        GT,
        LT,
        EQ,
    }
    int version;
    PacketId packetId;
    long value;
    private final BitString bs;
    private final List<Packet> subpackets;

    public Packet(BitString bs) {
        this.bs = bs;
        this.subpackets = new ArrayList<>();
    }

    void parseVersion() {
        this.version = bs.consume(3);
    }

    void parsePacketId() {
        int val = bs.consume(3);
        if (val == 4) {
            packetId = PacketId.CONSTANT;
        } else if (val == 0) {
            packetId = PacketId.SUM;
        } else if (val == 1) {
            packetId = PacketId.MULT;
        } else if (val == 2) {
            packetId = PacketId.MIN;
        } else if (val == 3) {
            packetId = PacketId.MAX;
        } else if (val == 5) {
            packetId = PacketId.GT;
        } else if (val == 6) {
            packetId = PacketId.LT;
        } else if (val == 7) {
            packetId = PacketId.EQ;
        } else {
            packetId = PacketId.OPERATOR;
        }
    }

    public void parse() {
        parseVersion();
        parsePacketId();
        switch (packetId) {
            case CONSTANT -> parseConstant();
            default -> parseOperator();
        }
    }

    private void parseOperator() {
        int lengthTypeId = bs.consume(1);
        switch (lengthTypeId) {
            case 0 -> {
                int length = packetsLength();
                BitString substring = new BitString(bs.consumeStr(length));
                while (substring.hasNext()) {
                    Packet packet = new Packet(substring);
                    packet.parse();
                    this.subpackets.add(packet);
                }
            }
            case 1 -> {
                int packetsCounts = packetsCount();
                for (int i = 0; i < packetsCounts; i++) {
                    Packet p = new Packet(bs);
                    p.parse();
                    this.subpackets.add(p);
                }
            }
        }
    }

    private int packetsLength() {
        return bs.consume(15);
    }

    private int packetsCount() {
        return bs.consume(11);
    }

    public long parseConstant() {
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

    public long packetCountSum() {
        if (this.version == 4) {
            return this.value;
        }
        return this.subpackets.stream().map(Packet::packetCountSum).reduce(0L, Long::sum);
    }

    public long compute() {
        switch (packetId) {
            case CONSTANT -> {
                return this.value;
            }
            case OPERATOR -> throw new RuntimeException();
            case SUM -> {
                return this.subpackets.stream().map(Packet::compute).reduce(0L, Long::sum);
            }
            case MULT -> {
                return this.subpackets.stream().map(Packet::compute).reduce(1L, (a,b) -> a*b);
            }
            case MIN -> {
                return this.subpackets.stream().map(Packet::compute).reduce(Long.MAX_VALUE, Long::min);
            }
            case MAX -> {
                return this.subpackets.stream().map(Packet::compute).reduce(Long.MIN_VALUE, Long::max);
            }
            case GT -> {
                if (subpackets.size() != 2) {
                    throw new IllegalStateException();
                }
                return (this.subpackets.get(0).compute() > this.subpackets.get(1).compute()) ? 1 : 0;
            }
            case LT -> {
                if (subpackets.size() != 2) {
                    throw new IllegalStateException();
                }
                return (this.subpackets.get(0).compute() < this.subpackets.get(1).compute()) ? 1 : 0;
            }
            case EQ -> {
                if (subpackets.size() != 2) {
                    throw new IllegalStateException();
                }
                return (this.subpackets.get(0).compute() == this.subpackets.get(1).compute()) ? 1 : 0;
            }
            default -> throw new IllegalStateException();
        }
    }

    public long versionSum() {
        if (packetId == PacketId.CONSTANT) {
            return this.version;
        }
        return this.version + this.subpackets.stream().map(Packet::versionSum).reduce(0L, Long::sum);
    }
}
