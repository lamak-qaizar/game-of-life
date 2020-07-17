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
}
