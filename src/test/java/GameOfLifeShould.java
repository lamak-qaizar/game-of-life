import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GameOfLifeShould {

    @Test
    public void remainLifelessWithEmptyBoard() {
        GameOfLife gameOfLife = new GameOfLife(4, 4);
        gameOfLife.tick();
        assertEquals(gameOfLife.hasLife(), false);
    }
}
