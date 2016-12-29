import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public final class Minimax {

    private Minimax() { }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        List<Integer> nums = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            nums.add(in.nextInt());
        }
        Collections.sort(nums);
        int p = in.nextInt();
        int q = in.nextInt();
        Collection<PossibleSolution> possibleSolutions = new ArrayList<>();
        for (int i = 0; i < (nums.size() - 1); i++) {
            int diff = nums.get(i + 1) - nums.get(i);
            possibleSolutions.add(new PossibleSolution(diff / 2, (diff / 2) + nums.get(i)));
        }
        possibleSolutions.add(new PossibleSolution(diffForNumClosestTo(nums, p), p));
        possibleSolutions.add(new PossibleSolution(diffForNumClosestTo(nums, q), q));
        possibleSolutions.stream().filter(x -> x.isInRange(p, q)).max(PossibleSolution::compareTo).get().printVal();
    }

    private static int diffForNumClosestTo(Collection<Integer> nums, int p) {
        return nums.stream().map(x -> Math.abs(x - p)).min(Integer::compareTo).get();
    }

    private static void solveDirectly(List<Integer> nums, int p, int q) {
        int minDiff = Integer.MIN_VALUE;
        int minVal = 0;
        for (int m = p; m <= q; m++) {
            int finalM = m;
            int diff = nums.stream().map(x -> Math.abs(finalM - x)).reduce(Integer::compareTo)
                .orElseThrow(IllegalStateException::new);
            if (diff > minDiff) {
                minDiff = diff;
                minVal = m;
            }
        }
        System.out.print(minVal);
    }
}

class PossibleSolution implements Comparable<PossibleSolution> {
    private final int diff;
    private final int val;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PossibleSolution that = (PossibleSolution) o;

        if (diff != that.diff) return false;
        return val == that.val;
    }

    @Override
    public int hashCode() {
        int result = diff;
        result = 31 * result + val;
        return result;
    }


    public PossibleSolution(int diff, int val) {
        this.diff = diff;
        this.val = val;
    }

    @Override
    public int compareTo(PossibleSolution o) {
        int diffComp = Integer.compare(this.diff, o.diff);
        return (diffComp != 0) ? diffComp : Integer.compare(o.val, this.val);
    }

    @Override
    public String toString() {
        return "PossibleSolution{" +
            "diff=" + diff +
            ", val=" + val +
            '}';
    }

    public void printVal() {
        System.out.println(val);
    }

    public boolean isInRange(int p, int q) {
        return (this.val >= p) && (this.val <= q);
    }
}
