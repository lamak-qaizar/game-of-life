package coordinate;

import coordinate.Coordinate;

public class Offset extends Coordinate {

    public Offset(int x, int y) {
        super(x, y);
    }

    public Coordinate applyTo(Coordinate coordinate) {
        return new Coordinate(coordinate.getRow() + this.getRow(),
                coordinate.getColumn() + this.getColumn());
    }
}
