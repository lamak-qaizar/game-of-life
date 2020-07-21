import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class GameOfLife {

    private Cells cells;

    public GameOfLife(int cells[][]) {
        this.cells = new Cells(cells);
    }

    private static final List<Mutation> MUTATIONS = Arrays.asList(
            new Overpopulation(),
            new Underpopulation(),
            new Revival());

    public void tick() {

        MutatingCells mutatingCells = new MutatingCells(cells);

        for (Coordinate coordinate : getAllCoordinate()) {
            for (Mutation mutation : MUTATIONS) {
                mutation.mutate(mutatingCells, coordinate);
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

    public void assertState(int[][] cells) {
        this.cells.assertState(cells);
    }
}
