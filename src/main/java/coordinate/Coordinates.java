package coordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Coordinates {

    List<Coordinate> coordinates = new ArrayList<>();

    protected Coordinates(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }

    public Stream<Coordinate> stream() {
        return coordinates.stream();
    }

}
