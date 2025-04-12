package sejevic.com._DWorldGenerationGame.core.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//Generates a list of collectible coin positions randomly placed on valid floor tiles.
public class CoinGenerator {

    /*
    Returns a list of unique random coordinates selected from floorTiles
    @param count number of coins to generate
    @param rand random number generator
    @param floorTiles coordinates representing valid floor tiles
    @return List<Coordinates> list of coordinate representing coins
     */
    public List<Coordinate> generateCoins(int count, Random rand, List<Coordinate> floorTiles) {
        List<Coordinate> coins = new ArrayList<>();
        int pos;

        while(count > 0) {
            pos = rand.nextInt(0, floorTiles.size());
            if (!coins.contains(floorTiles.get(pos))) {
                coins.add(floorTiles.get(pos));
                count--;
            }
        }
        return coins;
    }
}
