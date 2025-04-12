package sejevic.com._DWorldGenerationGame.core.world;

import sejevic.com._DWorldGenerationGame.core.enemy.Enemy;
import sejevic.com._DWorldGenerationGame.core.hallway.Hallway;
import sejevic.com._DWorldGenerationGame.core.hallway.HallwaysGenerator;
import sejevic.com._DWorldGenerationGame.core.player.Player;
import sejevic.com._DWorldGenerationGame.core.room.Room;
import sejevic.com._DWorldGenerationGame.core.room.RoomGenerator;
import sejevic.com._DWorldGenerationGame.core.utils.CoinGenerator;
import sejevic.com._DWorldGenerationGame.core.utils.Coordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/*
2d board filled with floor tiles surrounded by walls (hallways and rooms)
 */
public class World {
    private List<Room> rooms = new ArrayList<>();
    private List<Hallway> hallways = new ArrayList<>();
    private int worldHeight;
    private int worldWidth;
    private final int maxRoomSize = 10;//both width and height
    private final int minRoomSize = 2;//both width and height
    double worldDensity;
    private Random randomGenerator;

    private Player player;
    private Coordinate playerPosition;

    private Enemy enemy;
    private Coordinate enemyPosition;

    List<Coordinate> coins;


    int seed;

    /*
    Create 2d world of size worldHeight * worldWidth and fill it with number of rooms depending on density.
    Create Rooms of random sizes and position and connect them with hallways.
     */
    public World (int worldHeight, int worldWidth, int seed, double worldDensity) {
        this.worldHeight = worldHeight;
        this.worldWidth = worldWidth;
        this.randomGenerator = new Random(seed);
        this.worldDensity = worldDensity;
        generateWorld();
        this.seed = seed;
    }

    //generate Rooms and Hallways
    private void generateWorld() {
        //Initialise room generator
        RoomGenerator RG = new RoomGenerator(randomGenerator, worldHeight, worldWidth, minRoomSize, maxRoomSize);
        HallwaysGenerator HG;

        //Generate rooms
        rooms = RG.generateRooms(worldDensity);

        //Initialise Hallway Generator
        HG = new HallwaysGenerator(rooms, randomGenerator);

        //Generate hallways
        hallways = HG.generateHallways();

        //Generate player
        player = new Player(randomGenerator, getAllFloors());
        playerPosition = player.spawnPlayer();

        //Generate Enemy
        enemy = new Enemy(randomGenerator, getAllFloors());
        enemyPosition = enemy.spawn();

        //Generate coins
        coins = generateCoins();
    }

    //Get all walls Coordinates by going through all rooms and hallways
    public List<Coordinate> getAllWalls() {
        List<Coordinate> wallsCoordinates = new ArrayList<>();

        for (Room room : rooms) {
            wallsCoordinates.addAll(room.getWalls());
        }

        for (Hallway hallway : hallways) {
            wallsCoordinates.addAll(hallway.getWall());
        }

        return wallsCoordinates;
    }

    //Get all floors Coordinates by going through all rooms and hallways
    public List<Coordinate> getAllFloors() {
        List<Coordinate> floorCoordinates = new ArrayList<>();

        for (Room room : rooms) {
            floorCoordinates.addAll(room.getFloor());
        }

        for (Hallway hallway : hallways) {
            floorCoordinates.addAll(hallway.getFloor());
        }
        return floorCoordinates;
    }

    public int getWidth() {
        return worldWidth;
    }

    public int getHeight() {
        return worldHeight;
    }

    public Coordinate getPlayerPosition() {
        return playerPosition;
    }

    public Coordinate getEnemyPosition() { return enemyPosition; }

    //return seed used for world generation
    public int getSeed() {
        return seed;
    }

    public double getDensity() {
        return worldDensity;
    }

    private List<Coordinate> generateCoins() {
        return new CoinGenerator().generateCoins(10, randomGenerator, getAllFloors());
    }

    public List<Coordinate> getCoins() {
        return coins;
    }
}
