package mutation;

import grid.Grid;
import coordinate.Coordinate;

public class Underpopulation extends Mutation {

    @Override
    protected boolean mutable(Grid grid, Coordinate coordinate) {
        return livingNeighboursAround2(grid, coordinate).lessThan(2);
    }

    @Override
    protected void mutate(Grid grid, Coordinate coordinate) {
        kill(grid, coordinate);
    }
}
