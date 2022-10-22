import java.util.Arrays;

public class Grid {
    private char[][] grid = new char[10][10];

    public void shotAt(Position pos, boolean hit, char initial)
    {
        if(hit)
        {
            grid[pos.rowIndex()][pos.columnIndex()] = initial;
        }
        else
        {
            grid[pos.rowIndex()][pos.columnIndex()] = '*'; 
        }
    }
    public boolean hit(Position pos)
    {
        if(grid[pos.rowIndex()][pos.columnIndex()] != '.' && grid[pos.rowIndex()][pos.columnIndex()] != '*')
        {
            return true;
        }
        return false;
        
    }
    public boolean miss(Position pos)
    {
        if(grid[pos.rowIndex()][pos.columnIndex()] != '*')
        {
            return true;
        }
        return false;
    }
    public boolean empty(Position pos)
    {
        if(grid[pos.rowIndex()][pos.columnIndex()] == '.')
        {
            return true;
        }
        return false;
    }
    public char boatInitial(Position pos)
    {
        if(hit(pos))
        {
            return grid[pos.rowIndex()][pos.columnIndex()];
        }
        if(miss(pos))
        {
            return '*';
        }
        return '.';
    }
    public void createGrid()
    {
        for(int i = 0; i < 10; i++)
        {
            Arrays.fill(grid[i], '.');
        }
    }
    public void printGrid()
    {   System.out.println("  1 2 3 4 5 6 7 8 9 10");
        System.out.println(" ┏———————————————————┓");
        int rowChar = 65;
        for(int i = 0; i < 10; i++)
        {
            System.out.print((char)(rowChar));
            rowChar ++;
            System.out.print("┃");
            for(int j = 0; j < 10; j++)
            {
                System.out.print(grid[i][j] + "┃");
            }
            System.out.println("\n ┃—┃—┃—┃—┃—┃—┃—┃—┃—┃—┃");
            
        }
        System.out.println(" ┗———————————————————┛");
    }
}
