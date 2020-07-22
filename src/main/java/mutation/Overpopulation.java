package mutation;

import grid.Grid;
import coordinate.Coordinate;

public class Overpopulation extends Mutation {

    @Override
    protected boolean mutable(Grid grid, Coordinate coordinate) {
        return livingNeighboursAround(grid, coordinate).greaterThan(3);
    }

    @Override
    protected void mutate(Grid grid, Coordinate coordinate) {
        kill(grid, coordinate);
    }
}
