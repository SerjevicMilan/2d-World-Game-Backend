package sejevic.com._DWorldGenerationGame.core.hallway;

import sejevic.com._DWorldGenerationGame.core.room.Room;
import sejevic.com._DWorldGenerationGame.core.utils.Coordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static sejevic.com._DWorldGenerationGame.core.utils.RandomUtils.uniform;

//Create hallway path based on start and end position and state of worldTiles
public class Hallway {
    private final Coordinate from;
    private final Coordinate to;
    private final List<Coordinate> hallwayPath = new ArrayList<>();
    private final List<Coordinate> hallwayWall = new ArrayList<>();
    private final List<Room> rooms;
    private List<Hallway> hallways = new ArrayList<>();

    //during construction add from and to coordinates
    public Hallway(Coordinate from, Coordinate to, List<Room> rooms ) {
        this.from = from;
        this.to = to;
        this.rooms = rooms;
    }

    //assign two rooms center coordinates and use random number generator to pick how to generate hallway
    public Hallway(Coordinate start, Coordinate end, Random random, List<Room> rooms, List<Hallway> hallways) {
        from = start;
        to = end;
        this.rooms = rooms;
        this.hallways = hallways;

        if (uniform(random, 2) == 1) {
            generateZigZagPath();
        }
        else {
            generateLShapePath();
        }
    }

    //Takes from and to coordinates in worldTiles
    // and generates list of coordinates representing path.
    public List<Coordinate> generateZigZagPath() {
        int x = from.x;//starting x pos
        int y = from.y;//starting y pos
        int turn = 1;//for swapping axis incrementation

        while (x != to.x || y != to.y) {//reached end position
            hallwayPath.add(new Coordinate(x, y));
            if (turn == 1) {//increment either x or y
                x = moveOne(x, to.x);
                turn = 2;
            }
            else {
                y = moveOne(y, to.y);
                turn = 1;
            }
        }
        hallwayPath.add(new Coordinate(x, y));

        return hallwayPath;
    }

    //generate L shape path by traversing one y-axis first and then x-axis
    public List<Coordinate> generateLShapePath() {
        for (int y = from.y; y != to.y; y = moveOne(y, to.y)) {//traverse x-axis
            hallwayPath.add(new Coordinate(from.x, y));
        }

        for (int x = from.x; x != to.x; x = moveOne(x, to.x)) {//traverse y-axis
            hallwayPath.add(new Coordinate(x, to.y));
        }
        return hallwayPath;
    }

    //returns array of coordinates representing path
    public List<Coordinate> getFloor() {
        return hallwayPath;
    }

    //check around hallway pathway(floor tiles) if you can place wall tiles
    public List<Coordinate> getWall() {
        for (int i = 0; i < hallwayPath.size(); i++) {
            generateWall(hallwayPath.get(i));
        }
        return hallwayWall;
    }

    //check one up, down, left, right tile and generate if it's not floor tile
    private void generateWall(Coordinate floorTile1) {
        generateIfNotFloor(new Coordinate(floorTile1.x, floorTile1.y + 1));//up
        generateIfNotFloor(new Coordinate(floorTile1.x, floorTile1.y - 1));//down
        generateIfNotFloor(new Coordinate(floorTile1.x + 1, floorTile1.y));//right
        generateIfNotFloor(new Coordinate(floorTile1.x - 1, floorTile1.y));//left
    }

    //it checks current cord all rooms and hallway for floor tiles coordinates and generate wall if it's not floor
    private void generateIfNotFloor(Coordinate potentialWall) {
        for(Room room : rooms) {
            //if it's a floor tile skip otherwise
            if (room.getFloor().contains(potentialWall) || hallwayPath.contains(potentialWall)
                    || hallwaysContains(potentialWall)) {
                continue;
            }
            hallwayWall.add(potentialWall);
        }
    }

    //check previews hallways placed
    private boolean hallwaysContains(Coordinate potentialWall) {
        for (Hallway hallway : hallways) {
            if (hallway.hallwayPath.contains(potentialWall)) { return true; }
        }
        return false;
    }

    //increment or decrement (depending on start and end positions)
    private int moveOne(int start, int end) {
        int i = 1;
        if (start > end) { i *= -1; }//if end pos less then start pos then decrement
        if ( start != end) { start += i; }
        return start;
    }

    public Coordinate getFrom () {
        return from;
    }

    public Coordinate getTo () {
        return to;
    }
}
