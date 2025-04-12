package sejevic.com._DWorldGenerationGame.core.game;

import org.springframework.stereotype.Component;
import sejevic.com._DWorldGenerationGame.core.utils.Coordinate;

import java.util.List;

public class GameState {
    public List<Coordinate> floors;
    public List<Coordinate> walls;
    public List<Coordinate> coins;
    public Coordinate player;
    public Coordinate enemy;
    public boolean isGameOver;
    public GameStatus status;
}
