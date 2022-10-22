import java.util.Scanner;
public class BattleshipPlayer {
    private String name;
    public Oceans grid;
    public int turns;

    public BattleshipPlayer()
    {
        name = playerName();
        if(name == null)
        {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter your name: ");
            name = input.nextLine();
            System.out.println("Hello " + name + ", Welcome to Battleship!");
        }
    }

    public void startGame()
    {
        grid = new Oceans();
    }
    public String playerName()
    {
        return name;
    }
    public Position shoot()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a row val(A-J): ");
        char rowVal = Character.toUpperCase(input.next().charAt(0));
        System.out.println("Enter a column val(1-10): ");
        int colVal = input.nextInt();
        turns++;
        updateGrid(new Position(rowVal, colVal));
        updatePlayer(new Position(rowVal, colVal));
        return( new Position(rowVal, colVal));
    }
    protected void updateGrid(Position pos)
    {
        grid.shootAt(pos);
        grid.gameBoard.printGrid();

    }
    public void updatePlayer(Position pos)
    {
        System.out.println("Turn #"+turns+":");
        if(grid.isHit == true)
        {
            System.out.println("Your shot at "+pos.gridVal()+" was a hit ");
        }
        else
        {
            System.out.println("Your shot at "+pos.gridVal()+" was not a hit ");
        }
        if(grid.sunk(pos))
        {
            String sunkShip;
            for(int i=0;i<5;i++)
            {
                if(grid.boats.get(i).abbreviation() == grid.gameBoard.boatInitial(pos))
                {
                    sunkShip = grid.boats.get(i).name();
                    System.out.println("A " + sunkShip + " was sunk");
                }
            }
        }
        if(turns == 100)
        {
            System.out.println("The game is over because you have exceeded the turn limit.");
        }
        if(grid.allSunk())
        {
            System.out.println("You have sunk all the ships! The game is over.");
        }
    
    }
}
