import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Cells {

    private final Map<Coordinate, Integer> cells = new HashMap<>();
    private final int rows;
    private final int columns;

    private static final int[][] NEIGHBOURS = new int[][]{
            {-1, -1},
            {-1, 0},
            {-1, 1},
            {0, -1},
            {0, 1},
            {1, -1},
            {1, 0},
            {1, 1}};

    private static final List<Offset> NEIGHBOUR_OFFSETS = new ArrayList() {{
        add(new Offset(-1, -1));
        add(new Offset(-1, 0));
        add(new Offset(-1, 1));
        add(new Offset(0, -1));
        add(new Offset(0, 1));
        add(new Offset(1, -1));
        add(new Offset(1, 0));
        add(new Offset(1, 1));
    }};


    public Cells(int[][] cells) {
        rows = cells.length;
        columns = cells[0].length;
        for (int row = 0; row < cells.length; row++) {
            for (int column = 0; column < cells[row].length; column++) {
                this.cells.put(new Coordinate(row, column), cells[row][column]);
            }
        }
    }

    private Cells(Map<Coordinate, Integer> cells, int rows, int columns) {
        this.cells.putAll(cells);
        this.rows = rows;
        this.columns = columns;
    }

    public Cells copy() {
        return new Cells(cells, this.rows, this.columns);
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return this.columns;
    }

    public void set(Coordinate coordinate, int value) {
        cells.put(coordinate, value);
    }

    public boolean at(Coordinate coordinate, int value) {
        return cells.get(coordinate) == value;
    }

    public int countNeighboursMatching(Coordinate coordinate, int value) {
        List<Coordinate> neighbours = NEIGHBOUR_OFFSETS.stream().map(offset -> offset.applyTo(coordinate)).collect(
                Collectors.toList());
        return countNeighboursMatching(coordinate, value, neighbours);
    }

    private int countNeighboursMatching(Coordinate coordinate, int value, int[][] neighbours) {
        if (neighbours.length > 0) {
            int[] neighbour = neighbours[0];
            Coordinate neighbourCoordinate = new Coordinate(coordinate.getRow() + neighbour[0], coordinate.getColumn() + neighbour[1]);
            if (isWithinGrid(neighbourCoordinate) &&
                    cells.get(neighbourCoordinate) == value) {
                return 1 + countNeighboursMatching(coordinate, value,
                        Arrays.copyOfRange(neighbours, 1, neighbours.length));
            } else {
                return 0 + countNeighboursMatching(coordinate, value,
                        Arrays.copyOfRange(neighbours, 1, neighbours.length));
            }
        }
        return 0;
    }

    private int countNeighboursMatching(Coordinate coordinate, int value, List<Coordinate> neighbours) {
        if (neighbours.size() > 0) {
            Coordinate neighbour = neighbours.get(0);
            if (isWithinGrid(neighbour) &&
                    cells.get(neighbour) == value) {
                return 1 + countNeighboursMatching(coordinate, value, neighbours.subList(1, neighbours.size()));
            } else {
                return 0 + countNeighboursMatching(coordinate, value, neighbours.subList(1, neighbours.size()));
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
                assert cells[row][column] == this.cells.get(new Coordinate(row, column));
            }
        }
    }
}
