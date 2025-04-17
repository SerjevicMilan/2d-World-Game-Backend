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

    /**
    Generate new game, assign new tread.
    Run it until victory or defeat condition is meet .
     */
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

        //assign new thread
        new Thread(this::runGameLoop).start();
    }

    private void runGameLoop() {
        waitForInitilisation();//wait until serRunning is called
        while (running) {
            try {
                Thread.sleep(GAME_TICK_MS);
                worldState.updateState();

                if (worldState.getStatus() != GameStatus.RUNNING) {
                    running = false;
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    /**
    wait until serRunning is called
     */
    private void waitForInitilisation() {
        while (!clientReady) {
            try {
                Thread.sleep(50); // wait for frontend
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     *set game running
     */
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

    /**
     *Generate random number that we can as a seed
     */
    private static int getSeed() {
        return (int)(Math.random() * Integer.MAX_VALUE);
    }
}
