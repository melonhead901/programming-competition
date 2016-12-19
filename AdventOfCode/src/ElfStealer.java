import java.util.Scanner;

/**
 * Created by kdonohue on 12/18/16.
 */
public class ElfStealer {
    private ElfNode firstElf;
    private int elfCount;

    private ElfStealer(int numElves) {
        elfCount = numElves;
        ElfNode prev = null;
        for (int i = 0; i < numElves; i++) {
            ElfNode elfNode = new ElfNode(i, prev);
            if (prev != null) {
                prev.setInitialTarget(elfNode);
            } else {
                firstElf = elfNode;
            }
            prev = elfNode;
        }
        prev.setInitialTarget(firstElf);
        firstElf.setPrev(prev);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ElfStealer elfStealer = new ElfStealer(in.nextInt());
        System.out.println(elfStealer.executeGame().getDisplayNumber());
    }

    private ElfNode executeGame() {
        ElfNode next = firstElf;
        ElfNode target = firstElf;
        for (int i = 0; i < (elfCount / 2); i++) {
            target = target.getNext();
        }
        while (!isDone()) {
            elfCount--;
            target = next.consumerOther(target);
            if ((elfCount % 2) == 0) {
                target = target.getNext();
            }
            next = next.getNext();
        }
        return next;
    }

    private boolean isDone() {
        return (elfCount == 1);
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

    public ElfNode consumeNext() {
        this.next = next.next;
        next.prev = this;
        // System.out.println(this.getDisplayNumber() + " stealing from " + consumedTarget.getDisplayNumber());
        return this.next;
    }

    @Override
    public String toString() {
        return "ElfNode{" +
            "number=" + this.getDisplayNumber() +
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

    public int getDisplayNumber() {
        return number + 1;
    }

    public ElfNode getNext() {
        return next;
    }

    public void setPrev(ElfNode prev) {
        if (this.prev != null) {
            throw  new IllegalStateException();
        }
        this.prev = prev;
    }
}
