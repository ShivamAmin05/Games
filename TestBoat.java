public class TestBoat{
    public static void main(String[] args) 
    {
        
        Position boat1 = new Position(0, 2);
        Boat instance1 = new Boat("Submarine", "horizontal", boat1);
        Position hit1 = new Position(0, 0);
        Position hit2 = new Position(0, 1);
        Position hit3 = new Position(0, 2);

        Position boat2 = new Position(5, 0);
        Boat instance2 = new Boat("Aircraft Carrier", "vertical", boat2);
        Position hit4 = new Position(5, 0);
        Position hit5 = new Position(6, 0);
        Position hit6 = new Position(7, 0);
        Position hit7 = new Position(8, 0);
        Position hit8 = new Position(9, 0);

        System.out.println("The first boat is a " + instance1.name());
        System.out.println("The boat is " + instance1.direction());
        System.out.println("The abrreviation is " + instance1.abbreviation());
        System.out.println("The size of the boat is " + instance1.size());
        System.out.println("It is " + instance1.isHit(hit1) + " that the first move hit the boat");
        System.out.println("It is " + instance1.sunk() + " that the ship has sunk");
        System.out.println("It is " + instance1.isHit(hit2) + " that the second move hit the boat");
        System.out.println("It is " + instance1.isHit(hit3) + " that the third move hit the boat");
        System.out.println("It is " + instance1.sunk() + " that the ship has sunk");
        System.out.println(" ");
        instance1.printGrid();
        System.out.println(" ");

        System.out.println("The second boat is a " + instance2.name());
        System.out.println("The boat is " + instance2.direction());
        System.out.println("The abrreviation is " + instance2.abbreviation());
        System.out.println("The size of the boat is " + instance2.size());
        System.out.println("It is " + instance2.isHit(hit4) + " that the first move hit the boat");
        System.out.println("It is " + instance2.sunk() + " that the ship has sunk");
        System.out.println("It is " + instance2.isHit(hit5) + " that the second move hit the boat");
        System.out.println("It is " + instance2.isHit(hit6) + " that the third move hit the boat");
        System.out.println("It is " + instance2.isHit(hit7) + " that the forth move hit the boat");
        System.out.println("It is " + instance2.isHit(hit8) + " that the fifth move hit the boat");
        System.out.println("It is " + instance2.sunk() + " that the ship has sunk");
        System.out.println(" ");
        instance2.printGrid();

    }
}