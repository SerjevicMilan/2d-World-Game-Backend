package sejevic.com._DWorldGenerationGame.core.game;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/init")
    public WorldLayout getInitialWorld(@RequestParam String width, @RequestParam String height,@RequestParam String sessionId) {
        return gameService.getOrCreateGame(width,height, sessionId).getWorlLayout();
    }

    @GetMapping("/state")
    public GameState getCurrentState(@RequestParam String width, @RequestParam String height, @RequestParam String sessionId) {
        return gameService.getOrCreateGame(width,height, sessionId).getCurrentGameState();
    }

    @PostMapping("/move")
    public GameState move(@RequestParam String sessionId, @RequestBody MoveCommand cmd) {
        Game game = gameService.getOrCreateGame(sessionId);
        game.updatePlayer(cmd.getDirection());
        return game.getCurrentGameState();
    }

    @PostMapping("/ready")
    public void clientReady(@RequestParam String sessionId) {
        gameService.getOrCreateGame(sessionId).setRunning(true);
    }

    @PostMapping("/restart")
    public void restartGame(@RequestParam String sessionId) {
        gameService.restartGame(sessionId);
    }
}


