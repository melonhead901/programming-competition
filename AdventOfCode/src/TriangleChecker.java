import org.jetbrains.annotations.Contract;

import java.util.Scanner;

/**
 * Created by kdonohue on 12/16/16.
 */
public class TriangleChecker {
    private long numValidTriangles;

    public TriangleChecker() {
        numValidTriangles = 0;
    }

    public static void main(String[] args) {
        TriangleChecker triangleChecker = new TriangleChecker();
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            triangleChecker.addPotentialTriangle(in);
        }
        triangleChecker.printValidTriangleCount();

    }

    private void printValidTriangleCount() {
        System.out.print(numValidTriangles);
    }

    private void addPotentialTriangle(Scanner in) {
        int[] nums = parseInputString(in);
        if (isValidTriangle(nums)) {
            numValidTriangles++;
        }
    }

    @Contract(pure = true)
    private boolean isValidTriangle(int[] nums) {
        return ((nums[0] + nums[1]) > nums[2]) &&
            ((nums[1] + nums[2]) > nums[0]) &&
            ((nums[0] + nums[2]) > nums[1]);
    }

    private int[] parseInputString(Scanner in) {
        return new int[]{in.nextInt(), in.nextInt(), in.nextInt()};
    }

}
