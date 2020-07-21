public abstract class CellMutation {

    public abstract boolean check(Cells cells, Coordinate coordinate);

    public abstract void doIt(Cells cells, Coordinate coordinate);

    protected int livingNeighboursAround(Cells cells, Coordinate coordinate) {
        return cells.countNeighboursMatching(coordinate, Cell.ALIVE);
    }

    protected void killCell(Cells cells, Coordinate coordinate) {
        cells.set(coordinate, Cell.DEAD);
    }
}
