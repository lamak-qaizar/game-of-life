public class Overpopulation extends Mutation {

    @Override
    public boolean check(Cells cells, Coordinate coordinate) {
        return livingNeighboursAround(cells, coordinate) > 3;
    }

    @Override
    public void doIt(Cells cells, Coordinate coordinate) {
        killCell(cells, coordinate);
    }
}
