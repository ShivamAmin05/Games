public class Position {

    private char rowVal;
    private int colVal;

    public Position(char row, int column)
    {
        if((row - 65 > 10 || row - 65 < 0)|| (column > 10 || column < 1))
        {
            throw new IllegalArgumentException("The value of row has to be within A-J and the value of the column has to be within 1-10");
        }
        rowVal = row;
        colVal = column; 
    }
    public Position(int row, int column)
    {
        if((row > 9 || row < 0)|| (column > 9 || column < 0))
        {
            throw new IllegalArgumentException("The boat is outside of the range of the board. The index of row and the column have to be between 0-9");
        }
        rowVal = (char)(row + 65);
        colVal = column + 1;
    }
    public char row()
    {
        return rowVal;
    }
    public int rowIndex()
    {
        return rowVal - 65;
    }
    public int columnIndex()
    {
        return colVal - 1;
    }
    public int column()
    {
        return colVal;
    }
    public String gridVal()
    {
        return row() + "-" + Integer.toString(column());
    }
}