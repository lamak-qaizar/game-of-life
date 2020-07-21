package mutation;

import cell.Cells;
import cell.Coordinate;

public class Underpopulation extends Mutation {

    @Override
    protected boolean itAppliesAt(Cells cells, Coordinate coordinate) {
        return livingNeighboursAround(cells, coordinate) < 2;
    }

    @Override
    protected void applyAt(Cells cells, Coordinate coordinate) {
        kill(cells, coordinate);
    }
}
