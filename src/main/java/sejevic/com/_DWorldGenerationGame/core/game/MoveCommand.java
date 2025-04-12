package sejevic.com._DWorldGenerationGame.core.game;

public class MoveCommand {
    private char direction;

    public MoveCommand() {} // required for JSON deserialization

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }
}