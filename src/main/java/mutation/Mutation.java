package mutation;

import cell.Cell;
import grid.Grid;
import coordinate.Coordinate;

public abstract class Mutation {

    protected abstract boolean mutable(Grid grid, Coordinate coordinate);

    protected abstract void mutate(Grid grid, Coordinate coordinate);

    public void apply(Grid grid) {
        grid.coordinates().stream()
                .filter(c -> mutable(grid, c))
                .forEach(c -> mutate(grid, c));
    }

    protected int livingNeighboursAround(Grid grid, Coordinate coordinate) {
        return grid.matchNeighbours(coordinate, Cell.ALIVE);
    }

    protected void kill(Grid grid, Coordinate coordinate) {
        grid.set(coordinate, Cell.DEAD);
    }

    protected void revive(Grid grid, Coordinate coordinate) {
        grid.set(coordinate, Cell.ALIVE);
    }
}
