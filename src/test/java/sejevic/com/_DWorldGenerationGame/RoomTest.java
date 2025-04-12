package sejevic.com._DWorldGenerationGame;

import org.junit.jupiter.api.Test;
import sejevic.com._DWorldGenerationGame.core.room.Room;
import sejevic.com._DWorldGenerationGame.core.utils.Coordinate;

import java.util.HashSet;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

public class RoomTest {
    @Test
    public void getWallTest() {
        Coordinate pos1 = new Coordinate(5, 5);
        Room r = new Room(pos1, 2, 2);

        HashSet<Coordinate> walls = new HashSet<>();
        //upside
        walls.add(new Coordinate(3, 7));
        walls.add(new Coordinate(3, 6));
        walls.add(new Coordinate(3, 5));
        walls.add(new Coordinate(3, 4));
        walls.add(new Coordinate(3, 3));
        //downside
        walls.add(new Coordinate(7, 7));
        walls.add(new Coordinate(7, 6));
        walls.add(new Coordinate(7, 5));
        walls.add(new Coordinate(7, 4));
        walls.add(new Coordinate(7, 3));
        //right side
        walls.add(new Coordinate(3, 7));
        walls.add(new Coordinate(4, 7));
        walls.add(new Coordinate(5, 7));
        walls.add(new Coordinate(6, 7));
        walls.add(new Coordinate(7, 7));
        //left side
        walls.add(new Coordinate(3, 3));
        walls.add(new Coordinate(4, 3));
        walls.add(new Coordinate(5, 3));
        walls.add(new Coordinate(6, 3));
        walls.add(new Coordinate(7, 3));

        //check if all wall coordinates match
        for (Coordinate cord : r.getWalls()) {
            assertThat(walls.contains(cord)).isTrue();
        }
    }

    @Test
    public void badInputTest () {
        Coordinate pos1 = new Coordinate(5, 5);

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            Room r = new Room(pos1, 1, 1);
        });

        assertThat(e).hasMessageThat().contains("minimum size fot height and width is 2");

        e = assertThrows(IllegalArgumentException.class, () -> {
            Room r = new Room(pos1, 1, 1);
        });

        assertThat(e).hasMessageThat().contains("minimum size fot height and width is 2");

        e = assertThrows(IllegalArgumentException.class, () -> {
            Room r = new Room(null, 2, 2);
        });

        assertThat(e).hasMessageThat().contains("centerPosition was null");

        e = assertThrows(IllegalArgumentException.class, () -> {
            Room r = new Room(pos1.changeCoordinates(1, 1), 2, 2);
        });

        assertThat(e).hasMessageThat().contains("room is out of bounds");
    }

    @Test
    public void getFloorTest() {
        Coordinate pos1 = new Coordinate(5, 5);
        Room r = new Room(pos1, 2, 2);
        HashSet<Coordinate> walls = new HashSet<>();

        //fill cords
        for(int x = 4; x < 7; x++) {
            for (int y = 4; y < 7; y++) {
                walls.add(new Coordinate(x,y));
            }
        }

        //check if coordinates match
        for(Coordinate cord: r.getFloor()) {
            assertThat(walls.contains(cord)).isTrue();
        }
    }

    @Test
    public void distanceToTest() {
        Coordinate pos1 = new Coordinate(5, 5);
        Coordinate pos2 = new Coordinate(10, 10);

        Room r1 = new Room(pos1, 2, 2);
        Room r2 = new Room(pos1, 2, 2);

        //check distance between two rooms
        assertThat(r1.distanceTo(r2)).isEqualTo(0);

        r2 = new Room(pos2, 3,4);
        assertThat(r1.distanceTo(r2)).isEqualTo(7.0710678118654755);

    }

    @Test
    public void containsCoordinateTest() {
        Coordinate pos1 = new Coordinate(5, 5);
        Room r1 = new Room(pos1, 2, 2);

        //check if room contains all coordinates
        assertThat(r1.containsCoordinate(pos1.changeCoordinates(6, 6))).isTrue();
        assertThat(r1.containsCoordinate(pos1.changeCoordinates(3, 7))).isTrue();
        assertThat(r1.containsCoordinate(pos1.changeCoordinates(7, 3))).isTrue();
        assertThat(r1.containsCoordinate(pos1.changeCoordinates(4, 4))).isTrue();
        assertThat(r1.containsCoordinate(pos1.changeCoordinates(5, 5))).isTrue();
        assertThat(r1.containsCoordinate(pos1.changeCoordinates(7, 7))).isTrue();
        assertThat(r1.containsCoordinate(pos1.changeCoordinates(3, 3))).isTrue();


    }
}
