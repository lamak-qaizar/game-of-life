public class MutatingCells extends Cells {

    Cells mutating;

    public MutatingCells(Cells cells) {
        super(cells);
    }

    public void mutate(Coordinate coordinate, Cell cell) {
        mutating.set(coordinate, cell);
    }
}
