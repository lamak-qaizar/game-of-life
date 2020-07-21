package cell;

public class DeadCell extends Cell {

    public DeadCell() {
        super(0);
    }

    @Override
    public boolean isAlive() {
        return false;
    }
}
