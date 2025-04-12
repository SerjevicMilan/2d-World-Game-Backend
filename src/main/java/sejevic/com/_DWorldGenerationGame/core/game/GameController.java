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

    @GetMapping("/state")
    public GameState getState() {
        return game.getCurrentGameState();
    }

    @PostMapping("/move")
    public GameState move(@RequestBody MoveCommand cmd) {
        game.updatePlayer(cmd.getDirection());
        return game.getCurrentGameState();
    }
}

