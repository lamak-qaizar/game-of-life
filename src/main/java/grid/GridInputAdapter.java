package grid;

import cell.Cell;
import coordinate.Coordinate;

public class GridInputAdapter extends Grid {

    public GridInputAdapter(int[][] cells) {
        for (int row = 0; row < cells.length; row++) {
            for (int column = 0; column < cells[row].length; column++) {
                set(new Coordinate(row, column), Cell.create(cells[row][column]));
            }
        }
    }
}
