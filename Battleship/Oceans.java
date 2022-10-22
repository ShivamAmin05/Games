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

        int curBoatStart;
        int curBoatEnd;
        int curBoatRow;
        int curBoatCol;
        int prevBoatStart;
        int prevBoatEnd;
        int prevBoatRow;
        int prevBoatCol;

        for(int i = 0; i < boats.size(); i++)
        {
            if(curBoat.orientation().equals("horizontal") && boats.get(i).orientation().equals("horizontal"))
            {
                curBoatStart = curBoat.boatPos().columnIndex();
                curBoatEnd = curBoat.boatPos().columnIndex() - curBoat.size() + 1; 
                curBoatRow = curBoat.boatPos().rowIndex();

                prevBoatStart = boats.get(i).boatPos().columnIndex();
                prevBoatEnd = boats.get(i).boatPos().columnIndex() - boats.get(i).size() + 1;
                prevBoatRow = boats.get(i).boatPos().rowIndex();

                if(curBoatRow == prevBoatRow)
                {
                    if(curBoatStart >= prevBoatEnd && curBoatEnd <= prevBoatStart)
                    {
                        throw(new Exception());
                    }
                }
            }

            if(curBoat.orientation().equals("horizontal") && boats.get(i).orientation().equals("vertical"))
            {
                curBoatStart = curBoat.boatPos().columnIndex();
                curBoatEnd = curBoat.boatPos().columnIndex() - curBoat.size() + 1; 
                curBoatRow = curBoat.boatPos().rowIndex();

                prevBoatStart = boats.get(i).boatPos().rowIndex();
                prevBoatEnd = boats.get(i).boatPos().rowIndex() + boats.get(i).size() - 1;
                prevBoatCol = boats.get(i).boatPos().columnIndex();

                if(curBoatStart >= prevBoatCol && curBoatEnd <= prevBoatCol)
                {
                    if(curBoatRow >= prevBoatStart && curBoatRow <= prevBoatEnd)
                    {
                        throw(new Exception());
                    }
                }

            }

            if(curBoat.orientation().equals("vertical") && boats.get(i).orientation().equals("vertical"))
            {
                curBoatStart = curBoat.boatPos().rowIndex();
                curBoatEnd = curBoat.boatPos().rowIndex() + curBoat.size() - 1; 
                curBoatCol = curBoat.boatPos().columnIndex();

                prevBoatStart = boats.get(i).boatPos().rowIndex();
                prevBoatEnd = boats.get(i).boatPos().rowIndex() + boats.get(i).size() - 1;
                prevBoatCol = boats.get(i).boatPos().columnIndex();

                if(curBoatCol == prevBoatCol)
                {
                    if(curBoatStart <= prevBoatEnd && curBoatEnd >= prevBoatStart)
                    {
                        throw(new Exception());
                    }
                }
            }
            if(curBoat.orientation().equals("vertical") && boats.get(i).orientation().equals("horizontal"))
            {
                curBoatStart = curBoat.boatPos().rowIndex();
                curBoatEnd = curBoat.boatPos().rowIndex() + curBoat.size() - 1; 
                curBoatCol = curBoat.boatPos().columnIndex();

                prevBoatStart = boats.get(i).boatPos().columnIndex();
                prevBoatEnd = boats.get(i).boatPos().columnIndex() - boats.get(i).size() + 1;
                prevBoatRow = boats.get(i).boatPos().rowIndex();

                if(curBoatStart <= prevBoatRow && curBoatEnd >= prevBoatRow)
                {
                    if(curBoatCol <= prevBoatStart && curBoatCol >= prevBoatEnd)
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
                isHit = true;
                boatInitial = boats.get(i).abbreviation();
                gameBoard.shotAt(pos,true,boatInitial);
                return;
            }
        }
        gameBoard.shotAt(pos,false,'*');
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

    public boolean sunk(Position pos)
    {
        for(int i=0;i<5;i++)
        {
            if(boats.get(i).abbreviation() == gameBoard.boatInitial(pos) && boats.get(i).sunk())
            {
                return true;
            }
        }
        return false;
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
                }
            }
        }
    }

}