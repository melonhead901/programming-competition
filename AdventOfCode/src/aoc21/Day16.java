package aoc21;

import org.apache.commons.lang3.StringUtils;

import java.util.Locale;
import java.util.Scanner;

public class Day16 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String bits = buildBits(in);
        BitString bs = new BitString(bits);
        Packet packet = new Packet(bs);
        System.out.println(packet.compute());
    }

    private static String buildBits(Scanner in) {
        String line = in.nextLine().toLowerCase(Locale.ROOT);
        StringBuilder builder = new StringBuilder();
        for (char c : line.toCharArray()) {
            int hex = Integer.parseInt(c + "", 16);
            String binary = Integer.toBinaryString(hex);
            String padded = StringUtils.leftPad(binary, 4, "0");
            builder.append(padded);
        }
        System.out.println(builder);
        return builder.toString();
    }
}
