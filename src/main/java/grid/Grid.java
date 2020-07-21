package grid;

import cell.Cell;
import coordinate.Offset;
import coordinate.Coordinate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Grid {

    private final Map<Coordinate, Cell> cells = new HashMap<>();
    private final int rows;
    private final int columns;

    private static final List<Offset> NEIGHBOUR_OFFSETS = Arrays.asList(
            new Offset(-1, -1),
            new Offset(-1, 0),
            new Offset(-1, 1),
            new Offset(0, -1),
            new Offset(0, 1),
            new Offset(1, -1),
            new Offset(1, 0),
            new Offset(1, 1));

    public Grid(int[][] cells) {
        rows = cells.length;
        columns = cells[0].length;
        for (int row = 0; row < cells.length; row++) {
            for (int column = 0; column < cells[row].length; column++) {
                this.cells.put(new Coordinate(row, column), Cell.create(cells[row][column]));
            }
        }
    }

    protected Grid(Grid grid) {
        this.cells.putAll(grid.cells);
        this.rows = grid.rows;
        this.columns = grid.columns;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return this.columns;
    }

    private Cell at(Coordinate coordinate) {
        return cells.getOrDefault(coordinate, Cell.OUT_OF_GRID);
    }

    public void set(Coordinate coordinate, Cell cell) {
        cells.put(coordinate, cell);
    }

    public int countMatching(Coordinate coordinate, Cell cell) {
        List<Coordinate> neighbours = NEIGHBOUR_OFFSETS.stream()
                .map(offset -> offset.applyTo(coordinate)).collect(
                        Collectors.toList());
        return countMatching(cell, neighbours);
    }

    private int countMatching(Cell cell, List<Coordinate> neighbours) {

        if (neighbours.size() == 0) {
            return 0;
        }

        Coordinate neighbour = neighbours.get(0);
        return (at(neighbour).equals(cell) ? 1 : 0)
                + countMatching(cell, neighbours.subList(1, neighbours.size()));
    }


    public void assertState(int[][] cells) {
        for (int row = 0; row < cells.length; row++) {
            for (int column = 0; column < cells[row].length; column++) {
                Cell cell = Cell.create(cells[row][column]);
                assert this.cells.get(new Coordinate(row, column)).equals(cell);
            }
        }
    }
}
