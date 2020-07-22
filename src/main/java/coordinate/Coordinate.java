package coordinate;

public class Coordinate {

    private final int row;
    private final int column;

    public Coordinate(int row, int column) {
        this.row = row;
        this.column = column;
    }

    protected int row() {
        return row;
    }

    protected int column() {
        return column;
    }
}
