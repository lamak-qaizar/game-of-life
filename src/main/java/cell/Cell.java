package cell;

public abstract class Cell {

    int value;

    public static final Cell DEAD = new DeadCell();
    public static final Cell ALIVE = new LivingCell();
    public static final Cell OUT_OF_GRID = new OutOfGrid();

    Cell(int value) {
        this.value = value;
    }

    public static Cell create(int value) {
        return value == 0? DEAD: ALIVE;
    }
}
