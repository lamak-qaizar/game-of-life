package mutation;

import cell.Cell;
import cell.Cells;
import cell.Coordinate;

public abstract class Mutation {

    protected abstract boolean itAppliesAt(Cells cells, Coordinate coordinate);

    protected abstract void applyAt(Cells cells, Coordinate coordinate);

    public void mutate(Cells cells, Coordinate coordinate) {
        if (itAppliesAt(cells, coordinate)) {
            applyAt(cells, coordinate);
        }
    }

    protected int livingNeighboursAround(Cells cells, Coordinate coordinate) {
        return cells.countNeighboursMatching(coordinate, Cell.ALIVE);
    }

    protected void kill(Cells cells, Coordinate coordinate) {
        cells.set(coordinate, Cell.DEAD);
    }

    protected void revive(Cells cells, Coordinate coordinate) {
        cells.set(coordinate, Cell.ALIVE);
    }
}
