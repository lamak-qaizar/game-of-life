public class Cells {

    private final int[][] cells;


    public Cells(int[][] cells) {
        this.cells = cells;
    }

    public int[][] getCells() {
        return cells;
    }

    public int getRows() {
        return this.getCells().length;
    }

    public int getColumns() {
        return this.getCells()[0].length;
    }

    public void killCell(int row, int column) {
        this.getCells()[row][column] = GameOfLife.DEAD_CELL;
    }

    public void reviveCell(int row, int column) {
        this.getCells()[row][column] = GameOfLife.LIVING_CELL;
    }
}
