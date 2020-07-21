package mutation;

import cell.Cells;
import cell.Coordinate;

public class Overpopulation extends Mutation {

    @Override
    protected boolean check(Cells cells, Coordinate coordinate) {
        return livingNeighboursAround(cells, coordinate) > 3;
    }

    @Override
    protected void doIt(Cells cells, Coordinate coordinate) {
        kill(cells, coordinate);
    }
}
