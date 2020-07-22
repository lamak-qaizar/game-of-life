package grid;

import cell.Cell;
import coordinate.Coordinate;

public class NextGenerationGrid extends Grid {

    private Grid nextGeneration;

    private NextGenerationGrid(Grid grid) {
        super(grid);
        nextGeneration = new Grid(grid);
    }

    public static NextGenerationGrid from(Grid grid) {
        return new NextGenerationGrid(grid);
    }

    @Override
    public void set(Coordinate coordinate, Cell cell) {
        nextGeneration.set(coordinate, cell);
    }

    public Grid nextGeneration() {
        return nextGeneration;
    }
}
