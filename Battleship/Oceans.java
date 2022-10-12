import java.util.ArrayList;
import java.util.Random;


public class Oceans {

    private static String shipType;
    private static String orientation;
    private static Boat curBoat;
    private int boatsSunk;
    private static int boatIndex;
    public static boolean isHit;
    public static char boatInitial;

    public static ArrayList<Boat> boats = new ArrayList<Boat>();
    public static Grid gameBoard = new Grid();

    public static void placeBoat(String ship, String hori_vert, Position startPos) throws Exception
    {
        curBoat = new Boat(ship, hori_vert, startPos);
        shipType = ship;
        orientation = hori_vert;

        for(int i = 0; i < boats.size(); i++)
        {
            if(i == boatIndex)
            {
                continue;
            }

            if(curBoat.orientation().equals("horizontal"))
            {
                if(curBoat.boatPos().columnIndex() >= boats.get(i).boatPos().columnIndex() && curBoat.boatPos().columnIndex() - curBoat.size() + 1 <= boats.get(i).boatPos().columnIndex())
                {
                    if(curBoat.boatPos().rowIndex() >= boats.get(i).boatPos().rowIndex() && curBoat.boatPos().rowIndex() <= boats.get(i).boatPos().rowIndex() + boats.get(i).size() - 1)
                    {
                        throw(new Exception());
                    }
                }
            }
            if(curBoat.orientation().equals("vertical"))
            {
                if(curBoat.boatPos().rowIndex() <= boats.get(i).boatPos().rowIndex() && curBoat.boatPos().rowIndex() + curBoat.size() - 1 >= boats.get(i).boatPos().rowIndex())
                {
                    if(curBoat.boatPos().columnIndex() <= boats.get(i).boatPos().columnIndex() && curBoat.boatPos().columnIndex() >= boats.get(i).boatPos().columnIndex() - boats.get(i).size() + 1)
                    {
                        throw(new Exception());
                    }
                }
            }
        }
        boats.add(curBoat);
        boatIndex++;
    }
    public static void shootAt(Position pos)
    {
        for(int i = 0; i < boats.size(); i++)
        {
            isHit = false;
            if(boats.get(i).isHit(pos) == true)
            {
                // System.out.println("A boat was hit on " + pos.gridval());
                isHit = true;
                boatInitial = boats.get(i).abbreviation();
                gameBoard.shotAt(pos,true,boatInitial);
                return;
            }
        }
        // System.out.println("A boat was not hit on " + pos.gridval());
        gameBoard.shotAt(pos,false,'M');
    }
    public static boolean hit(Position pos)
    {
        return isHit;
    }

    public static String boatName()
    {
        return shipType;
    }

    public static char boatInitial()
    {
        return shipType.charAt(0);
    }

    public boolean sunk(int boatIndex)
    {
        return (boats.get(boatIndex).sunk());
    }

    public boolean allSunk()
    {
        boatsSunk = 0;
        for(int i = 0; i < boats.size(); i++)
        {
            if(boats.get(i).sunk() == true)
            {
               boatsSunk += 1;
            }
        }
        return(boatsSunk == 5);
    }
    public void placeAllBoats()
    {
        String[] orientation = {"horizontal","vertical"};
        String[] boatNames = {"Aircraft Carrier", "Battleship", "Cruiser","Destroyer","Submarine"};
        Random pos = new Random();
        int boatRow;
        int orientationIndex;
        int boatcol;
        boolean error;
        
        for(int i = 0; i<5;i++)
        {
            error = true;
            while(error)
            {
                boatRow = pos.nextInt(10);
                orientationIndex = pos.nextInt(2);
                boatcol = pos.nextInt(10);
                try {
                    placeBoat(boatNames[i], orientation[orientationIndex], new Position(boatRow, boatcol));
                    error = false;
                } 
                catch (Exception ex) {
                    error = true;
                    // System.out.println("Fail"+i);
                }
            }
        }
    }
}