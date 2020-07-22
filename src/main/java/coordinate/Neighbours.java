package coordinate;

import java.util.Arrays;
import java.util.List;
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
}
