package mutation;

import cell.Cells;
import cell.Coordinate;

public class Underpopulation extends Mutation {

    @Override
    protected boolean check(Cells cells, Coordinate coordinate) {
        return livingNeighboursAround(cells, coordinate) < 2;
    }

    @Override
    protected void doIt(Cells cells, Coordinate coordinate) {
        killCell(cells, coordinate);
    }
}
