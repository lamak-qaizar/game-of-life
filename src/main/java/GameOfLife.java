import java.util.Arrays;

public class GameOfLife {

    public static final int LIVING_CELL = 1;
    public static final int DEAD_CELL = 0;
    public static final int NUMBER_OF_NEIGHBOURS_TO_BRING_BACK_TO_LIFE = 3;
    public static final int NUMBER_OF_NEIGHBOURS_TO_KILL_DUE_TO_UNDERPOPULATION = 2;
    public static final int NUMBER_OF_NEIGHBOURS_TO_KILL_DUE_TO_OVERPOPULATION = 3;

    private Cells cells;

    public GameOfLife(int cells[][]) {
        this.cells = new Cells(cells);
    }

    public void tick() {

        Cells cellsAfterTick = this.cells.copy();

        for (int row = 0; row < cells.getRows(); row++) {
            for (int column = 0; column < cells.getColumns(); column++) {
                int livingNeighbours = cells.countNeighboursMatching(row, column, LIVING_CELL);
                livingCellChecks(cellsAfterTick, row, column, livingNeighbours);
                deadCellChecks(cells, row, column, livingNeighbours, cellsAfterTick);
            }
        }

        this.cells = cellsAfterTick;
    }

    private void deadCellChecks(Cells cells, int row, int column, int livingNeighbours,
            Cells cellsAfterTick) {
        if (cells.at(row, column, DEAD_CELL)) {
            checkForBringingBackToLife(cellsAfterTick, row, column, livingNeighbours);
        }
    }

    private void livingCellChecks(Cells cellsAfterTick, int row, int column, int livingNeighbours) {
        if (cells.at(row, column, LIVING_CELL)) {
            checkForUnderpopulation(cellsAfterTick, row, column, livingNeighbours);
            checkForOverpopulation(cellsAfterTick, row, column, livingNeighbours);
        }
    }

    private void checkForBringingBackToLife(Cells cellsAfterTick, int row, int column,
            int livingNeighbours) {
        if (livingNeighbours == NUMBER_OF_NEIGHBOURS_TO_BRING_BACK_TO_LIFE) {
            cellsAfterTick.set(row, column, LIVING_CELL);
        }
    }

    private void checkForUnderpopulation(Cells cellsAfterTick, int row, int column,
            int livingNeighbours) {
        if (livingNeighbours < NUMBER_OF_NEIGHBOURS_TO_KILL_DUE_TO_UNDERPOPULATION) {
            cellsAfterTick.set(row, column, DEAD_CELL);
        }
    }

    private void checkForOverpopulation(Cells cellsAfterTick, int row, int column,
            int livingNeighbours) {
        if (livingNeighbours > NUMBER_OF_NEIGHBOURS_TO_KILL_DUE_TO_OVERPOPULATION) {
            cellsAfterTick.set(row, column, DEAD_CELL);
        }
    }

    public void assertState(int[][] cells) {
        assert Arrays.deepEquals(this.getCells(), cells);
    }

    public int[][] getCells() {
        return cells.getCells();
    }
}
