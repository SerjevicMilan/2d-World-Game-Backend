package sejevic.com._DWorldGenerationGame;

import org.junit.jupiter.api.Test;
import sejevic.com._DWorldGenerationGame.core.room.Room;
import sejevic.com._DWorldGenerationGame.core.room.RoomGenerator;

import java.util.List;
import java.util.Random;

import static com.google.common.truth.Truth.assertThat;

public class RoomGeneratorTest {
    @Test
    public void basicRoomGeneratorTest() {
        RoomGenerator Rg = new RoomGenerator(new Random(2), 50, 80);
        List<Room> rooms = Rg.generateRooms(0.25);

        assertThat(rooms.size()).isEqualTo(8);
    }
}
