import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Oceans {

    private static String shipType;
    private static Boat curBoat;
    private static boolean isHit;
    private static char boatInitial;
    private int smallestShipLeft = 2;
    public static ArrayList<Boat> boats;
    public static ArrayList<Integer> boatSizes;
    public static Grid gameBoard;

    public Oceans()
    {
        gameBoard = new Grid();
        boats = new ArrayList<>();
        boatSizes = new ArrayList<>();
        boatSizes.add(5);
        boatSizes.add(4);
        boatSizes.add(3);
        boatSizes.add(2);
        boatSizes.add(3);
    }
    
    public static void placeBoat(String ship, String orientation, Position startPos) throws Exception
    {
        curBoat = new Boat(ship, orientation, startPos);
        shipType = ship;

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
    }
    public static void shootAt(Position pos)
    {
        for(int i = 0; i < boats.size(); i++)
        {
            isHit = false;
            if(gameBoard.empty(pos) && boats.get(i).isHit(pos))
            {
                isHit = true;
                boatInitial = boats.get(i).abbreviation();
                gameBoard.shotAt(pos,true,boatInitial);
                return;
            }

        }
        gameBoard.shotAt(pos,false,'*');
    }
    public static boolean hit()
    {
        return isHit;
    }
    public static String boatName()
    {
        return shipType;
    }
    public int smallestShipLeft()
    {
        return smallestShipLeft;
    }
    public boolean sunk(Position pos)
    {
        for(int i = 0;i < 5;i++)
        {
            if(boats.get(i).abbreviation() == gameBoard.boatInitial(pos) && boats.get(i).sunk())
            {
                setLongestShipSize(gameBoard.boatInitial(pos));
                return true;
            }
        }
        return false;
    }
    private void setLongestShipSize(char initial)
    {
        switch(initial)
        {
            case 'A': boatSizes.set(0, 10);
                break;
            case 'B': boatSizes.set(1, 10);
                break;
            case 'C': boatSizes.set(2, 10);
                break;
            case 'D': boatSizes.set(3, 10);
                break;
            case 'S': boatSizes.set(4,10);
                break;
        }
        smallestShipLeft = Collections.min(boatSizes);
    }
    public boolean allSunk()
    {
        int boatsSunk = 0;
        for(int i = 0; i < boats.size(); i++)
        {
            if(boats.get(i).sunk())
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
        int boatCol;
        
        for(int i = 0; i<5;i++)
        {
            while(true)
            {
                boatRow = pos.nextInt(10);
                orientationIndex = pos.nextInt(2);
                boatCol = pos.nextInt(10);
                try {
                    placeBoat(boatNames[i], orientation[orientationIndex], new Position(boatRow, boatCol));
                    break;
                } 
                catch (Exception ex) {
                }
            }
        }
    }

}