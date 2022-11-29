public class ComputerBattleshipPlayer extends BattleshipPlayer {

    public ComputerBattleshipPlayer()
    {
        name = playerName();
    }

    public String playerName()
    {
        return "Computer Player";
    }
    public void updateGrid(Position pos)
    {
        grid.shootAt(pos);
    }
    public Position shoot()
    {
        int randRow = (int)(Math.random() * 10);
        int randCol = (int)(Math.random() * 10);
        while(grid.gameBoard.grid[randRow][randCol] != '.')
        {
            randRow = (int)(Math.random() * 10);
            randCol = (int)(Math.random() * 10);
        }
        turns++;
        updateGrid(new Position(randRow, randCol));
        return( new Position(randRow, randCol));
    }
}