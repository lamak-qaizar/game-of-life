import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Cells {

    private final int[][] cells;

    private Map<Coordinate, Integer> cellsMap = new HashMap<>();

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
    }

    public boolean at(Coordinate coordinate, int value) {
        return cells[coordinate.getRow()][coordinate.getColumn()] == value;
    }

    public int countNeighboursMatching(Coordinate coordinate, int value) {
        return countNeighboursMatching(coordinate, value, NEIGHBOURS);
    }

    private int countNeighboursMatching(Coordinate coordinate, int value, int[][] neighbours) {
        if (neighbours.length > 0) {
            int[] neighbour = neighbours[0];
            if (isWithinGrid(coordinate.getRow() + neighbour[0],
                    coordinate.getColumn() + neighbour[1]) &&
                    cells[coordinate.getRow() + neighbour[0]][coordinate.getColumn() + neighbour[1]]
                            == value) {
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
