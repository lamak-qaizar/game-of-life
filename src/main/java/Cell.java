public class Cell {

    int value;

    public Cell(int value) {
        this.value = value;
    }

    public boolean is(int value) {
        return this.value == value;
    }
}
