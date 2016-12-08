/**
 * Created by kdonohue on 12/8/16.
 */
class Coord {
    private final XPos xpos;
    private final YPos ypos;

    public Coord(XPos xpos, YPos ypos) {
        this.xpos = xpos;
        this.ypos = ypos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }

        Coord coord = (Coord) o;

        return xpos.equals(coord.xpos) && ypos.equals(coord.ypos);

    }

    @Override
    public String toString() {
        return String.format("{%s,%s}", xpos.xPosition, ypos.yPosition);
    }

    @Override
    public int hashCode() {
        int result = xpos.hashCode();
        result = (31 * result) + ypos.hashCode();
        return result;
    }

    public Coord advance(Direction dir, int numSteps) {
        return new Coord(this.xpos.moveIn(dir, numSteps), this.ypos.moveIn(dir, numSteps));
    }

    public int calculateDistanceFromOrigin() {
        return Math.abs(xpos.xPosition) + Math.abs(ypos.yPosition);
    }

    public Coord rotateCol(TwoFactor.MoveAmount moveAmount) {
        return new Coord(xpos, ypos.rotateRow(moveAmount));
    }

    public Coord rotateRow(TwoFactor.MoveAmount moveAmount) {
        return new Coord(xpos.rotateCol(moveAmount), ypos);
    }

    public boolean isInCol(XPos xPos) {
        return this.xpos.equals(xPos);
    }
    
    public boolean isInRow(YPos yPos) {
        return this.ypos.equals(yPos);
    }

    public void populateGrid(boolean[][] grid) {
        grid[xpos.xPosition][ypos.yPosition] = true;
    }
}
