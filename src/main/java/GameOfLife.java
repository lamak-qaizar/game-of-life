import coordinate.Coordinates;
import grid.Grid;
import grid.MutatingGrid;
import java.util.Arrays;
import java.util.List;
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
        MutatingGrid grid = MutatingGrid.from(this.grid);
        applyMutations(grid);
        this.grid = grid.mutate();
    }

    private void applyMutations(MutatingGrid grid) {
        for (Mutation mutation : MUTATIONS) {
            grid.apply(mutation);
        }
    }

    public void assertState(int[][] cells) {
        this.grid.assertState(cells);
    }
}
