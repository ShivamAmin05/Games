import java.util.ArrayList;
public class ComputerBattleshipPlayer extends BattleshipPlayer {
    int rowVal = (int)(Math.random() * 10);
    int colVal = (int)(Math.random() * 10);
    public ArrayList<Position> squares;

    public ComputerBattleshipPlayer()
    {
        name = playerName();
        squares = new ArrayList<>();
    }

    public String playerName()
    {
        return "Computer Player";
    }
    public void updateGrid(Position pos)
    {
        grid.shootAt(pos);
    }
    public void chooseRandSquare()
    {
        while(grid.gameBoard.grid[rowVal][colVal] != '.')
        {
            rowVal = (int)(Math.random() * 10);
            colVal = (int)(Math.random() * 10);
        }
    }
    public void chooseRandSquareParity()
    {
        rowVal = (int)(Math.random() * 10);
        colVal = (int)(Math.random() * 10);
        while(true)
        {
            rowVal = (int)(Math.random() * 10);
            colVal = (int)(Math.random() * 10);
            if(grid.gameBoard.grid[rowVal][colVal] != '.')
            {
                continue;
            }
            else if(((colVal + rowVal) % grid.smallestShipLeft) != 0)
            {
                continue;
            }
            else
            {
                break;
            }
        }
    }
    public void chooseAdj()
    {
        if(grid.hit())
        {
            for(int i = 0; i <= 1; i++)
            {
                for(int j = 0; j <= 1; j++)
                {
                    if(i != j && rowVal + i <= 9 && rowVal + i>= 0 && colVal + j <= 9 && colVal + j >= 0 && grid.gameBoard.grid[rowVal + i][colVal + j] == '.')
                    {
                        squares.add(new Position(rowVal + i, colVal + j));
                    }
                    if(i != j && rowVal - i <= 9 && rowVal - i>= 0 && colVal - j <= 9 && colVal - j >= 0 && grid.gameBoard.grid[rowVal - i][colVal - j] == '.')
                    {
                        squares.add(new Position(rowVal - i, colVal - j));
                    }
                }
            }
        }
        if(squares.isEmpty())
        {
            chooseRandSquareParity();
        }
        else
        {   
            rowVal = squares.get(squares.size()-1).rowIndex();
            colVal = squares.get(squares.size()-1).columnIndex();
            squares.remove(squares.size()-1);
        }
    }

    public Position shoot()
    {
        chooseAdj();
        // chooseRandSquare();
        // chooseRandSquareParity();
        updateGrid(new Position(rowVal, colVal));
        // updatePlayer(new Position(rowVal, colVal));
        turns++;
        int smallestShipLeft = grid.smallestShipLeft;
        return( new Position(rowVal, colVal));
    }
}