package org.example;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;

public class TestGrid
{

    @BeforeClass
    public static void disableRandomSeed()
    {Board.setRandomSeed();}
    @Test
    @Tag("CellMineTest")
    @DisplayName("Test tile does not have a mine given a pre-set seed")
    public void testCellHasNoMine()
    {
        Assertions.assertEquals(-1, Board.getInstance().getBoardTile(1, 1).checkMine, "Tile has mine");
    }
    @Test
    public void testSeedNotRandom()
    {
        Assertions.assertEquals(1, Board.getInstance().getBoardTile(1, 8).checkMine, "Seed not properly done");
    }
    @Test
    @Tag("CellMineTest")
    @DisplayName("Test tile has a mine given a pre-set seed")
    public void testCellHasMine()
    {
        Assertions.assertEquals(1, Board.getInstance().getBoardTile(1, 4).checkMine, "Tile does not have mine");
    }




}
