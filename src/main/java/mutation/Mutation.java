package mutation;

import cell.Cell;
import cell.Cells;
import cell.Coordinate;

public abstract class Mutation {

    protected abstract boolean check(Cells cells, Coordinate coordinate);

    protected abstract void doIt(Cells cells, Coordinate coordinate);

    public void mutate(Cells cells, Coordinate coordinate) {
        if (check(cells, coordinate)) {
            doIt(cells, coordinate);
        }
    }

    protected int livingNeighboursAround(Cells cells, Coordinate coordinate) {
        return cells.countNeighboursMatching(coordinate, Cell.ALIVE);
    }

    protected void killCell(Cells cells, Coordinate coordinate) {
        cells.set(coordinate, Cell.DEAD);
    }

    protected void revive(Cells cells, Coordinate coordinate) {
        cells.set(coordinate, Cell.ALIVE);
    }
}
