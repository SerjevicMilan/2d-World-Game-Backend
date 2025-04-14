package sejevic.com._DWorldGenerationGame.core.game;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GameService {

    private final Map<String, Game> games = new ConcurrentHashMap<>();
    private final GameFactory gameFactory;

    public GameService(GameFactory gameFactory) {
        this.gameFactory = gameFactory;
    }

    public Game getOrCreateGame(String width, String height, String sessionId) {
        return games.computeIfAbsent(sessionId, id -> gameFactory.createNewGame(width, height));
    }

    public Game getOrCreateGame(String sessionId) {
        return games.computeIfAbsent(sessionId, id -> gameFactory.createNewGame());
    }

    public void restartGame(String sessionId) {
        games.put(sessionId, gameFactory.createNewGame());
    }
}
