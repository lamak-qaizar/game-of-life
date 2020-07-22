package grid;

import cell.Cell;
import coordinate.Coordinate;
import coordinate.Coordinates;
import coordinate.Neighbours;
import java.util.HashMap;
import java.util.Map;
import mutation.Mutation;

public class Grid {

    private final Map<Coordinate, Cell> cells = new HashMap<>();

    public Grid(int[][] cells) {
        for (int row = 0; row < cells.length; row++) {
            for (int column = 0; column < cells[row].length; column++) {
                this.cells.put(new Coordinate(row, column), Cell.create(cells[row][column]));
            }
        }
    }

    protected Grid(Grid grid) {
        this.cells.putAll(grid.cells);
    }

    public Grid() {}

    public void apply(Mutation mutation) {
        coordinates().stream().forEach(coordinate -> mutation.apply(this, coordinate));
    }

    private Coordinates coordinates() {
        return new Coordinates(cells.keySet());
    }

    private Cell at(Coordinate coordinate) {
        return cells.getOrDefault(coordinate, Cell.OUT_OF_GRID);
    }

    public void set(Coordinate coordinate, Cell cell) {
        cells.put(coordinate, cell);
    }

    public int countMatching(Coordinate coordinate, Cell cell) {
        return countMatching(cell, Neighbours.from(coordinate));
    }

    private int countMatching(Cell cell, Neighbours neighbours) {
        if (neighbours.isEmpty()) {
            return 0;
        }

        Coordinate neighbour = neighbours.getFirst();
        return (at(neighbour).equals(cell) ? 1 : 0)
                + countMatching(cell, neighbours.excludeFirst());
    }


    public void assertState(int[][] cells) {
        Grid grid = new Grid(cells);
        assert this.cells.equals(grid.cells);
    }
}
