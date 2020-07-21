public abstract class Cell {

    int value;

    Cell(int value) {
        this.value = value;
    }

    public static Cell create(int value) {
        if (value == 0) {
            return new DeadCell();
        }
        return new LivingCell();
    }

    public boolean is(int value) {
        return this.value == value;
    }
}
