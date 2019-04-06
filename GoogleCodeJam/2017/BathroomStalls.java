import com.google.common.collect.Iterables;

import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * Created by kdonohue on 4/8/17.
 */
public class BathroomStalls {

    public static final int SMALL = -1000000;
    public static final int LARGE = 1000000;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numCases = in.nextInt();
        for (int i = 1; i <= numCases; i++) {
            System.out.println("Case #" + i + ": " + processCase(in));
        }
    }

    static int maxSeenLSRS;
    static int minSeenLSRS;

    private static class SeatVal {
        private int dl;
        private int dr;
        final int val;


        public int getVal() {
            return val;
        }

        public SeatVal(int val) {
            this.val = val;
        }

        public void setDl(int dl) {
            this.dl = dl;
        }

        public int maxDlDr() {
            return Math.max(dl, dr);
        }

        public int minDlDr() {
            return Math.min(dl, dr);
        }

        public void setDr(int dr) {
            this.dr = dr;
        }

    }

    static SeatVal[] seatValArray;
    static TreeSet<SeatVal> seatValSet;

    private static String processCase(Scanner in) {
        int n = in.nextInt();
        int k = in.nextInt();
        boolean[] occupied = new boolean[n];
        int[] distL = new int[n];
        int[] distR = new int[n];
        seatValArray = new SeatVal[distL.length];
        for (int i = 0; i < distL.length; i++) {
            seatValArray[i] = new SeatVal(i);
        }
        seatValSet = new TreeSet<>(Comparator.comparing(SeatVal::maxDlDr).reversed().thenComparing
                (SeatVal::minDlDr).reversed().thenComparing(SeatVal::getVal));
        recalculateDistL(-1, distL, occupied);
        recalculateDistR(n, distR, occupied);
        for (int i = 0; i < k; i++) {
            int seatToOccupy = chooseSeatToOccupy(occupied, distL, distR);
            occupied[seatToOccupy] = true;
            recalculateDistL(seatToOccupy, distL, occupied);
            recalculateDistR(seatToOccupy, distR, occupied);
        }

        return String.format("%s %s", maxSeenLSRS, minSeenLSRS);

    }

    private static int chooseSeatToOccupy(boolean[] occupied, int[] distL, int[] distR) {
        /*
        maxSeenLSRS = SMALL;
        minSeenLSRS = SMALL;
        int seat = 0;
        for (int i = 0; i < distL.length; i++) {
            if (occupied[i]) {
                continue;
            }
            int myMinLSRS = Math.min(distL[i], distR[i]);
            int myMaxLSRS = Math.max(distL[i], distR[i]);
            if (myMinLSRS > minSeenLSRS) {
                minSeenLSRS = myMinLSRS;
                maxSeenLSRS = myMaxLSRS;
                seat = i;
            } else if (myMinLSRS == minSeenLSRS) {
                if (myMaxLSRS > maxSeenLSRS) {
                    maxSeenLSRS = myMaxLSRS;
                    seat = i;
                }
            }
        }
        */
        SeatVal seatVal = Iterables.getFirst(seatValSet, null);
        seatValSet.remove(seatVal);
        maxSeenLSRS = seatVal.maxDlDr();
        minSeenLSRS = seatVal.minDlDr();
        return seatVal.val;
    }

    private static void recalculateDistR(int start, int[] distR, boolean[] occupied) {
        int runLength = 0;
        for (int i = start - 1; i > 0; i--) {
            distR[i] = runLength;
            SeatVal seatVal = seatValArray[i];
            seatValSet.remove(seatVal);
            seatVal.setDr(runLength);
            seatValSet.add(seatVal);
            if (occupied[i]) {
                break;
            } else {
                runLength++;
            }
        }
    }

    private static void recalculateDistL(int start, int[] distL, boolean[] occupied) {
        int runLength = 0;
        for (int i = start + 1; i < distL.length; i++) {
            distL[i] = runLength;
            SeatVal seatVal = seatValArray[i];
            seatValSet.remove(seatVal);
            seatVal.setDl(runLength);
            seatValSet.add(seatVal);
            if (occupied[i]) {
                break;
            } else {
                runLength++;
            }
        }
    }
}
