package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCell
{
    Cell testCell = new Cell();
    @Test
    public void testCellIsCreated()
    {

        assertInstanceOf(Cell.class, testCell, "object of type Cell not created");
    }

    @Test
    public void testCellHasMine()
    {
        assertTrue(testCell.hasMine == 1, "first cell not instantiated with a mine");
    }


}
