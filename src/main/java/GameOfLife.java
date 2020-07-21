public class GameOfLife {

    private static final int LIVING_CELL = 1;
    private static final int DEAD_CELL = 0;
    private static final int NUMBER_OF_NEIGHBOURS_TO_BRING_BACK_TO_LIFE = 3;
    private static final int NUMBER_OF_NEIGHBOURS_TO_KILL_DUE_TO_UNDERPOPULATION = 2;
    private static final int NUMBER_OF_NEIGHBOURS_TO_KILL_DUE_TO_OVERPOPULATION = 3;

    private Cells cells;

    public GameOfLife(int cells[][]) {
        this.cells = new Cells(cells);
    }

    public void tick() {

        Cells cellsAfterTick = this.cells.copy();

        for (int row = 0; row < cells.getRows(); row++) {
            for (int column = 0; column < cells.getColumns(); column++) {
                livingCellChecks(cellsAfterTick, new Coordinate(row, column));
                deadCellChecks(cellsAfterTick, new Coordinate(row, column));
            }
        }

        this.cells = cellsAfterTick;
    }

    private void deadCellChecks(Cells cellsAfterTick, Coordinate coordinate) {
        if (cells.at(coordinate.getRow(), coordinate.getColumn(), DEAD_CELL)) {
            checkForBringingBackToLife(cellsAfterTick, coordinate);
        }
    }

    private void livingCellChecks(Cells cellsAfterTick, Coordinate coordinate) {
        if (cells.at(coordinate.getRow(), coordinate.getColumn(), LIVING_CELL)) {
            checkForUnderpopulation(cellsAfterTick, coordinate);
            checkForOverpopulation(cellsAfterTick, coordinate);
        }
    }

    private void checkForBringingBackToLife(Cells cellsAfterTick, Coordinate coordinate) {
        if (livingNeighboursAround(coordinate)
                == NUMBER_OF_NEIGHBOURS_TO_BRING_BACK_TO_LIFE) {
            bringCellToLife(cellsAfterTick, coordinate);
        }
    }

    private int livingNeighboursAround(int row, int column) {
        return cells.countNeighboursMatching(row, column, LIVING_CELL);
    }

    private int livingNeighboursAround(Coordinate coordinate) {
        return cells.countNeighboursMatching(coordinate.getRow(), coordinate.getColumn(), LIVING_CELL);
    }

    private void bringCellToLife(Cells cellsAfterTick, Coordinate coordinate) {
        cellsAfterTick.set(coordinate.getRow(), coordinate.getColumn(), LIVING_CELL);
    }

    private void checkForUnderpopulation(Cells cellsAfterTick, Coordinate coordinate) {
        if (livingNeighboursAround(coordinate)
                < NUMBER_OF_NEIGHBOURS_TO_KILL_DUE_TO_UNDERPOPULATION) {
            killCell(cellsAfterTick, coordinate);
        }
    }

    private void killCell(Cells cellsAfterTick, Coordinate coordinate) {
        cellsAfterTick.set(coordinate.getRow(), coordinate.getColumn(), DEAD_CELL);
    }

    private void checkForOverpopulation(Cells cellsAfterTick, Coordinate coordinate) {
        if (livingNeighboursAround(coordinate)
                > NUMBER_OF_NEIGHBOURS_TO_KILL_DUE_TO_OVERPOPULATION) {
            killCell(cellsAfterTick, coordinate);
        }
    }

    public void assertState(int[][] cells) {
        this.cells.assertState(cells);
    }
}
