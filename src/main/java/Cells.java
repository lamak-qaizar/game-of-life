import java.util.Arrays;

public class Cells {

    private final int[][] cells;

    private static final int[][] NEIGHBOURS = new int[][] {
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

    public void set(int row, int column, int value) {
        cells[row][column] = value;
    }

    public void set(Coordinate coordinate, int value) {
        cells[coordinate.getRow()][coordinate.getColumn()] = value;
    }

    public boolean at(int i, int j, int value) {
        return cells[i][j] == value;
    }

    public boolean at(Coordinate coordinate, int value) {
        return cells[coordinate.getRow()][coordinate.getColumn()] == value;
    }

    public int countNeighboursMatching(int row, int column, int value) {
        return countNeighboursMatching(row, column, value, NEIGHBOURS);
    }

    private int countNeighboursMatching(int row, int column, int value, int[][] neighbours) {
        if (neighbours.length > 0) {
            int[] neighbour = neighbours[0];
            if(isWithinGrid(row + neighbour[0], column + neighbour[1]) &&
                    cells[row + neighbour[0]][column+neighbour[1]] == value) {
                return 1 + countNeighboursMatching(row, column, value,
                        Arrays.copyOfRange(neighbours, 1, neighbours.length));
            } else {
                return 0 + countNeighboursMatching(row, column, value,
                        Arrays.copyOfRange(neighbours, 1, neighbours.length));
            }
        }
        return 0;
    }


    private boolean isNeighbour(int i, int j) {
        return i != 0 || j != 0;
    }

    private boolean isWithinGrid(int row, int column, int i, int j) {
        return row + i >= 0
                && column + j >= 0
                && row + i < getRows()
                && column + j < getColumns();
    }

    private boolean isWithinGrid(int row, int column) {
        return row >= 0
                && column >= 0
                && row < getRows()
                && column < getColumns();
    }


    public void assertState(int[][] cells) {
        assert Arrays.deepEquals(this.cells, cells);
    }
}
