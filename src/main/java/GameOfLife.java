import java.util.Arrays;
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

    private static final List<CellMutation> MUTATIONS = Arrays.asList(new Overpopulation());

    public void tick() {

        MutatingCells mutatingCells = new MutatingCells(cells);

        for (Coordinate coordinate : getAllCoordinate()) {
            livingCellChecks(mutatingCells, coordinate);
            deadCellChecks(mutatingCells, coordinate);

            for (CellMutation cellMutation: MUTATIONS) {
                if (cellMutation.check(mutatingCells, coordinate)) {
                    cellMutation.doIt(mutatingCells, coordinate);
                }
            }
        }

        this.cells = mutatingCells.mutate();
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

    private void deadCellChecks(Cells cells, Coordinate coordinate) {
        if (cells.at(coordinate).isDead()) {
            checkForBringingBackToLife(cells, coordinate);
        }
    }

    private void livingCellChecks(Cells cells, Coordinate coordinate) {
        if (cells.at(coordinate).isAlive()) {
            checkForUnderpopulation(cells, coordinate);
            checkForOverpopulation(cells, coordinate);
        }
    }

    private void checkForBringingBackToLife(Cells cells, Coordinate coordinate) {
        if (livingNeighboursAround(coordinate)
                == NUMBER_OF_NEIGHBOURS_TO_BRING_BACK_TO_LIFE) {
            bringCellToLife(cells, coordinate);
        }
    }

    private int livingNeighboursAround(Coordinate coordinate) {
        return cells.countNeighboursMatching(coordinate, Cell.ALIVE);
    }

    private void bringCellToLife(Cells cells, Coordinate coordinate) {
        cells.set(coordinate, Cell.ALIVE);
    }

    private void checkForUnderpopulation(Cells cells, Coordinate coordinate) {
        if (livingNeighboursAround(coordinate)
                < NUMBER_OF_NEIGHBOURS_TO_KILL_DUE_TO_UNDERPOPULATION) {
            killCell(cells, coordinate);
        }
    }

    private void killCell(Cells cells, Coordinate coordinate) {
        cells.set(coordinate, Cell.DEAD);
    }

    private void checkForOverpopulation(Cells mutatingCells, Coordinate coordinate) {
        if (livingNeighboursAround(coordinate)
                > NUMBER_OF_NEIGHBOURS_TO_KILL_DUE_TO_OVERPOPULATION) {
            killCell(mutatingCells, coordinate);
        }
    }

    public void assertState(int[][] cells) {
        this.cells.assertState(cells);
    }
}
