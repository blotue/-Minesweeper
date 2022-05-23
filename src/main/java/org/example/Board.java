package org.example;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.TestOnly;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board
{
    private static Board board;
    private static final Random random = new Random();
    private static final String BoardTemplate = """
            8 .  .  .  .  .  .  .  .\s
            7 .  .  .  .  .  .  .  .\s
            6 .  .  .  .  .  .  .  .\s
            5 .  .  .  .  .  .  .  .\s
            4 .  .  .  .  .  .  .  .\s
            3 .  .  .  .  .  .  .  .\s
            2 .  .  .  .  .  .  .  .\s
            1 .  .  .  .  .  .  .  .\s
              0  1  2  3  4  5  6  7\s
            """;

    private static final char[] Board = BoardTemplate.toCharArray();

    private static Tile[] boardTiles = new Tile[64];

    private int clickedTiles = 0;
    private Board()
    {
        for(int i = 0; i < 64; i++) {
            boardTiles[i] = new Tile();
        }
        for(int i = 0; i < 64; i++)
        {
            int j = random.nextInt(i, 64);
            Tile tempTile = boardTiles[j];
            boardTiles[j] = boardTiles[i];
            boardTiles[i] = tempTile;

        }
        for(int i = 0; i < 64; i++)
        {
            boardTiles[i].setBoardPosition(i);
        }

        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {

                int mineCount = 0;
                List<Tile> adjacent  = getEightAdjacent(i, j);
                for (Tile cell : adjacent) {
                    mineCount += cell.checkMine == 1 ? 1 : 0;
                }
                boardTiles[i*8+j].mineCount = mineCount;
            }
        }
    }

    private @NotNull List<Tile> getEightAdjacent(int i, int j) {
        List<Tile> adjacentTiles = new ArrayList<>();
        if(j +1 < 8)
        {
            //middle right
            adjacentTiles.add(boardTiles[(i * 8) + j + 1]);
        }
        if(j -1 > -1 && ((i * 8) + j - 1) >= i *8 )
        {
            //middle left
            adjacentTiles.add(boardTiles[(i * 8) + j - 1]);
        }
        if((((i * 8) + j +7) > (i +1) * 8 ) && ((i * 8) + j +7) < 64 )
        {
            //top right
            adjacentTiles.add(boardTiles[((i * 8) + j +7)]);
        }
        if(j +1 < 8 && ((i * 8) + j +9) < 64)
        {
            //top left
            adjacentTiles.add(boardTiles[((i * 8) + j +9)]);
        }
        if((i *8 - j -9) > ((i -1) * 8) && j -1 > -1)
        {
            //bottom left
            adjacentTiles.add(boardTiles[((i * 8) + j -9)]);
        }
        if((i *8 + j - 7) < i *8 && (i *8 + j - 7) > -1)
        {
            //bottom right
            adjacentTiles.add(boardTiles[(i * 8 + j -7)]);
        }
        if((i *8 + j +8) < 64)
        {
            //top middle
            adjacentTiles.add(boardTiles[((i * 8) + j +8)]);
        }
        if((i *8 + j -8) > -1)
        {
            //bottom middle
            adjacentTiles.add(boardTiles[((i * 8) + j -8)]);
        }
        return adjacentTiles;
    }

    public Tile getBoardTile(int row, int col)
    {
        return boardTiles[8*(row-1) + col];
    }

    public static Board getInstance()
    {
        if(board == null)
        {
            board = new Board();
        }
        return board;
    }

    public void renderBoard()
    {
        System.out.println(Board);

    }

    public void updateBoard(int index)
    {
        double row = index / 8;
        int IntRow = (int)row;
        int tilePositionInBoard= 26*(8-IntRow-1) + (index - (IntRow*8))*3 + 2;
        Board[tilePositionInBoard] = boardTiles[index].getTileStatus();
    }

    public void revealAllCells()
    {
        for(int i = 1; i < 64; i++)
        {
            boardTiles[i].setRevealed();
        }
    }

    public boolean revealTile(int row, int col)
    {
        getBoardTile(row, col).setRevealed();

        if(getBoardTile(row, col).checkMine == 1)
        {
            System.out.println("Game Over!");
            revealAllCells();
            renderBoard();
            return false;
        }
        if(clickedTiles >= 54)
        {
            System.out.println("You won the game!");
            revealAllCells();
            renderBoard();
            return false;
        }
        else
        {
            for(Tile cell : getEightAdjacent(row-1, col-1))
            {
                cell.setObserved();
            }
            System.out.println("Enter row and column:");
            renderBoard();
            return true;
        }

    }

    public void updateTilesPlayedCount()
    {
        clickedTiles++;
    }
    @TestOnly
    public static void setRandomSeed() {
        setRandomSeed(402442877);
    }
    @TestOnly
    public static void setRandomSeed(long seed)
    {
        random.setSeed(seed);
    }

}