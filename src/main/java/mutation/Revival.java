package mutation;

import grid.Grid;
import coordinate.Coordinate;

public class Revival extends Mutation {

    @Override
    protected boolean mutable(Grid grid, Coordinate coordinate) {
        return livingNeighboursAround2(grid, coordinate).equalTo(3);
    }

    @Override
    protected void mutate(Grid grid, Coordinate coordinate) {
        revive(grid, coordinate);
    }
}
