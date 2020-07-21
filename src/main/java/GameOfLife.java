import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class GameOfLife {

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
        return IntStream
                .range(0, cells.getRows())
                .mapToObj(this::getCoordinatesInRow)
                .flatMap(Function.identity())
                .collect(Collectors.toList());

    }

    private Stream<Coordinate> getCoordinatesInRow(int row) {
        return IntStream
                .range(0, cells.getColumns())
                .mapToObj(column -> new Coordinate(row, column));
    }

    private void deadCellChecks(Cells cellsAfterTick, Coordinate coordinate) {
        if (cells.at(coordinate).isDead()) {
            checkForBringingBackToLife(cellsAfterTick, coordinate);
        }
    }

    private void livingCellChecks(Cells cellsAfterTick, Coordinate coordinate) {
        if (cells.at(coordinate).isAlive()) {
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
        return cells.countNeighboursMatching(coordinate, Cell.ALIVE);
    }

    private void bringCellToLife(Cells cellsAfterTick, Coordinate coordinate) {
        cellsAfterTick.set(coordinate, Cell.ALIVE);
    }

    private void checkForUnderpopulation(Cells cellsAfterTick, Coordinate coordinate) {
        if (livingNeighboursAround(coordinate)
                < NUMBER_OF_NEIGHBOURS_TO_KILL_DUE_TO_UNDERPOPULATION) {
            killCell(cellsAfterTick, coordinate);
        }
    }

    private void killCell(Cells cellsAfterTick, Coordinate coordinate) {
        cellsAfterTick.set(coordinate, Cell.DEAD);
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
