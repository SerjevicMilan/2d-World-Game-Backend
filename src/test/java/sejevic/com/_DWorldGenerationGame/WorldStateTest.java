package sejevic.com._DWorldGenerationGame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sejevic.com._DWorldGenerationGame.core.world.WorldState;
import sejevic.com._DWorldGenerationGame.core.utils.Coordinate;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

class WorldStateTest {

    private WorldState worldState;

    @BeforeEach
    void setUp() {
        worldState = new WorldState();
        worldState.generateWorld(10, 10, 123, 1.0);
    }

    @Test
    void testWorldIsGeneratedWithCorrectDimensions() {
        assertThat(worldState.getWidth()).isEqualTo(10);
        assertThat(worldState.getHeight()).isEqualTo(10);
        assertThat(worldState.getDensity()).isEqualTo(1.0);
        assertThat(worldState.getSeed()).isEqualTo(123);
    }

    @Test
    void testPlayerStartsOnValidFloorTile() {
        Coordinate player = worldState.getPlayer();
        List<Coordinate> floor = worldState.getFloor();
        assertThat(floor).contains(player);
    }

    @Test
    void testPlayerCanMoveOntoFloorTile() {
        Coordinate start = worldState.getPlayer();
        worldState.updatePlayerPosition('W');
        Coordinate afterMove = worldState.getPlayer();
        assertThat(afterMove).isNotEqualTo(start); // should move
        assertThat(worldState.getFloor()).contains(afterMove);
    }

    @Test
    void testPlayerCannotMoveIntoWall() {
        // Move the player to the edge and try going out of bounds
        for (int i = 0; i < 20; i++) {
            worldState.updatePlayerPosition('W'); // eventually hits wall
        }
        Coordinate current = worldState.getPlayer();
        worldState.updatePlayerPosition('W'); // attempt to move again
        Coordinate after = worldState.getPlayer();

        // Should stay in place
        assertThat(after).isEqualTo(current);
    }

    @Test
    void testCoinCollectionRemovesCoin() {
        List<Coordinate> coins = worldState.getCoins();
        Coordinate target = coins.get(0);

        worldState.updatePositin(target.x, target.y);

        assertThat(worldState.getCoins()).doesNotContain(target);
    }

}
