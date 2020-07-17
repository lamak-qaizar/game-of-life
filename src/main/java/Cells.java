import java.util.Arrays;

public class Cells {

    private final int[][] cells;


    public Cells(int[][] cells) {
        this.cells = cells;
    }

    public Cells copy() {
        return new Cells(createCopyOf(this.cells));
    }

    private int[][] createCopyOf(int[][] cells) {
        return Arrays.stream(cells).map(int[]::clone).toArray(int[][]::new);
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

    public boolean at(int i, int j, int value) {
        return cells[i][j] == value;
    }
}
