import javax.swing.plaf.TreeUI;
import javax.swing.text.html.HTMLDocument.RunElement;
import java.util.ArrayList;


public class Oceans {

    private static String shipType;
    private static String orientation;
    private static Boat curBoat;
    private int boatsSunk = 0;
    private static int boatIndex;
    private static boolean shotAt;
    private static ArrayList<Boat> boats = new ArrayList<Boat>();
    // private static Boat[] boats;
    // private int shipRow;
    // private int shipCol;
    // private int peicesHit;
    public static void placeBoat(String ship, String hori_vert, Position startPos) throws Exception
    {
        curBoat = new Boat(ship, hori_vert, startPos);
        boats.add(curBoat);
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
        boatIndex++;
    }
    public void shootAt(Position pos)
    {
        for(int i = 0; i < boats.size(); i++)
        {
            if(boats.get(i).isHit(pos) == true)
            {
                System.out.println("A boat was hit");
                shotAt = true;
                return;
            }
        }
        shotAt = false;
        System.out.println("A boat was not hit");
    }
    // public static boolean hit(Position pos)
    // {
    //     if(shotAt)
    //     {

    //     }
    // }

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
        if(boats.get(boatIndex).sunk() == true)
        {
            return true;
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
        if(boatsSunk == 5)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public ArrayList<Boat> allBoats()
    {
        return boats;
    }
}