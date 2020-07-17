import java.util.Arrays;

public class GameOfLife {

    private final int[][] cells;

    public GameOfLife(int cells[][]) {
        this.cells = cells;
    }

    public void tick() {

        int[][] copy = Arrays.stream(cells).map(int[]::clone).toArray(int[][]::new);

        for (int row = 0; row < cells.length; row++) {
            for (int column = 0; column < cells[row].length; column++) {
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

    private static int getNumberOfLivingNeighboursFor(int[][] cells, int row, int column) {
        int livingNeighbours = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if ((i!=0 || j!=0) &&
                        isWithinGrid(cells, row, column, i, j)) {
                    if (cells[row + i][column + j] == 1) {
                        livingNeighbours++;
                    }
                }
            }
        }
        return livingNeighbours;
    }

    private static boolean isWithinGrid(int[][] cells, int row, int column, int i, int j) {
        return row + i >= 0
                && column + j >= 0
                && row + i < cells.length
                && column + j < cells[row].length;
    }

    public void assertState(int[][] cells) {
        assert Arrays.deepEquals(this.cells, cells);
    }
}
