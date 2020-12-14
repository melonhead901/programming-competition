package aoc20;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day14 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        in.next();
        in.next();
        String mask = in.next();
        in.nextLine();
        System.out.println(mask);


        ProcessedMask pm = createMasks(mask);


        Map<Integer, BitSet> memory = new HashMap<>();
        while (in.hasNextLine()) {
            String line = in.nextLine();
            if (line.startsWith("mask")) {
                // mask =
                pm = createMasks(line.substring(7));
            } else if (line.isEmpty()) {
                break;
            } else {
                processLine(line, pm, memory);
            }
        }

        System.out.println("memory " + memory);
        long output = 0;
        for (BitSet bs : memory.values()) {
            System.out.println(bs);
            System.out.println(convertToLong(bs));
            output += convertToLong(bs);
        }
        System.out.println(output);
    }

    private static ProcessedMask createMasks(String mask) {
        char[] chars = mask.toCharArray();
        BitSet andMask = new BitSet(36);
        andMask.set(0, 36);
        BitSet orMask = new BitSet(36);
        for (int i = 0; i < chars.length; i++) {
            switch (chars[i]) {
                case 'X':
                    break;
                case '1':
                    orMask.set(i);
                    break;
                case '0':
                    andMask.clear(i);
                    break;
                default:
                    throw new IllegalStateException(chars[i] + "");
            }
        }
        return new ProcessedMask(andMask, orMask);
    }

    private static long convertToLong(BitSet bitSet) {
        long longValue = 0;

        for (int bit = 0; bit < 36; bit++) {
            longValue *= 2;
            if (bitSet.get(bit)) {
                longValue++;
            }
        }
        return longValue;
    }

    private static void processLine(String line, ProcessedMask masks, Map<Integer, BitSet> memory) {
        Pattern pattern = Pattern.compile("mem\\[([0-9]+)\\] = ([0-9]+)");
        Matcher matcher = pattern.matcher(line);
        matcher.find();
        final int addr = Integer.parseInt(matcher.group(1));
        final long value = Long.parseLong(matcher.group(2));

        BitSet bits = getBits(value);

        bits.and(masks.andMask);
        bits.or(masks.orMask);

        memory.put(addr, bits);
        System.out.printf("mem[%s] = %s%n", addr, convertToLong(bits));
    }

    private static BitSet getBits(long value) {
        BitSet bs = new BitSet(36);
        for (int i = 35; i >= 0; i--) {
            if ((value % 2) == 1) {
                bs.set(i);
            }
            value /= 2;
        }
        return bs;
    }
}

class ProcessedMask {
    final BitSet andMask;
    final BitSet orMask;

    public ProcessedMask(BitSet andMask, BitSet orMask) {
        this.andMask = andMask;
        this.orMask = orMask;
    }
}
