package grid;

import cell.Cell;
import coordinate.Coordinate;

public class MutatingGrid extends Grid {

    private Grid mutating;

    private MutatingGrid(Grid grid) {
        super(grid);
        mutating = new Grid(grid);
    }

    public static MutatingGrid from(Grid grid) {
        return new MutatingGrid(grid);
    }

    @Override
    public void set(Coordinate coordinate, Cell cell) {
        mutating.set(coordinate, cell);
    }

    public Grid mutate() {
        return mutating;
    }
}
