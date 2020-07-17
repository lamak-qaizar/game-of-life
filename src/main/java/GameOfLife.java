import java.util.Arrays;

public class GameOfLife {

    public static final int LIVING_CELL = 1;
    public static final int DEAD_CELL = 0;
    public static final int NUMER_OF_NEIGHBOURS_TO_BRING_BACK_TO_LIFE = 3;
    public static final int NUMBER_OF_NEIGHBOURS_TO_KILL_DUE_TO_UNDERPOPULATION = 2;
    public static final int NUMBER_OF_NEIGHBOURS_TO_KILL_DUE_TO_OVERPOPULATION = 3;

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
        if (livingNeighbours == NUMER_OF_NEIGHBOURS_TO_BRING_BACK_TO_LIFE) {
            cells[row][column] = LIVING_CELL;
        }
    }

    private static void checkForUnderpopulation(int[][] cells, int row, int column, int livingNeighbours) {
        if (livingNeighbours < NUMBER_OF_NEIGHBOURS_TO_KILL_DUE_TO_UNDERPOPULATION) {
            cells[row][column] = DEAD_CELL;
        }
    }

    private static void checkForOverpopulation(int[][] cells, int row, int column, int livingNeighbours) {
        if (livingNeighbours > NUMBER_OF_NEIGHBOURS_TO_KILL_DUE_TO_OVERPOPULATION) {
            cells[row][column] = DEAD_CELL;
        }
    }

    private int getNumberOfLivingNeighboursFor(int[][] cells, int row, int column) {
        int livingNeighbours = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (isNeighbour(i, j) &&
                        isWithinGrid(row, column, i, j)) {
                    if (cells[row + i][column + j] == LIVING_CELL) {
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
