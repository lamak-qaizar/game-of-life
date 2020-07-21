public abstract class Cell {

    int value;

    public static final Cell DEAD = new DeadCell();

    Cell(int value) {
        this.value = value;
    }

    public boolean isAlive() {
        return true;
    }

    public final boolean isDead() {
        return !isAlive();
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
