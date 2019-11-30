package aoc_unknown;

import java.util.Optional;
import java.util.Scanner;

/**
 * Created by kdonohue on 12/8/16.
 */
public class Decompression {
    private final String stringToDecompress;

    public Decompression(String stringToDecompress) {
        this.stringToDecompress = stringToDecompress;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Decompression decompression = new Decompression(in.nextLine());
        System.out.println(decompression.getDecompressedLength());
    }

    private Optional<Marker> canGrabMarker(String substring) {
        if (!substring.startsWith("(")) {
            throw new IllegalStateException();
        }
        substring = substring.substring(1);
        String[] splitForLength = substring.split("x");
        if (splitForLength.length < 2) {
            return Optional.empty();
        }
        String[] splitForRepeats = splitForLength[1].split("\\)");
        if (splitForRepeats.length < 2) {
            return Optional.empty();
        }
        try {
            int length = Integer.valueOf(splitForLength[0]);
            int repeats = Integer.valueOf(splitForRepeats[0]);
            int lengthOfMarker = Marker.regenerateMarkerLength(length, repeats);
            // The minus 1 is because we stripped off the original open paren.
            String containedSection = substring.substring(lengthOfMarker - 1, (lengthOfMarker - 1) + length);
            return Optional.of(new Marker(length, repeats, containedSection));
        } catch (NumberFormatException ex) {
            return Optional.empty();
        }
    }

    public long getDecompressedLength() {
        int i = 0;
        int length = 0;
        while (i < stringToDecompress.length()) {
            if (stringToDecompress.charAt(i) == '(') {
                Optional<Marker> markerOptional = canGrabMarker(stringToDecompress.substring(i));
                if (markerOptional.isPresent()) {
                    System.err.println("Found pattern starting at" + stringToDecompress.substring(i));
                    length += markerOptional.get().getExpandedLength();
                    i += markerOptional.get().getReplacedLength();
                    continue;
                }
            }
            System.err.println("Processing " + stringToDecompress.charAt(i));
            // Continue as normal.
            i++;
            length++;
        }
        return length;
    }
}

class Marker {
    private final long length;
    private final int repeats;
    private final String containedSection;

    public Marker(int length, int repeats, String containedSection) {
        this.length = length;
        this.repeats = repeats;
        this.containedSection = containedSection;
    }

    public long getExpandedLength() {
        Decompression decompression = new Decompression(containedSection);
        return decompression.getDecompressedLength() * repeats;
    }

    public long getReplacedLength() {
        return regenerateMarkerLength(length, repeats) + length;
    }

    public static int regenerateMarkerLength(long length, int repeats) {
        return String.format("(%sx%s)", length, repeats).length();
    }
}
