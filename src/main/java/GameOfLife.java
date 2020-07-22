import grid.Grid;
import grid.GridIOAdapter;
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
        this.grid = new GridIOAdapter(cells);
    }

    private static final List<Mutation> MUTATIONS = Arrays.asList(
            new Overpopulation(),
            new Underpopulation(),
            new Revival());

    public void tick() {
        MutatingGrid grid = MutatingGrid.from(this.grid);
        applyMutationsTo(grid);
        this.grid = grid.mutate();
    }

    private void applyMutationsTo(Grid grid) {
        MUTATIONS.stream().forEach(grid::apply);
    }

    public void assertState(int[][] cells) {
        new GridIOAdapter(cells).equals(grid);
    }
}
