package org.example;

import org.example.GridSingleton;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;

public class TestGrid
{

    @BeforeClass
    public static void disableRandomSeed()
    {
        GridSingleton.setRandomSeed();
    }


    @Test
    public void testSeedNotRandom()
    {
        Assertions.assertEquals(1, GridSingleton.getInstance().getGridCell(1, 'H').hasMine, "Seed not random");
    }

    @Test
    @Tag("CellMineTest")
    @DisplayName("Tests a cell has a mine given a pre-set seed")
    public void testCellHasMine()
    {
        Assertions.assertEquals(1, GridSingleton.getInstance().getGridCell(1, 'H').hasMine, "Set cell does not have mine");
    }

    @Test
    @Tag("CellMineTest")
    @DisplayName("Tests a cell does not have a mine given a pre-set seed")
    public void testCellHasNoMine()
    {
        Assertions.assertEquals(-1, GridSingleton.getInstance().getGridCell(1, 'A').hasMine, "Set cell has mine");
    }


}
