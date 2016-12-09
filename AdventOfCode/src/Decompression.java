import java.util.Optional;
import java.util.Scanner;

/**
 * Created by kdonohue on 12/8/16.
 */
public class Decompression {
    private long length;

    public Decompression() {
        this.length = 0L;
    }

    public static void main(String[] args) {
        Decompression decompression = new Decompression();
        Scanner in = new Scanner(System.in);
        decompression.decompress(in.nextLine());
        System.out.println(decompression.getLength());
    }

    public void decompress(String s) {
        int i = 0;
        while (i < s.length()) {
            if (s.charAt(i) == '(') {
                Optional<Marker> markerOptional = canGrabMarker(s.substring(i));
                if (markerOptional.isPresent()) {
                    System.err.println("Found pattern starting at" + s.substring(i));
                    length += markerOptional.get().getExpandedLength();
                    i += markerOptional.get().getReplacedLength();
                    continue;
                }
            }
            System.err.println("Processing " + s.charAt(i));
            // Continue as normal.
            i++;
            length++;
        }
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
            int lengthOfMarker = String.format("(%sx%s)", length, repeats).length();
            // The minus 1 is because we stripped off the original open paren.
            String containedSection = substring.substring(lengthOfMarker - 1, (lengthOfMarker - 1) + length);
            return Optional.of(new Marker(length, repeats, containedSection));
        } catch (NumberFormatException ex) {
            return Optional.empty();
        }
    }

    public long getLength() {
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
        Decompression decompression = new Decompression();
        decompression.decompress(containedSection);
        return decompression.getLength() * repeats;
    }

    public long getReplacedLength() {
        return String.format("(%sx%s)", length, repeats).length() + length;
    }
}
