package sejevic.com._DWorldGenerationGame.core.game;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000") // your frontend URL
public class GameController {

    private final Game game;

    public GameController(Game game) {
        this.game = game;
    }

    @GetMapping("/init")
    public WorldLayout getInitialWorld(WorldLayout layout) {
        return game.getWorlLayout();
    }

    @GetMapping("/state")
    public GameState getCurrentState() {
        return game.getCurrentGameState();
    }

    @PostMapping("/move")
    public GameState move(@RequestBody MoveCommand cmd) {
        game.updatePlayer(cmd.getDirection());
        return game.getCurrentGameState();
    }

    @PostMapping("/ready")
    public void clientReady() {
        game.setRunning(true);
    }

}

