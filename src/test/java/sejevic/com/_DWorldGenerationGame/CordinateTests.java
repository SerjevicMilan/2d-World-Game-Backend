package sejevic.com._DWorldGenerationGame;

import org.junit.jupiter.api.Test;
import sejevic.com._DWorldGenerationGame.core.utils.Coordinate;

import static com.google.common.truth.Truth.assertThat;
//import static org.assertj.core.api.Assertions.assertThat;


public class CordinateTests {

    @Test
    public void basicTests() {
        Coordinate pos1 = new Coordinate(2, 2);
        Coordinate pos2 = new Coordinate(4, 4);

        //uses Pythagorean Calculation if not on same axis
        assertThat(pos1.distanceBettwen(pos2)).isEqualTo(2.8284271247461903);

        //If on same axis just calc difference between opposite axis
        pos2.changeCoordinates(4, 2);
        assertThat(pos1.distanceBettwen(pos2)).isEqualTo(2);

        pos2.changeCoordinates(2, 4);
        assertThat(pos1.distanceBettwen(pos2)).isEqualTo(2);

        //test equals
        pos2.changeCoordinates(2, 2);
        assertThat(pos1.equals(pos2)).isTrue();

        pos2.changeCoordinates(2, 1);
        assertThat(pos1.equals(pos2)).isFalse();

        //test toString
        assertThat(pos2.toString()).isEqualTo("(2,1)");
    }
}
