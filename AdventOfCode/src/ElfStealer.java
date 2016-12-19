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
            ElfNode elfNode = new ElfNode(i);
            if (prev != null) {
                prev.setInitialTarget(elfNode);
            } else {
                firstElf = elfNode;
            }
            prev = elfNode;
        }
        prev.setInitialTarget(firstElf);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ElfStealer elfStealer = new ElfStealer(in.nextInt());
        System.out.println(elfStealer.executeGame().getDisplayNumber());
    }

    private void printOutRemainingElf() {
    }

    private ElfNode executeGame() {
        ElfNode next = firstElf;
        while (!isDone()) {
            elfCount--;
            next.consumeTarget();
            next = next.getTarget();
        }
        return next;
    }

    private boolean isDone() {
        return (elfCount == 1);
    }
}

class ElfNode {
    private final int number;
    private ElfNode target;

    public ElfNode(int number) {
        this.number = number;
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

    public ElfNode consumeTarget() {
        ElfNode consumedTarget = this.target;
        this.target = target.target;
        // System.out.println(this.getDisplayNumber() + " stealing from " + consumedTarget.getDisplayNumber());
        return consumedTarget;
    }

    public void setInitialTarget(ElfNode target) {
        if (this.target != null) {
            throw new IllegalStateException();
        }
        this.target = target;
    }

    public int getDisplayNumber() {
        return number + 1;
    }

    public ElfNode getTarget() {
        return target;
    }
}
