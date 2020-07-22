package coordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Coordinates {

    List<Coordinate> coordinates = new ArrayList<>();

    private Coordinates(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }

    public static Coordinates from(int rows, int columns) {
        return new Coordinates(allCoordinates(rows, columns));
    }

    public Stream<Coordinate> stream() {
        return coordinates.stream();
    }

    private static List<Coordinate> allCoordinates(int rows, int columns) {
        return IntStream
                .range(0, rows)
                .mapToObj(row -> getCoordinatesInRow(row, columns))
                .flatMap(Function.identity())
                .collect(Collectors.toList());
    }

    private static Stream<Coordinate> getCoordinatesInRow(int row, int columns) {
        return IntStream
                .range(0, columns)
                .mapToObj(column -> new Coordinate(row, column));
    }
}
