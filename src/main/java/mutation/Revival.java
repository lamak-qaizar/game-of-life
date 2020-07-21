package mutation;

import grid.Grid;
import coordinate.Coordinate;

public class Revival extends Mutation {

    @Override
    protected boolean itAppliesAt(Grid grid, Coordinate coordinate) {
        return livingNeighboursAround(grid, coordinate) == 3;
    }

    @Override
    protected void applyAt(Grid grid, Coordinate coordinate) {
        revive(grid, coordinate);
    }
}
