import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GameOfLifeShould {

    @Test
    public void remainLifelessWithEmptyBoard() {
        GameOfLife gameOfLife = new GameOfLife(new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}});
        gameOfLife.tick();
        gameOfLife.assertState(new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}});
    }

    @Test
    public void beLifelessDueToUnderpopulation() {
        GameOfLife gameOfLife = new GameOfLife(new int[][]{
                {0, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}});
        gameOfLife.tick();
        gameOfLife.assertState(new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}});
    }

    @Test
    public void beLifelessDueToUnderpopulationAtTheEdgeOfGrid() {
        GameOfLife gameOfLife = new GameOfLife(new int[][]{
                {0, 0, 1, 0},
                {1, 0, 0, 0},
                {0, 0, 0, 1},
                {0, 0, 0, 0}});
        gameOfLife.tick();
        gameOfLife.assertState(new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}});
    }

    @Test
    public void remainTheSameIfEachCellHas2LivingNeighbours() {
        GameOfLife gameOfLife = new GameOfLife(new int[][]{
                {0, 0, 0, 0},
                {0, 1, 1, 0},
                {0, 1, 0, 0},
                {0, 0, 0, 0}});
        gameOfLife.tick();
        gameOfLife.assertState(new int[][]{
                {0, 0, 0, 0},
                {0, 1, 1, 0},
                {0, 1, 0, 0},
                {0, 0, 0, 0}});
    }
}
