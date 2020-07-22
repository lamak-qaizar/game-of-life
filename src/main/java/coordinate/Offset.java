package coordinate;

public class Offset extends Coordinate {

    public Offset(int x, int y) {
        super(x, y);
    }

    public Coordinate applyTo(Coordinate coordinate) {
        return new Coordinate(coordinate.row() + this.row(),
                coordinate.column() + this.column());
    }
}
