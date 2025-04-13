package sejevic.com._DWorldGenerationGame.core.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sejevic.com._DWorldGenerationGame.core.world.WorldState;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GameService {

    private final GameProperties config;
    private final Map<String, Game> games = new ConcurrentHashMap<>();

    @Autowired
    public GameService(GameProperties config) {
        this.config = config;
    }

    public Game getOrCreateGame(String sessionId) {
        return games.computeIfAbsent(sessionId, id -> {
            WorldState ws = new WorldState(); // create a new world per session
            return new Game(ws, config);
        });
    }

    public void restartGame(String sessionId) {
        WorldState ws = new WorldState();
        games.put(sessionId, new Game(ws, config));
    }
}
