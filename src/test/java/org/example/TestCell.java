package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class TestCell
{
    Tile testCell = new Tile();
    @Test
    public void testCellIsCreated()
    {

        assertInstanceOf(Tile.class, testCell, "tile not created");
    }


}
