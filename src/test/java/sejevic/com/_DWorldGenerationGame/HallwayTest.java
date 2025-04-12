package sejevic.com._DWorldGenerationGame;

import sejevic.com._DWorldGenerationGame.core.hallway.Hallway;
import org.junit.jupiter.api.Test;
import sejevic.com._DWorldGenerationGame.core.room.Room;
import sejevic.com._DWorldGenerationGame.core.utils.Coordinate;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class HallwayTest {
    @Test
    public void generatePathTest() {
        Coordinate pos1 = new Coordinate(5, 5);
        Coordinate pos2 = new Coordinate(12,12);

        Room r1 = new Room(pos1, 2, 2);
        Room r2 = new Room(pos2, 3, 3);

        List<Room> rooms = new ArrayList<>();
        rooms.add(r1);
        rooms.add(r2);


        List<Coordinate> expectedPath = new ArrayList<>();
        expectedPath.add(new Coordinate(5, 5));
        expectedPath.add(new Coordinate(6, 5));
        expectedPath.add(new Coordinate(6, 6));
        expectedPath.add(new Coordinate(7, 6));
        expectedPath.add(new Coordinate(7, 7));
        expectedPath.add(new Coordinate(8, 7));
        expectedPath.add(new Coordinate(8, 8));
        expectedPath.add(new Coordinate(9, 8));
        expectedPath.add(new Coordinate(9, 9));
        expectedPath.add(new Coordinate(10, 9));
        expectedPath.add(new Coordinate(10, 10));
        expectedPath.add(new Coordinate(11, 10));
        expectedPath.add(new Coordinate(11, 11));
        expectedPath.add(new Coordinate(12, 11));
        expectedPath.add(new Coordinate(12, 12));

        Hallway hw1 = new Hallway(r1.getCenter(), r2.getCenter(), rooms);
        Hallway hw2 = new Hallway(r2.getCenter(), r1.getCenter(), rooms);

        assertThat(hw1.generateZigZagPath()).isEqualTo(expectedPath);

        expectedPath = new ArrayList<>();
        expectedPath.add(new Coordinate(12, 12));
        expectedPath.add(new Coordinate(11, 12));
        expectedPath.add(new Coordinate(11, 11));
        expectedPath.add(new Coordinate(10, 11));
        expectedPath.add(new Coordinate(10, 10));
        expectedPath.add(new Coordinate(9, 10));
        expectedPath.add(new Coordinate(9, 9));
        expectedPath.add(new Coordinate(8, 9));
        expectedPath.add(new Coordinate(8, 8));
        expectedPath.add(new Coordinate(7, 8));
        expectedPath.add(new Coordinate(7, 7));
        expectedPath.add(new Coordinate(6, 7));
        expectedPath.add(new Coordinate(6, 6));
        expectedPath.add(new Coordinate(5, 6));
        expectedPath.add(new Coordinate(5, 5));

        assertThat(hw2.generateZigZagPath()).isEqualTo(expectedPath);

        expectedPath = new ArrayList<>();
        expectedPath.add(new Coordinate(5, 5));
        expectedPath.add(new Coordinate(5, 6));
        expectedPath.add(new Coordinate(5, 7));
        expectedPath.add(new Coordinate(5, 8));
        expectedPath.add(new Coordinate(5, 9));
        expectedPath.add(new Coordinate(5, 10));
        expectedPath.add(new Coordinate(5, 11));
        expectedPath.add(new Coordinate(5, 12));
        expectedPath.add(new Coordinate(6, 12));
        expectedPath.add(new Coordinate(7, 12));
        expectedPath.add(new Coordinate(8, 12));
        expectedPath.add(new Coordinate(9, 12));
        expectedPath.add(new Coordinate(10, 12));
        expectedPath.add(new Coordinate(11, 12));

        Hallway hw3 = new Hallway(r1.getCenter(), r2.getCenter(), rooms);
        assertThat(hw3.generateLShapePath()).isEqualTo(expectedPath);
    }

}
