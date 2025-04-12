package sejevic.com._DWorldGenerationGame.core.enemy;

import sejevic.com._DWorldGenerationGame.core.utils.Coordinate;

import java.util.List;
import java.util.Random;

//Pseudo random generate coordinates representing enemy
//by generating coordinates until you find one that are in part of floor coordinates
public class Enemy {
    //generates number
    private Random randomGenerator;
    //list of coordinates representing valid coordinates
    private List<Coordinate> floorTiles;

    //position of enemy in world
    private Coordinate position;

    //initialise values need for enemy generation
    public Enemy(Random randomGenerator, List<Coordinate> floorTiles) {
        this.randomGenerator = randomGenerator;
        this.floorTiles = floorTiles;
    }

    //Pseudo random generate coordinates representing enemy
    public Coordinate spawn() {
        generate();
        return position;
    }

    //return enemy position
    public Coordinate getPosition() {
        return position;
    }

    //generating coordinates until you find one that are in part of floor coordinates
    private void generate() {
        int pos = randomGenerator.nextInt(0, floorTiles.size());
        position = floorTiles.get(pos);
    }
}

