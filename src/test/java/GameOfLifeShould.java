import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GameOfLifeShould {

    @Test
    public void remainLifelessWithEmptyBoard() {
        GameOfLife gameOfLife = new GameOfLife(4, 4);
        gameOfLife.tick();
        assertEquals(gameOfLife.hasLife(), false);
    }

    @Test
    public void beLifelessDueToUnderpopulation() {
        GameOfLife gameOfLife = new GameOfLife(new int[][]{
                {0, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}});
        gameOfLife.tick();
        assertEquals(gameOfLife.hasLife(), false);
    }
}
