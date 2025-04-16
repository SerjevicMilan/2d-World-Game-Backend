package sejevic.com._DWorldGenerationGame.core.room;

import sejevic.com._DWorldGenerationGame.core.utils.Coordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static sejevic.com._DWorldGenerationGame.core.utils.RandomUtils.uniform;


//Generate rooms with random placements and sizes
public class RoomGenerator {
    private int width;
    private int height;
    private final int maxRoomsSize = 8;//both width and height
    private final int minRoomsSize = 2;//both width and height
    List<Room> rooms = new ArrayList<>();
    Random random;
    private static final int AVERAGE_ROOM_AREA = 60;

    //take pseudo random number generator and 2d world dimensions
    public RoomGenerator(Random random, int height, int width) {
        this.width = width;
        this.height = height;
        this.random = random;
    }

    //generate random rooms(number of rooms depends on density)
    public List<Room> generateRooms(double density) {
        int numberOfRooms = calcNumberOfRooms(density);
        Room room;

        for (int i = 0; i < numberOfRooms; i++) {
            room = generateRoom(0);
            if (room == null) { break; }//in case creation impossible
            rooms.add(room);
        }
        return rooms;
    }

    //calc number of rooms to generate
    private int calcNumberOfRooms(double density) {
        if (density <= 0 || density > 1)  { throw new IllegalArgumentException("density needs to be bigger then 0"); }

        int roomMaxHeightWidth = maxRoomsSize * 2 + 1;
        int roomMinHeightWidth = minRoomsSize * 2 + 1;
        double averageRoomHeight =(double) (roomMaxHeightWidth +  roomMinHeightWidth) / 2;
        double averageRoomWidth = (double) (roomMaxHeightWidth + roomMinHeightWidth) / 2;


        double averageRoomArea = averageRoomHeight * averageRoomWidth;

        return (int) Math.round(width * height * density / averageRoomArea);
    }

    //generate random position and room size and create new Room
    private Room generateRoom(int numberOfTries) {
        Coordinate pos = new Coordinate(uniform(random, width), uniform(random, height));
        int roomWidth = uniform(random, minRoomsSize, maxRoomsSize);
        int roomHeight = uniform(random, minRoomsSize, maxRoomsSize);
        Room room;

        if (numberOfTries > 200) {//safety from stack overflow
            return null;
        }


        if (canPlace(pos, roomWidth, roomHeight)) {
            room = new Room(pos, roomHeight, roomWidth);
            if (!occupied(room)) { return room; }
        }

        return generateRoom(numberOfTries + 1);
    }

    //check if there is already room in that area
    private boolean occupied(Room room) {
        for(Coordinate pos : room.getAllCoordinates()) {
            for(Room r : rooms) {
                if (isOcupied(r, pos)){
                    return true;
                }
            }
        }
        return false;
    }

    //if room has pos coordinate position is already occupied
    //check around pos(+- 1, +-1) to make sure rooms don't touch
    private boolean isOcupied(Room r, Coordinate pos) {
        if (r.containsCoordinate(pos)
                || r.containsCoordinate(new Coordinate(pos.x + 1, pos.y))
                || r.containsCoordinate(new Coordinate(pos.x, pos.y + 1))
                || r.containsCoordinate(new Coordinate(Math.abs(pos.x - 1), pos.y))
                || r.containsCoordinate(new Coordinate(pos.x, Math.abs(pos.y - 1)))){
            return true;
        }
        return false;
    }

    //check if room is outside 2d world bounds
    private boolean canPlace(Coordinate pos, int roomWidth, int roomHeight) {
        if (pos.x - roomWidth < 0 || pos.y - roomHeight < 0) { return false; }
        if (pos.x + roomWidth >= width || pos.y + roomHeight >= height) { return false; }

        return true;
    }

}
