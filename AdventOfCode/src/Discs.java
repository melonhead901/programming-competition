import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Discs {
    private long position;
    private final List<Disc> discs;

    private Discs(List<Disc> discs, long position) {
        this.discs = discs;
        this.position = position;
    }

    public static void main(String[] args) {
        Discs discs = new Discs(new ArrayList<>(), 0);
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            discs.addDisc(in.nextLine());
        }
        discs.addExtraDisc();
        while (!discs.isDone()) {
            discs.advance();
        }
        discs.printCurrentPosition();
    }

    private void addExtraDisc() {
        discs.add(new Disc(11, 0, discs.size() + 1));
    }

    private void printCurrentPosition() {
        System.out.println(position);
    }

    private void advance() {
        position++;
        discs.forEach(x -> x.advance());
        this.printDiscs();
    }

    private void printDiscs() {
        discs.forEach(x -> System.out.print(x.getPosition() + " "));
        System.out.println();
    }

    private boolean isDone() {
        return discs.stream().allMatch(x -> x.isReady());
    }

    private void addDisc(String s) {
        String[] words = s.split(" ");
        discs.add(new Disc(Integer.valueOf(words[3]), Integer.valueOf(words[11].split("\\.")[0]), discs.size() + 1));
    }
}

class Disc {
    private final int slots;
    private int position;
    private final int order;

    public Disc(int slots, int position, int order) {
        this.slots = slots;
        this.position = position;
        this.order = order;
    }

    public boolean isReady() {
        return ((this.position + this.order) % this.slots) == 0;
    }

    public void advance() {
        this.position = (this.position + 1) % slots;
    }

    public int getPosition() {
        return position;
    }
}
