import org.jetbrains.annotations.Contract;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by kdonohue on 12/16/16.
 */
public class TriangleChecker {
    private final List<int[]> lines;

    public TriangleChecker(List<int[]> lines) {
        this.lines = lines;
    }


    public static void main(String[] args) {
        TriangleChecker triangleChecker = new TriangleChecker(new ArrayList<>());
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            triangleChecker.addNextLine(in);
        }
        triangleChecker.printValidTriangleCount();

    }

    private void addNextLine(Scanner in) {
        lines.add(parseInputString(in));
    }

    private void printValidTriangleCount() {
        int numValidTriangles = 0;
        int numOverThree = lines.size() / 3;
        for (int i = 0; i < numOverThree; i++) {
            int firstRow = (i) * 3;
            if (isValidTriangle(new int[]{
                lines.get(firstRow + 0)[0],
                lines.get(firstRow + 1)[0],
                lines.get(firstRow + 2)[0] })) {
                numValidTriangles++;
            }
            if (isValidTriangle(new int[]{
                lines.get(firstRow + 0)[1],
                lines.get(firstRow + 1)[1],
                lines.get(firstRow + 2)[1] })) {
                numValidTriangles++;
            }
            if (isValidTriangle(new int[]{
                lines.get(firstRow + 0)[2],
                lines.get(firstRow + 1)[2],
                lines.get(firstRow + 2)[2] })) {
                numValidTriangles++;
            }
        }
        System.out.print(numValidTriangles);
    }

    @Contract(pure = true)
    private boolean isValidTriangle(int[] nums) {
        System.out.println("Considering " + Arrays.toString(nums));
        return ((nums[0] + nums[1]) > nums[2]) && ((nums[1] + nums[2]) > nums[0]) && ((nums[0] + nums[2]) > nums[1]);
    }

    private int[] parseInputString(Scanner in) {
        return new int[]{in.nextInt(), in.nextInt(), in.nextInt()};
    }

}
