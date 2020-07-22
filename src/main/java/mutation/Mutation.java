package mutation;

import cell.Cell;
import grid.Grid;
import coordinate.Coordinate;

public abstract class Mutation {

    protected abstract boolean itAppliesAt(Grid grid, Coordinate coordinate);

    protected abstract void applyAt(Grid grid, Coordinate coordinate);

    public void apply(Grid grid) {
        grid.coordinates().stream()
                .filter(c -> itAppliesAt(grid, c))
                .forEach(c -> applyAt(grid, c));
    }

    protected int livingNeighboursAround(Grid grid, Coordinate coordinate) {
        return grid.countMatching(coordinate, Cell.ALIVE);
    }

    protected void kill(Grid grid, Coordinate coordinate) {
        grid.set(coordinate, Cell.DEAD);
    }

    protected void revive(Grid grid, Coordinate coordinate) {
        grid.set(coordinate, Cell.ALIVE);
    }
}
