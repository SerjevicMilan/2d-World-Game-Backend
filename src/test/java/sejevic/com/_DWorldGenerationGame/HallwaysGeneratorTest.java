package sejevic.com._DWorldGenerationGame;

import org.junit.jupiter.api.Test;
import sejevic.com._DWorldGenerationGame.core.hallway.Hallway;
import sejevic.com._DWorldGenerationGame.core.hallway.HallwaysGenerator;
import sejevic.com._DWorldGenerationGame.core.room.Room;
import sejevic.com._DWorldGenerationGame.core.utils.Coordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.google.common.truth.Truth.assertThat;

public class HallwaysGeneratorTest {

    @Test
    public void BasicHallwaysGeneratorTest() {
        List<Room> rooms = new ArrayList<>();
        List<Hallway> hallways;
        Random random = new Random(1);

        rooms.add(new Room(new Coordinate(5, 5), 2, 2));
        rooms.add(new Room(new Coordinate(25, 15), 2, 2));
        rooms.add(new Room(new Coordinate(10, 10), 2, 2));
        rooms.add(new Room(new Coordinate(20, 35), 2, 2));

        HallwaysGenerator hg =new HallwaysGenerator(rooms, random);
        hallways = hg.generateHallways();

        assertThat(hallways.size()).isEqualTo(3);
    }
}
