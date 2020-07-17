import java.util.Arrays;

public class GameOfLife {

    public static final int LIVING_CELL = 1;
    public static final int DEAD_CELL = 0;
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
                if (copy[row][column] == LIVING_CELL) {
                    int livingNeighbours = getNumberOfLivingNeighboursFor(copy, row, column);
                    checkForUnderpopulation(this.cells, row, column, livingNeighbours);
                    checkForOverpopulation(this.cells, row, column, livingNeighbours);
                }

                if (copy[row][column] == DEAD_CELL) {
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
            cells[row][column] = LIVING_CELL;
        }
    }

    private static void checkForUnderpopulation(int[][] cells, int row, int column, int livingNeighbours) {
        if (livingNeighbours < 2) {
            cells[row][column] = DEAD_CELL;
        }
    }

    private static void checkForOverpopulation(int[][] cells, int row, int column, int livingNeighbours) {
        if (livingNeighbours > 3) {
            cells[row][column] = DEAD_CELL;
        }
    }

    private int getNumberOfLivingNeighboursFor(int[][] cells, int row, int column) {
        int livingNeighbours = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (isNeighbour(i, j) &&
                        isWithinGrid(row, column, i, j)) {
                    if (cells[row + i][column + j] == 1) {
                        livingNeighbours++;
                    }
                }
            }
        }
        return livingNeighbours;
    }

    private boolean isNeighbour(int i, int j) {
        return i!=0 || j!=0;
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
