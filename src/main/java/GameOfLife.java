import java.util.Arrays;

public class GameOfLife {

    private final int[][] cells;

    public GameOfLife(int cells[][]) {
        this.cells = cells;
    }

    private int getRows() {
        return this.cells.length;
    }

    private int getColumns() {
        return this.cells[0].length;
    }

    public void tick() {

        int[][] copy = getCopyOf(cells);

        for (int row = 0; row < getRows(); row++) {
            for (int column = 0; column < getColumns(); column++) {
                if (copy[row][column] == 1) {
                    int livingNeighbours = getNumberOfLivingNeighboursFor(copy, row, column);
                    checkForUnderpopulation(this.cells, row, column, livingNeighbours);
                    checkForOverpopulation(this.cells, row, column, livingNeighbours);
                }

                if (copy[row][column] == 0) {
                    int livingNeighbours = getNumberOfLivingNeighboursFor(copy, row, column);
                    checkForBringingBackToLife(this.cells, row, column, livingNeighbours);
                }
            }
        }
    }

    private int[][] getCopyOf(int[][] cells) {
        return Arrays.stream(cells).map(int[]::clone).toArray(int[][]::new);
    }

    private static void checkForBringingBackToLife(int[][] cells, int row, int column,
            int livingNeighbours) {
        if (livingNeighbours == 3) {
            cells[row][column] = 1;
        }
    }

    private static void checkForUnderpopulation(int[][] cells, int row, int column, int livingNeighbours) {
        if (livingNeighbours < 2) {
            cells[row][column] = 0;
        }
    }

    private static void checkForOverpopulation(int[][] cells, int row, int column, int livingNeighbours) {
        if (livingNeighbours > 3) {
            cells[row][column] = 0;
        }
    }

    private int getNumberOfLivingNeighboursFor(int[][] cells, int row, int column) {
        int livingNeighbours = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if ((i!=0 || j!=0) &&
                        isWithinGrid(row, column, i, j)) {
                    if (cells[row + i][column + j] == 1) {
                        livingNeighbours++;
                    }
                }
            }
        }
        return livingNeighbours;
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
