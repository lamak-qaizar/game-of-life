package grid;

import cell.Cell;
import coordinate.Coordinate;
import java.util.stream.IntStream;

public class GridInputAdapter extends Grid {

    public GridInputAdapter(int[][] cells) {
        IntStream.range(0, cells.length).forEach(row -> createRow(cells[row], row));
    }

    private void createRow(int[] cellsInRow, int row) {
        IntStream.range(0, cellsInRow.length)
                .forEach(column -> set(new Coordinate(row, column), Cell.create(cellsInRow[column])));
    }
}
