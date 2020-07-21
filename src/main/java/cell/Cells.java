package cell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Cells {

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

    public Cells(int[][] cells) {
        rows = cells.length;
        columns = cells[0].length;
        for (int row = 0; row < cells.length; row++) {
            for (int column = 0; column < cells[row].length; column++) {
                this.cells.put(new Coordinate(row, column), Cell.create(cells[row][column]));
            }
        }
    }

    protected Cells(Cells cells) {
        this.cells.putAll(cells.cells);
        this.rows = cells.rows;
        this.columns = cells.columns;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return this.columns;
    }

    public void set(Coordinate coordinate, Cell cell) {
        cells.put(coordinate, cell);
    }

    public Cell at(Coordinate coordinate) {
        return cells.get(coordinate);
    }

    public int countNeighboursMatching(Coordinate coordinate, Cell cell) {
        List<Coordinate> neighbours = NEIGHBOUR_OFFSETS.stream()
                .map(offset -> offset.applyTo(coordinate)).collect(
                        Collectors.toList());
        return countNeighboursMatching(cell, neighbours);
    }

    private int countNeighboursMatching(Cell cell,
            List<Coordinate> neighbours) {
        if (neighbours.size() > 0) {
            Coordinate neighbour = neighbours.get(0);
            if (isWithinGrid(neighbour) &&
                    cells.get(neighbour).equals(cell)) {
                return 1 + countNeighboursMatching(cell,
                        neighbours.subList(1, neighbours.size()));
            } else {
                return 0 + countNeighboursMatching(cell,
                        neighbours.subList(1, neighbours.size()));
            }
        }
        return 0;
    }


    private boolean isWithinGrid(Coordinate coordinate) {
        return cells.containsKey(coordinate);
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
