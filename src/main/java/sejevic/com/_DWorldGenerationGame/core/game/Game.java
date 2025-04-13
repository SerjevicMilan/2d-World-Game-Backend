package sejevic.com._DWorldGenerationGame.core.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sejevic.com._DWorldGenerationGame.core.world.WorldState;

@Service
public class Game {

    private final WorldState worldState;
    private final int GAME_TICK_MS = 100;
    private volatile boolean running = true;
    private volatile boolean clientReady = false;

    @Autowired
    public Game(WorldState worldState, GameProperties config) {
        this.worldState = worldState;

        // Initialize the world using values from configuration
        worldState.generateWorld(
                config.getHeight(),
                config.getWidth(),
                config.getSeed(),
                config.getDensity()
        );


        new Thread(this::runGameLoop).start();
    }

    private void runGameLoop() {
        waitForInitilisation();

        while (running) {
            try {
                Thread.sleep(GAME_TICK_MS);
                worldState.updateState();

                if (worldState.getStatus() != GameStatus.RUNNING) {
                    running = false;
                    System.out.println("Game ended with status: " + worldState.getStatus());
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    private void waitForInitilisation() {
        while (!clientReady) {
            try {
                Thread.sleep(50); // wait for frontend
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void setRunning(boolean ready) {
        this.clientReady = ready;
    }

    public void updatePlayer(char direction) {
        worldState.updatePlayerPosition(direction);
    }

    public WorldLayout getWorlLayout() {
        WorldLayout layout = new WorldLayout();
        layout.floors = worldState.getFloor();
        layout.walls = worldState.getWalls();
        return layout;
    }

    public GameState getCurrentGameState() {
        GameState update = new GameState();
        update.coins = worldState.getCoins();
        update.player = worldState.getPlayer();
        update.enemy = worldState.getEnemy();
        update.status = worldState.getStatus();
        update.isGameOver = (update.status == GameStatus.LOST || update.status == GameStatus.WON);
        return update;
    }

    private static int getSeed() {
        return (int)(Math.random() * Integer.MAX_VALUE);
    }
}
