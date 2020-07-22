package coordinate;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Neighbours extends Coordinates {

    private Neighbours(List<Coordinate> coordinates) {
        super(coordinates);
    }

    public boolean isEmpty() {
        return this.coordinates.size() == 0;
    }

    public Coordinate getFirst() {
        return coordinates.get(0);
    }

    public Neighbours excludeFirst() {
        return new Neighbours(coordinates.subList(1, coordinates.size()));
    }

    private static final List<Offset> NEIGHBOUR_OFFSETS = Arrays.asList(
            new Offset(-1, -1),
            new Offset(-1, 0),
            new Offset(-1, 1),
            new Offset(0, -1),
            new Offset(0, 1),
            new Offset(1, -1),
            new Offset(1, 0),
            new Offset(1, 1));

    public static Neighbours from(Coordinate coordinate) {
        List<Coordinate> neighbours = NEIGHBOUR_OFFSETS.stream()
                .map(offset -> offset.applyTo(coordinate))
                .collect(Collectors.toList());
        return new Neighbours(neighbours);
    }

    public Neighbours filter(Predicate<Coordinate> predicate) {
        List<Coordinate> coordinates = this.stream()
                .filter(predicate)
                .collect(Collectors.toList());
        return new Neighbours(coordinates);
    }

    public int count() {
        return coordinates.size();
    }

    public boolean greaterThan(int value) {
        return coordinates.size() > value;
    }

    public boolean lessThan(int value) {
        return coordinates.size() < value;
    }
}
