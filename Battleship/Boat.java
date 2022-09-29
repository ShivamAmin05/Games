public class Boat {
    private String shipType;
    private String orientation;
    private int shipRow;
    private int shipCol;
    int peicesHit;
    private Position curPos;
    // private boolean[][] grid = new boolean[10][10];

    // Position startPos = new Position('D', 4);
    
    public Boat(String ship, String hori_vert, Position startPos)
    {
        shipType = ship;
        orientation = hori_vert;
        shipRow = startPos.rowIndex();
        shipCol = startPos.columnIndex();
        curPos = startPos;
        
        if (!(ship.equals("Aircraft Carrier") || ship.equals("Battleship") || ship.equals("Cruiser") || ship.equals("Destroyer") || ship.equals("Submarine")))
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
        if(hori_vert.equals("vertical") && startPos.rowIndex() + size() > 10)
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
        if(abbreviation() == 'A')
        {
            return 5;
        }
        if(abbreviation() == 'B')
        {
            return 4;
        }
        if(abbreviation() == 'C' || abbreviation() == 'S')
        {
            return 3;
        }
        if(abbreviation() == 'D')
        {
            return 2;
        }
        else
        {
            return 0;
        }
    }
    public void hit(Position curPos) 
    {
            // grid[curPos.rowIndex()][curPos.columnIndex()] = true;
            peicesHit += 1;
    }
 
    public boolean isHit(Position curPos)
    {
        if(direction().equals("horizontal"))
        {
            if(curPos.columnIndex() > shipCol || curPos.columnIndex() < shipCol - size())
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
            if(curPos.rowIndex() < shipRow || curPos.rowIndex() > shipRow + size())
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
    // public void printGrid()
    // {
    //     for(int i = 0; i < 10; i++)
    //     {
    //         for(int j = 0; j < 10; j++)
    //         {
    //             System.out.print(grid[i][j]+ ",");
    //         }
    //         System.out.println(" ");
    //     }
    // }
}
