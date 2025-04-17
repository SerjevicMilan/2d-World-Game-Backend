package sejevic.com._DWorldGenerationGame.core.game;

import org.springframework.stereotype.Component;
import sejevic.com._DWorldGenerationGame.core.world.WorldState;

@Component
public class GameFactory {

    private final GameProperties defaultProperties;

    public GameFactory(GameProperties defaultProperties) {
        this.defaultProperties = defaultProperties;
    }

    /**
    Create new game using default config and random seed
     */
    public Game createNewGame() {
        GameProperties config = new GameProperties();
        config.setWidth(defaultProperties.getWidth());
        config.setHeight(defaultProperties.getHeight());
        config.setDensity(defaultProperties.getDensity());
        config.setSeed((int)(Math.random() * Integer.MAX_VALUE)); // New seed every time
        return new Game(new WorldState(), config);
    }

    /**
    Create game with custom height and width and random seed
     */
    public Game createNewGame(String width, String height) {
        GameProperties config = new GameProperties();
        config.setWidth(Integer.parseInt(width));
        config.setHeight(Integer.parseInt(height));
        config.setDensity(defaultProperties.getDensity());
        config.setSeed((int)(Math.random() * Integer.MAX_VALUE)); // New seed every time
        return new Game(new WorldState(), config);
    }
}
