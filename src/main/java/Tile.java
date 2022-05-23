/*public class Tile
{
    private static int minedCells = 10;

    public int hasMine = -1;
    public int revealed = -1;
    public int observed = -1;
    public int mineCount = 0;

    public int GridPosition = 0;
    public Tile()
    {
        if(minedCells > 0)
        {
            hasMine = 1;
            minedCells--;

        }
    }

    public void setRevealed()
    {

        this.revealed = 1;
        if(this.revealed ==1 || this.observed == 1)
        {
            Grid.getInstance().updateCellsPlayedCount();
        }
        Grid.getInstance().updateGrid(GridPosition);
    }
    public void setObserved()
    {
        this.observed = 1;
        if(this.hasMine == -1)
        {
            this.revealed = 1;
        }
        if(this.revealed ==1 || this.observed == 1)
        {
            Grid.getInstance().updateCellsPlayedCount();
        }
        Grid.getInstance().updateGrid(GridPosition);
    }
    public char getTileStatus()
    {
        char returnChar = '-';
        //if an adjacent cell has a mine, then it wont be revealed.
        //so if observed and !revealed, it has a mine, so display ' '.
        //if a cell is revealed, a user has clicked on it, or it was an adjacent cell without a mine.
        //therefore, if revealed, if hasMine -> display 'X', else display mineCount or ' ' if < 1;
        if(this.revealed == 1)
        {
            if(this.hasMine == 1)
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

        //return returnChar = (char)(48+this.mineCount);
        return returnChar;
    }

    public void setGridPosition(int gridPosition) {
        GridPosition = gridPosition;
    }
}
*/