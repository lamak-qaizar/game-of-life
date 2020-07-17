import java.util.Arrays;

public class Cells {

    private final int[][] cells;


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

    public boolean at(int i, int j, int value) {
        return cells[i][j] == value;
    }

    public int countNeighboursMatching(int row, int column, int value) {
        int livingNeighbours = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (isNeighbour(i, j) &&
                        isWithinGrid(row, column, i, j)) {
                    if (cells[row + i][column + j] == value) {
                        livingNeighbours++;
                    }
                }
            }
        }
        return livingNeighbours;
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


    public void assertState(int[][] cells) {
        assert Arrays.deepEquals(this.cells, cells);
    }
}
