public class Boat {
    private String shipType;
    private String[] boatNames = {"Aircraft Carrier", "Battleship", "Cruiser","Destroyer","Submarine"};
    private String orientation;
    private int shipRow;
    private int shipCol;
    int piecesHit;
    private Position curPos;
    
    public Boat(String ship, String orientation, Position startPos)
    {
        shipType = ship;
        this.orientation = orientation;
        shipRow = startPos.rowIndex();
        shipCol = startPos.columnIndex();
        curPos = startPos;
        
        if (!(ship.equals(boatNames[0]) || ship.equals(boatNames[1]) || ship.equals(boatNames[2]) || ship.equals(boatNames[3]) || ship.equals(boatNames[4])))
        {
            throw new IllegalArgumentException("The name of the ship has to be 'Aircraft Carrier', 'Battleship', 'Cruiser', 'Destroyer', or 'Submarine'");
        }
        if(!(orientation.equals("horizontal") || orientation.equals("vertical"))){
            throw new IllegalArgumentException("The orientation of the ship has to be either 'horizontal' or 'vertical'");
        }
        if(orientation.equals("horizontal") && startPos.column() - size() < 0)
        {
            throw new IllegalArgumentException("The boat is outside of the range of the board");
        }
        if(orientation.equals("vertical") && startPos.rowIndex() + size() >= 10)
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
    public void hit() 
    {
            piecesHit += 1;
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
                hit();
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
                hit();
                return true;
            }
            return false; 
        }
    }
    public boolean sunk()
    {
        return(piecesHit >= size());
    }
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
