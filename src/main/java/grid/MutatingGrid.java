package grid;

import cell.Cell;
import coordinate.Coordinate;

public class MutatingGrid extends Grid {

    private Grid mutating;

    public MutatingGrid(Grid grid) {
        super(grid);
        mutating = new Grid(grid);
    }

    @Override
    public void set(Coordinate coordinate, Cell cell) {
        mutating.set(coordinate, cell);
    }

    public Grid mutate() {
        return mutating;
    }
}
