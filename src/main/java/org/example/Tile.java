package org.example;

public class Tile
{
    private static int mined = 10;
    public int checkMine = -1, mineCount = 0;
    public int revealed = -1, observed = -1;

    public int BoardPosition = 0;
    public Tile()
    {
        if(mined > 0)
        {
            checkMine = 1;
            mined--;

        }
    }
    public void setRevealed()
    {

        this.revealed = 1;
        if(this.revealed ==1 || this.observed == 1)
        {
            Board.getInstance().updateTilesPlayedCount();
        }
        Board.getInstance().updateBoard(BoardPosition);
    }
    public void setObserved()
    {
        this.observed = 1;
        if(this.checkMine == -1)
        {
            this.revealed = 1;
        }
        if(this.revealed ==1 || this.observed == 1)
        {
            Board.getInstance().updateTilesPlayedCount();
        }
        Board.getInstance().updateBoard(BoardPosition);
    }
    public char getTileStatus()
    {
        char returnChar = '.';
        if(this.revealed == 1)
        {
            if(this.checkMine == 1)
            {
                returnChar = 'X';
            }
            else if(this.observed == 1)
            {
                returnChar = (char)(48+this.mineCount);
            }
            else if(this.observed == -1)
            {
                returnChar = ' ';
            }
        }
        return returnChar;
    }

    public void setBoardPosition(int boardPosition) {
        BoardPosition = boardPosition;
    }
}
