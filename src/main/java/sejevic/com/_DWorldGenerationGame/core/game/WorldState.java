package sejevic.com._DWorldGenerationGame.core.game;

import org.springframework.stereotype.Component;
import sejevic.com._DWorldGenerationGame.core.enemy.EnemyPathGenerator;
import sejevic.com._DWorldGenerationGame.core.utils.Coordinate;
import sejevic.com._DWorldGenerationGame.core.world.World;

import java.util.ArrayList;
import java.util.List;

/**
 * WorldState manages the current state of the game world, including:
 * - Player position
 * - Coin positions
 * - Floor and wall tiles
 *
 * It also handles collision detection, movement updates, and triggers
 * the generation of a new world when all coins are collected.
 */
@Component
public class WorldState {
    // Instance of the World object
    World world = null;

    GameStatus status = GameStatus.RUNNING;

    // Lists of all wall and floor tile coordinates
    List<Coordinate> wallCordinates = new ArrayList<>();
    List<Coordinate> floorCordinates = new ArrayList<>();

    // World properties (dimensions, density, and seed)
    public int height = 0;
    public int width = 0;
    public double density;
    public int seed;

    // Current player position
    Coordinate playerPosition;

    //current enemy position
    Coordinate enemyPosition;
    EnemyPathGenerator enemyPathGenerator = new EnemyPathGenerator();
    private int enemyMoveCounter = 0;
    private static final int ENEMY_MOVE_INTERVAL = 10;

    // List of coin positions
    List<Coordinate> coins;

    /**
     * Initializes a new world with given dimensions, seed, and density.
     * Also extracts all wall, floor, coin, and player coordinates from the world.
     */
    public void generateWorld(int worldHeight, int worldWidth, int seed, double worldDensity) {
        height = worldHeight;
        width = worldWidth;
        density = worldDensity;
        this.seed = seed;
        world = new World(worldHeight, worldWidth, seed, worldDensity);
        getAllCordinates();
        enemyPathGenerator.generateGraph(floorCordinates);
    }

    /**
     * Updates the world state.
     * If all coins have been collected, a new world is generated.
     */
    public void updateState() {
        updateStatus();
        updateEnemyPosition();
    }

    public void updateStatus() {
        if (status != GameStatus.RUNNING) return;

        if (coins.isEmpty()) {
            status = GameStatus.WON;
            return;
        }

        if (playerPosition.equals(enemyPosition)) {
            status = GameStatus.LOST;
        }
    }

    /**
     * Extracts and stores all relevant coordinate data from the current world instance.
     */
    private void getAllCordinates() {
        floorCordinates = world.getAllFloors();
        wallCordinates = world.getAllWalls();
        coins = world.getCoins();
        playerPosition = world.getPlayerPosition();
        enemyPosition = world.getEnemyPosition();
    }

    // ====== Getters for external access ======

    public List<Coordinate> getWalls() {
        return wallCordinates;
    }

    public List<Coordinate> getFloor() {
        return floorCordinates;
    }

    public List<Coordinate> getCoins() {
        return coins;
    }

    public Coordinate getPlayer() {
        return playerPosition;
    }

    public Coordinate getEnemy() { return enemyPosition; }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getSeed() {
        return seed;
    }

    public double getDensity() {
        return density;
    }

    public GameStatus getStatus() { return status; }

    /**
     * Moves the player one tile in the specified direction.
     * Valid directions: 'W', 'A', 'S', 'D' (up, left, down, right).
     * If the move lands on a floor tile, updates player position and checks for coin collection.
     */
    public void updatePlayerPosition(char direction) {
        direction = Character.toUpperCase(direction);
        int x = 0;
        int y = 0;

        if (direction == 'W') y += 1; // Move up
        if (direction == 'S') y -= 1; // Move down
        if (direction == 'A') x -= 1; // Move left
        if (direction == 'D') x += 1; // Move right

        updatePositin(playerPosition.x + x, playerPosition.y + y);
    }

    /**
     * Updates the player position if the new tile is valid (a floor tile).
     * Also checks for collision with coins.
     */
    public void updatePositin(int x, int y) {
        Coordinate potentialPosition = new Coordinate(x, y);

        // Only update if the position is a valid floor tile
        if (floorCordinates.contains(potentialPosition)) {
            playerPosition = potentialPosition;
            checkPlayerCoinCollision();
        }
    }

    /**
     * Removes the coin from the list if the player is currently on it.
     * This simulates "picking up" the coin.
     */
    private void checkPlayerCoinCollision() {
        coins.remove(playerPosition);
    }

    private void updateEnemyPosition() {
        enemyMoveCounter++;
        if (enemyMoveCounter % ENEMY_MOVE_INTERVAL != 0) return;

        List<Coordinate> enemyPath = enemyPathGenerator.generatePath(enemyPosition, playerPosition);
        if (!enemyPath.isEmpty() && enemyPath.size() > 1) {
            enemyPosition = enemyPath.get(1); // move 1 step
        }
    }

    private boolean checkPlayerEnemyCollision() {
        return playerPosition == enemyPosition;
    }

    public boolean gameOver() { return  checkPlayerEnemyCollision(); }
}
