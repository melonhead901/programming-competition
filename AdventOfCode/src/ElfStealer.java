import java.util.Scanner;

/**
 * Day 19.
 */
public class ElfStealer {
    private ElfNode firstElf;
    private int elfCount;

    private ElfStealer(int numElves) {
        elfCount = numElves;
        ElfNode prev = null;
        // Use 1-based indexing to match problem spec.
        for (int i = 1; i <= numElves; i++) {
            ElfNode elfNode = new ElfNode(i, prev);
            if (prev != null) {
                prev.setInitialTarget(elfNode);
            } else {
                firstElf = elfNode;
            }
            prev = elfNode;
        }
        prev.setInitialTarget(firstElf);
        firstElf.setInitialPrev(prev);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ElfStealer elfStealer = new ElfStealer(in.nextInt());
        elfStealer.executeGame().printDisplayNumber();
    }

    private ElfNode executeGame() {
        ElfNode next = firstElf;
        ElfNode target = firstElf;
        // Move halfway around the circle.
        for (int i = 0; i < (elfCount / 2); i++) {
            target = target.getNext();
        }
        while (!isDone()) {
            elfCount--;
            target = next.consumerOther(target);
            // Every other time advance one. This makes up for the fact that we picked
            // the left of two possible choices before (when there were an odd number).
            if ((elfCount % 2) == 0) {
                target = target.getNext();
            }
            next = next.getNext();
        }
        return next;
    }

    private boolean isDone() {
        return elfCount == 1;
    }
}

class ElfNode {
    private final int number;
    private ElfNode next;
    private ElfNode prev;

    public ElfNode(int number, ElfNode prev) {
        this.number = number;
        this.prev = prev;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }

        ElfNode elfNode = (ElfNode) o;

        return number == elfNode.number;
    }

    @Override
    public int hashCode() {
        return number;
    }

    /**
     * @return the new next
     */
    private ElfNode consumeNext() {
        this.next = next.next;
        next.prev = this;
        // System.out.println(this.getDisplayNumber() + " stealing from " + consumedTarget.getDisplayNumber());
        return this.next;
    }

    @Override
    public String toString() {
        return "ElfNode{" +
            "number=" + number +
            '}';
    }

    public ElfNode consumerOther(ElfNode target) {
        // System.out.println(this.getDisplayNumber() + " stealing from " + target.getDisplayNumber());
        return target.prev.consumeNext();
    }

    public void setInitialTarget(ElfNode target) {
        if (this.next != null) {
            throw new IllegalStateException();
        }
        this.next = target;
    }

    public void setInitialPrev(ElfNode prev) {
        if (this.prev != null) {
            throw  new IllegalStateException();
        }
        this.prev = prev;
    }

    public ElfNode getNext() {
        return next;
    }

    public void printDisplayNumber() {
        System.out.print(number);
    }
}
