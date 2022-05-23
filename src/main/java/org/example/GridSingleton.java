package org.example;

import org.jetbrains.annotations.TestOnly;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GridSingleton
{
    //set seed for random, so we can repeat the testing and still get the same results.
    private static final Random random = new Random();
    //Singleton pattern because we only want a single instance of the grid
    //and a global point of access.
    //Our GridSingleton class will determine how to build the grid.

    private static GridSingleton gridSingleton;

    //create the grid template. It is quicker to calculate the cell we need to change and replace its character in
    //the string than it would be to use for loops to re-create the grid every time.
    private static final String GridTemplate = """
            8 -  -  -  -  -  -  -  -\s
            7 -  -  -  -  -  -  -  -\s
            6 -  -  -  -  -  -  -  -\s
            5 -  -  -  -  -  -  -  -\s
            4 -  -  -  -  -  -  -  -\s
            3 -  -  -  -  -  -  -  -\s
            2 -  -  -  -  -  -  -  -\s
            1 -  -  -  -  -  -  -  -\s
              A  B  C  D  E  F  G  H\s
            """;

    private static final char[] Grid = GridTemplate.toCharArray();

    private static Cell[] gridCells = new Cell[64];

    private int clickedCells = 0;
    private GridSingleton()
    {
        //generate new Cells
        for(int i = 0; i < 64; i++) {
            gridCells[i] = new Cell();
        }

        //pseudorandomly shuffle the cells
        for(int i = 0; i < 64; i++)
        {
            int j = random.nextInt(i, 64);
            Cell tempCell = gridCells[j];
            gridCells[j] = gridCells[i];
            gridCells[i] = tempCell;

        }
        for(int i = 0; i < 64; i++)
        {
            gridCells[i].setGridPosition(i);
        }

        //update each cell with mine count

        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {

                int mineCount = 0;
                List<Cell> adjacent  = getEightAdjacent(i, j);
                for (Cell cell : adjacent) {
                    mineCount += cell.hasMine == 1 ? 1 : 0;
                }
                /*
                    +8 top middle
                    -8 bottom middle
                    -A (if j - 1 < 0, doesn't exist)
                    +A (if j + 1 > 7, doesn't exist)

                 */
                gridCells[i*8+j].mineCount = mineCount;
            }
        }
    }

    private List<Cell> getEightAdjacent(int i, int j) {
        List<Cell> adjacentCells = new ArrayList<>();
        if(j +1 < 8)
        {
            //middle right
            adjacentCells.add(gridCells[(i * 8) + j + 1]);
        }
        if(j -1 > -1 && ((i * 8) + j - 1) >= i *8 )
        {
            //middle left
            adjacentCells.add(gridCells[(i * 8) + j - 1]);
        }
        if((((i * 8) + j +7) > (i +1) * 8 ) && ((i * 8) + j +7) < 64 )
        {
            //top right
            adjacentCells.add(gridCells[((i * 8) + j +7)]);
        }
        if(j +1 < 8 && ((i * 8) + j +9) < 64)
        {
            //top left
            adjacentCells.add(gridCells[((i * 8) + j +9)]);
        }
        if((i *8 - j -9) > ((i -1) * 8) && j -1 > -1)
        {
            //bottom left
            adjacentCells.add(gridCells[((i * 8) + j -9)]);
        }
        if((i *8 + j - 7) < i *8 && (i *8 + j - 7) > -1)
        {
            //bottom right
            adjacentCells.add(gridCells[(i * 8 + j -7)]);
        }
        if((i *8 + j +8) < 64)
        {
            //top middle
            adjacentCells.add(gridCells[((i * 8) + j +8)]);
        }
        if((i *8 + j -8) > -1)
        {
            //bottom middle
            adjacentCells.add(gridCells[((i * 8) + j -8)]);
        }
        return adjacentCells;
    }

    public Cell getGridCell(int row, char chr)
    {
        //calculate int column value from char (A-H = 8)
        int col = (chr - 65); //ASCII value - ASCII value for A

        return gridCells[8*(row-1) + col]; //calculate index in gridCell Array and return cell object.
    }

    public static GridSingleton getInstance()
    {
        if(gridSingleton == null)
        {
            gridSingleton = new GridSingleton();
        }
        return gridSingleton;
    }

    public void renderGrid()
    {
        System.out.println(Grid);

    }

    public void updateGrid(int index)
    {
        //each row has 26 chars.
        //cells are every 3 chars.
        //int cellPositionInGrid = 26*(9-row-1) + (chr - 65)*3 + 2;
        double row = index / 8;
        int IntRow = (int)row;
        int cellPositionInGrid = 26*(8-IntRow-1) + (index - (IntRow*8))*3 + 2;
        Grid[cellPositionInGrid] = gridCells[index].getCellStatus();
    }

    public void revealAllCells()
    {
        for(int i = 1; i < 64; i++)
        {
            gridCells[i].setRevealed();
        }
    }

    public boolean revealCell(int row, char chr)
    {
        getGridCell(row, chr).setRevealed();

        if(getGridCell(row, chr).hasMine == 1)
        {
            System.out.println(Messages.GAMEOVER);
            revealAllCells();
            renderGrid();
            return false;
        }
        if(clickedCells >= 54)
        {
            System.out.println(Messages.GAMEWON);
            revealAllCells();
            renderGrid();
            return false;
        }
        else
        {
            for(Cell cell : getEightAdjacent(row-1, (chr - 65)))
            {
                cell.setObserved();
            }
            System.out.println(Messages.MAKEMOVE);
            renderGrid();
            return true;
        }

    }

    public void updateCellsPlayedCount()
    {
        clickedCells++;
    }
    @TestOnly
    public static void setRandomSeed() {
        setRandomSeed(1965864024);
    }

    @TestOnly
    public static void setRandomSeed(long seed)
    {
        random.setSeed(seed);
    }

}
