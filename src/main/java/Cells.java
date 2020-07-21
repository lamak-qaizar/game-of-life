import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Cells {

    private final int[][] cells;

    private final Map<Coordinate, Integer> cellsMap = new HashMap<>();

    private static final int[][] NEIGHBOURS = new int[][]{
            {-1, -1},
            {-1, 0},
            {-1, 1},
            {0, -1},
            {0, 1},
            {1, -1},
            {1, 0},
            {1, 1}};


    public Cells(int[][] cells) {
        this.cells = createCopyOf(cells);
        for (int row = 0; row < cells.length; row++) {
            for (int column = 0; column < cells[row].length; column++) {
                cellsMap.put(new Coordinate(row, column), cells[row][column]);
            }
        }
    }

    public Cells copy() {
        return new Cells(createCopyOf(this.cells));
    }

    private int[][] createCopyOf(int[][] cells) {
        return Arrays.stream(cells).map(int[]::clone).toArray(int[][]::new);
    }

    public int getRows() {
        return cells.length;
    }

    public int getColumns() {
        return cells[0].length;
    }

    public void set(Coordinate coordinate, int value) {
        cells[coordinate.getRow()][coordinate.getColumn()] = value;
        cellsMap.put(coordinate, value);
    }

    public boolean at(Coordinate coordinate, int value) {
        return cellsMap.get(coordinate) == value;
    }

    public int countNeighboursMatching(Coordinate coordinate, int value) {
        return countNeighboursMatching(coordinate, value, NEIGHBOURS);
    }

    private int countNeighboursMatching(Coordinate coordinate, int value, int[][] neighbours) {
        if (neighbours.length > 0) {
            int[] neighbour = neighbours[0];
            Coordinate neighbourCoordinate = new Coordinate(coordinate.getRow() + neighbour[0], coordinate.getColumn() + neighbour[1]);
            if (isWithinGrid(neighbourCoordinate) &&
                    cellsMap.get(neighbourCoordinate) == value) {
                return 1 + countNeighboursMatching(coordinate, value,
                        Arrays.copyOfRange(neighbours, 1, neighbours.length));
            } else {
                return 0 + countNeighboursMatching(coordinate, value,
                        Arrays.copyOfRange(neighbours, 1, neighbours.length));
            }
        }
        return 0;
    }


    private boolean isWithinGrid(int row, int column) {
        return isRowWithinGrid(row)
                && isColumnWithinGrid(column);
    }

    private boolean isWithinGrid(Coordinate coordinate) {
        return isRowWithinGrid(coordinate.getRow())
                && isColumnWithinGrid(coordinate.getColumn());
    }

    private boolean isColumnWithinGrid(int column) {
        return column >= 0
                && column < getColumns();
    }

    private boolean isRowWithinGrid(int row) {
        return row >= 0
                && row < getRows();
    }


    public void assertState(int[][] cells) {
        assert Arrays.deepEquals(this.cells, cells);
    }
}
