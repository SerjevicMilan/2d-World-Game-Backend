package sejevic.com._DWorldGenerationGame.core.player;

import sejevic.com._DWorldGenerationGame.core.utils.Coordinate;

import java.util.List;
import java.util.Random;

//Pseudo random generate coordinates representing player
//by generating coordinates until you find one that are in part of floor coordinates
public class Player {
    //generates number
    private Random randomGenerator;
    //list of coordinates representing valid coordinates
    private List<Coordinate> floorTiles;

    //position of player in world
    private Coordinate position;

    //initialise values need for player generation
    public Player(Random randomGenerator, List<Coordinate> floorTiles) {
        this.randomGenerator = randomGenerator;
        this.floorTiles = floorTiles;
    }

    //Pseudo random generate coordinates representing player
    public Coordinate spawnPlayer() {
        generatePlayer();
        return position;
    }

    //return player position
    public Coordinate getPosition() {
        return position;
    }

    //generating coordinates until you find one that are in part of floor coordinates
    private void generatePlayer() {
        if(floorTiles.isEmpty()) throw new IllegalArgumentException("No valid tiles to spawn player");
        int pos = randomGenerator.nextInt(0, floorTiles.size());
        position = floorTiles.get(pos);
    }
}

