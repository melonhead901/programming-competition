package aoc21;

import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;
import java.util.Scanner;

public class Day16 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String bits = buildBits(in);
        BitString bs = new BitString(bits);
        Packet packet = new Packet();
        packet.parseVersion(bs);
        packet.parsePacketId(bs);
        packet.parseConstant(bs);
        System.out.println(packet.value);
    }

    private static String buildBits(Scanner in) {
        String line = in.nextLine().toLowerCase(Locale.ROOT);
        Scanner lineScanner = new Scanner(line);
        Queue<Integer> hexNumbers = new LinkedList<>();
        while (lineScanner.hasNextInt(16)) {
            hexNumbers.add(lineScanner.nextInt(16));
        }
        StringBuilder builder = new StringBuilder();
        while (!hexNumbers.isEmpty()) {
            builder.append(Integer.toString(hexNumbers.remove(), 2));
        }
        return builder.toString();
    }

}
