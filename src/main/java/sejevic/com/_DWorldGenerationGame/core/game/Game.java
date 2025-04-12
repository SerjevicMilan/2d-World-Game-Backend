package sejevic.com._DWorldGenerationGame.core.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Game {

    private final WorldState worldState;
    private final int GAME_TICK_MS = 100;
    private volatile boolean running = true;

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

    public void updatePlayer(char direction) {
        worldState.updatePlayerPosition(direction);
    }

    public GameState getCurrentGameState() {
        GameState gs = new GameState();
        gs.floors = worldState.getFloor();
        gs.walls = worldState.getWalls();
        gs.coins = worldState.getCoins();
        gs.player = worldState.getPlayer();
        gs.enemy = worldState.getEnemy();
        gs.status = worldState.getStatus();
        gs.isGameOver = (gs.status == GameStatus.LOST || gs.status == GameStatus.WON);
        return gs;
    }

    private static int getSeed() {
        return (int)(Math.random() * Integer.MAX_VALUE);
    }
}
