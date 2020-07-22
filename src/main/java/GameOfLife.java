import grid.Grid;
import grid.GridInputAdapter;
import grid.NextGenerationGrid;
import java.util.Arrays;
import java.util.List;
import mutation.Mutation;
import mutation.Overpopulation;
import mutation.Revival;
import mutation.Underpopulation;

public class GameOfLife {

    private Grid grid;

    public GameOfLife(int cells[][]) {
        this.grid = new GridInputAdapter(cells);
    }

    private static final List<Mutation> MUTATIONS = Arrays.asList(
            new Overpopulation(),
            new Underpopulation(),
            new Revival());

    public void tick() {
        NextGenerationGrid grid = NextGenerationGrid.from(this.grid);
        applyMutationsTo(grid);
        this.grid = grid.nextGeneration();
    }

    private void applyMutationsTo(Grid grid) {
        MUTATIONS.stream().forEach(mutation -> mutation.apply(grid));
    }

    public void assertState(int[][] cells) {
        new GridInputAdapter(cells).equals(grid);
    }
}
