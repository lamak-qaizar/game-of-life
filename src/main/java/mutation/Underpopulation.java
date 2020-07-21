package mutation;

import grid.Grid;
import coordinate.Coordinate;

public class Underpopulation extends Mutation {

    @Override
    protected boolean itAppliesAt(Grid grid, Coordinate coordinate) {
        return livingNeighboursAround(grid, coordinate) < 2;
    }

    @Override
    protected void applyAt(Grid grid, Coordinate coordinate) {
        kill(grid, coordinate);
    }
}
