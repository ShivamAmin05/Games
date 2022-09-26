public class TestOceans {
    public static Oceans placeBoat(String ship, String hori_vert, Position startPos)
    {
        Oceans test = new Oceans(ship, hori_vert, startPos);
        System.out.println(test.boatName());
        System.out.println(test.boatInitial());
        return test;
    }
    public static void main(String[] args) {
        // Position test = new Position(0, 9);
        placeBoat("Battleship", "horizontal", new Position(0, 9));

    }
}
