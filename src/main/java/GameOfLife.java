import grid.Grid;
import coordinate.Coordinate;
import grid.MutatingGrid;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import mutation.Mutation;
import mutation.Overpopulation;
import mutation.Revival;
import mutation.Underpopulation;

public class GameOfLife {

    private Grid grid;

    public GameOfLife(int cells[][]) {
        this.grid = new Grid(cells);
    }

    private static final List<Mutation> MUTATIONS = Arrays.asList(
            new Overpopulation(),
            new Underpopulation(),
            new Revival());

    public void tick() {

        MutatingGrid mutatingCells = new MutatingGrid(grid);

        for (Coordinate coordinate : allCoordinates()) {
            for (Mutation mutation : MUTATIONS) {
                mutation.mutate(mutatingCells, coordinate);
            }
        }

        this.grid = mutatingCells.mutate();
    }

    private List<Coordinate> allCoordinates() {
        return IntStream
                .range(0, grid.getRows())
                .mapToObj(this::getCoordinatesInRow)
                .flatMap(Function.identity())
                .collect(Collectors.toList());

    }

    private Stream<Coordinate> getCoordinatesInRow(int row) {
        return IntStream
                .range(0, grid.getColumns())
                .mapToObj(column -> new Coordinate(row, column));
    }

    public void assertState(int[][] cells) {
        this.grid.assertState(cells);
    }
}
