package sejevic.com._DWorldGenerationGame.core.game;

import org.springframework.stereotype.Component;
import sejevic.com._DWorldGenerationGame.core.utils.Coordinate;

import java.util.List;

@Component
public class GameState {
    public Coordinate player;
    public Coordinate enemy;
    public List<Coordinate> coins;
    public GameStatus status;
    public boolean isGameOver;
}
