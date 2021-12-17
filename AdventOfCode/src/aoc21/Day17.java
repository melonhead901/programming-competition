package aoc21;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Day17 {
    public static final int XMAX = 500;
    public static final int YMAX = 500;

    public static void main(String[] args) {
        final int xStart = 192;
        final int xEnd = 251;
        final int yStart = -89;
        final int yEnd = -59;
         //*/
        /**
        final int xStart = 20;
        final int xEnd = 30;
        final int yStart = -10;
        final int yEnd = -5;
         //*/

        AtomicLong maxY = new AtomicLong();

        int count = 0;

        for (int dx = 0; dx < XMAX; dx++) {
            for (int dy = -1 * YMAX; dy < YMAX; dy++) {
                Optional<Long> x = executeRun(xStart, xEnd, yStart, yEnd, dx, dy);
                if (x.isPresent()) {
                    System.out.printf("%s,%s\n", dx, dy);
                    count++;
                }
            }
        }

        System.out.println(count);
    }

    private static Optional<Long> executeRun(int xStart, int xEnd, int yStart, int yEnd, int xVelocity, int yVelocity) {
        int xi = xVelocity;
        int yi = yVelocity;
        boolean hadSuccess = false;
        long xPos = 0;
        long yPos = 0;

        long maxY = 0;
        boolean printPos = false;

        while ((xPos <= xEnd) && ((xPos >= xStart) || (xVelocity > 0)) && (yPos >= yStart))
        {
            xPos += xVelocity;
            yPos += yVelocity;

            maxY = Math.max(yPos, maxY);
            if (printPos) {
                System.out.printf(" \t%s, %s\n", xPos, yPos);
            }
            if ((xPos >= xStart) && (xPos <= xEnd) && (yPos >= yStart) && (yPos <= yEnd)) {
                hadSuccess = true;
                break;
            }

            yVelocity -= 1;
            if (xVelocity < 0) {
                xVelocity += 1;
            } else  if (xVelocity > 0) {
                xVelocity -= 1;
            }
        }
        if (hadSuccess) {
            //System.out.printf("success! dx: %s dy: %s maxY: %s\n", xi, yi, maxY);
        }
        return hadSuccess ? Optional.of(maxY) : Optional.empty();
    }
}
