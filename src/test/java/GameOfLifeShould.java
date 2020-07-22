import java.util.Arrays;
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
                {0, 1, 0, 0}});
        gameOfLife.tick();
        gameOfLife.assertState(new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}});
    }

    @Test
    public void beLifelessDueToUnderpopulationAtCorners() {
        GameOfLife gameOfLife = new GameOfLife(new int[][]{
                {1, 0, 0, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 0, 0, 1}});
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
                {0, 1, 1, 0},
                {0, 0, 0, 0}});
    }

    @Test
    public void killCellsDueToOverpopulation() {
        GameOfLife gameOfLife = new GameOfLife(new int[][]{
                {0, 1, 0, 0},
                {0, 1, 1, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 0}});
        gameOfLife.tick();
        gameOfLife.assertState(new int[][]{
                {0, 1, 1, 0},
                {1, 0, 0, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 0}});
    }

    @Test
    public void bringCellsToLifeDueToExactlyThreeLivingNeighbours() {
        GameOfLife gameOfLife = new GameOfLife(new int[][]{
                {0, 1, 0, 1},
                {0, 0, 1, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}});
        gameOfLife.tick();
        gameOfLife.assertState(new int[][]{
                {0, 0, 1, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}});
    }

    @Test
    public void notMutateInput() {
        int[][] cellsActual = {
                {0, 1, 0, 1},
                {0, 0, 1, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}};
        GameOfLife gameOfLife = new GameOfLife(cellsActual);
        gameOfLife.tick();
        int[][] cellsExpected = {
                {0, 1, 0, 1},
                {0, 0, 1, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}};
        assert Arrays.deepEquals(cellsActual, cellsExpected);
    }
}
