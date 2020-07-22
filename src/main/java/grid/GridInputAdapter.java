package grid;

import cell.Cell;
import coordinate.Coordinate;
import java.util.stream.IntStream;

public class GridInputAdapter extends Grid {

    public GridInputAdapter(int[][] cells) {
        IntStream.range(0, cells.length).forEach(i -> createRow(cells[i], i));
    }

    private void createRow(int[] cells, int row) {
        for (int column = 0; column < cells.length; column++) {
            set(new Coordinate(row, column), Cell.create(cells[column]));
        }
    }
}
