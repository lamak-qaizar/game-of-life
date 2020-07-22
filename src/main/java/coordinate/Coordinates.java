package coordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class Coordinates {

    List<Coordinate> coordinates = new ArrayList<>();

    public Coordinates(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinates(Set<Coordinate> coordinates) {
        this.coordinates = new ArrayList<>(coordinates);
    }

    public Stream<Coordinate> stream() {
        return coordinates.stream();
    }

}
