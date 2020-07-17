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
                deadCellChecks(cellsAfterTick, row, column, livingNeighbours);
            }
        }

        this.cells = cellsAfterTick;
    }

    private void deadCellChecks(Cells cellsAfterTick, int row, int column, int livingNeighbours) {
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
            bringCellToLife(cellsAfterTick, row, column);
        }
    }

    private void bringCellToLife(Cells cellsAfterTick, int row, int column) {
        cellsAfterTick.set(row, column, LIVING_CELL);
    }

    private void checkForUnderpopulation(Cells cellsAfterTick, int row, int column,
            int livingNeighbours) {
        if (livingNeighbours < NUMBER_OF_NEIGHBOURS_TO_KILL_DUE_TO_UNDERPOPULATION) {
            killCell(cellsAfterTick, row, column);
        }
    }

    private void killCell(Cells cellsAfterTick, int row, int column) {
        cellsAfterTick.set(row, column, DEAD_CELL);
    }

    private void checkForOverpopulation(Cells cellsAfterTick, int row, int column,
            int livingNeighbours) {
        if (livingNeighbours > NUMBER_OF_NEIGHBOURS_TO_KILL_DUE_TO_OVERPOPULATION) {
            killCell(cellsAfterTick, row, column);
        }
    }

    public void assertState(int[][] cells) {
        this.cells.assertState(cells);
    }
}
