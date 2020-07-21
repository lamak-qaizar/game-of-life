package cell;

public class MutatingCells extends Cells {

    private Cells mutating;

    public MutatingCells(Cells cells) {
        super(cells);
        mutating = new Cells(cells);
    }

    @Override
    public void set(Coordinate coordinate, Cell cell) {
        mutating.set(coordinate, cell);
    }

    public Cells mutate() {
        return mutating;
    }
}
