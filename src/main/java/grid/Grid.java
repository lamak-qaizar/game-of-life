package grid;

import cell.Cell;
import coordinate.Coordinate;
import coordinate.Coordinates;
import coordinate.Neighbours;
import java.util.HashMap;
import java.util.Map;

public class Grid {

    private final Map<Coordinate, Cell> cells = new HashMap<>();

    protected Grid(Grid grid) {
        this.cells.putAll(grid.cells);
    }

    public Grid() {
    }

    public Coordinates coordinates() {
        return new Coordinates(cells.keySet());
    }

    private Cell at(Coordinate coordinate) {
        return cells.getOrDefault(coordinate, Cell.OUT_OF_GRID);
    }

    public void set(Coordinate coordinate, Cell cell) {
        cells.put(coordinate, cell);
    }

    public int matchNeighbours(Coordinate coordinate, Cell cell) {
        return matchNeighbours(cell, Neighbours.from(coordinate));
    }

    private int matchNeighbours(Cell cell, Neighbours neighbours) {
        if (neighbours.isEmpty()) {
            return 0;
        }

        Coordinate neighbour = neighbours.getFirst();
        return (at(neighbour).equals(cell) ? 1 : 0)
                + matchNeighbours(cell, neighbours.excludeFirst());
    }
}
