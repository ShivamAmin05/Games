public class Boat {
    private String shipType;
    private String[] boatNames = {"Aircraft Carrier", "Battleship", "Cruiser","Destroyer","Submarine"};
    private String orientation;
    private int shipRow;
    private int shipCol;
    int peicesHit;
    private Position curPos;
    
    public Boat(String ship, String hori_vert, Position startPos)
    {
        shipType = ship;
        orientation = hori_vert;
        shipRow = startPos.rowIndex();
        shipCol = startPos.columnIndex();
        curPos = startPos;
        
        if (!(ship.equals(boatNames[0]) || ship.equals(boatNames[1]) || ship.equals(boatNames[2]) || ship.equals(boatNames[3]) || ship.equals(boatNames[4])))
        {
            throw new IllegalArgumentException("The name of the ship has to be 'Aircraft Carrier', 'Battleship', 'Cruiser', 'Destroyer', or 'Submarine'");
        }
        if(!(hori_vert.equals("horizontal") || hori_vert.equals("vertical"))){
            throw new IllegalArgumentException("The orientation of the ship has to be either 'horizontal' or 'vertical'");
        }
        if(hori_vert.equals("horizontal") && startPos.column() - size() < 0)
        {
            throw new IllegalArgumentException("The boat is outside of the range of the board");
        }
        if(hori_vert.equals("vertical") && startPos.rowIndex() + size() >= 10)
        {
            throw new IllegalArgumentException("The boat is outside of the range of the board");
        }
    }
    public String name()
    {
        return shipType;
    }
    public char abbreviation()
    {
        return shipType.charAt(0);
    }
    public int size()
    {
        switch(abbreviation()) {
            case 'A':
                return 5;
            case 'B':
                return 4;
            case 'C':
                return 3;
            case 'S':
                return 3;
            case 'D':
                return 2;
            default:
                return 0;
          }
    }
    public void hit(Position curPos) 
    {
            peicesHit += 1;
    }
 
    public boolean isHit(Position curPos)
    {
        if(direction().equals("horizontal"))
        {
            if(curPos.columnIndex() > shipCol || curPos.columnIndex() <= shipCol - size())
            {
                return false;
            }
            if(curPos.rowIndex() == shipRow)
            {
                hit(curPos);
                return true;
            }
            return false; 
        }
        else
        {
            if(curPos.rowIndex() < shipRow || curPos.rowIndex() >= shipRow + size())
            {
                return false;
            }
            if(curPos.columnIndex() == shipCol)
            {
                hit(curPos);
                return true;
            }
            return false; 
        }
    }
    public boolean sunk()
    {
        if(peicesHit >= size()){
            return true;
        }
        return false;
    }
    // public Position position()
    // {
    //     return startPos;
    // }
    public String direction()
    {
        return orientation;
    }
    public String orientation()
    {
        return orientation;
    }
    public Position boatPos()
    {
        return curPos;
    }
}
