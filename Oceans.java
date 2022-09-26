import javax.swing.text.html.HTMLDocument.RunElement;

public class Oceans {

    private String shipType;
    private String orientation;
    // private int shipRow;
    // private int shipCol;
    // private int peicesHit;

    public Oceans(String ship, String hori_vert, Position startPos)
    {
        shipType = ship;
        orientation = hori_vert;
        // shipRow = startPos.rowIndex();
        // shipCol = startPos.columnIndex();
        Boat Boat = new Boat(shipType, orientation, startPos);
    }
    public String boatName()
    {
        return shipType;
    }
    public char boatInitial()
    {
        return shipType.charAt(0);
    }
}
