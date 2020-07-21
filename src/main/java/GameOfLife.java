import java.util.ArrayList;
import java.util.List;

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

        for (Coordinate coordinate : getAllCoordinate()) {
            livingCellChecks(cellsAfterTick, coordinate);
            deadCellChecks(cellsAfterTick, coordinate);
        }

        this.cells = cellsAfterTick;
    }

    private List<Coordinate> getAllCoordinate() {
        List<Coordinate> coordinates = new ArrayList<>();
        for (int row = 0; row < cells.getRows(); row++) {
            for (int column = 0; column < cells.getColumns(); column++) {
                coordinates.add(new Coordinate(row, column));
            }
        }
        return coordinates;
    }

    private void deadCellChecks(Cells cellsAfterTick, Coordinate coordinate) {
        if (cells.at(coordinate, DEAD_CELL)) {
            checkForBringingBackToLife(cellsAfterTick, coordinate);
        }
    }

    private void livingCellChecks(Cells cellsAfterTick, Coordinate coordinate) {
        if (cells.at(coordinate, LIVING_CELL)) {
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

    private int livingNeighboursAround(Coordinate coordinate) {
        return cells.countNeighboursMatching(coordinate, LIVING_CELL);
    }

    private void bringCellToLife(Cells cellsAfterTick, Coordinate coordinate) {
        cellsAfterTick.set(coordinate, LIVING_CELL);
    }

    private void checkForUnderpopulation(Cells cellsAfterTick, Coordinate coordinate) {
        if (livingNeighboursAround(coordinate)
                < NUMBER_OF_NEIGHBOURS_TO_KILL_DUE_TO_UNDERPOPULATION) {
            killCell(cellsAfterTick, coordinate);
        }
    }

    private void killCell(Cells cellsAfterTick, Coordinate coordinate) {
        cellsAfterTick.set(coordinate, DEAD_CELL);
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
