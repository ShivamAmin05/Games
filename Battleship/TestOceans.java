public class TestOceans {
    
    public static void main(String[] args) {
        Oceans gameBoard = new Oceans();
        try {
            gameBoard.placeBoat("Destroyer", "horizontal", new Position(0, 1));
        } catch (Exception ex) {
            System.out.println("The boat can not overlap another boat or be off of the grid");
        }
        try {
            gameBoard.placeBoat("Aircraft Carrier", "vertical", new Position(0, 2));
        } catch (Exception ex) {
            System.out.println("The boat can not overlap another boat 1");
        }
        try {
            gameBoard.placeBoat("Battleship", "vertical", new Position(1, 1));
        } catch (Exception ex) {
            System.out.println("The boat can not overlap another boat 2");
        }
        try {
            gameBoard.placeBoat("Destroyer", "vertical", new Position(2, 0));
        } catch (Exception ex) {
            System.out.println("The boat can not overlap another boat 3");
        }
        try {
            gameBoard.placeBoat("Submarine", "horizontal", new Position(9, 2));
        } catch (Exception ex) {
            System.out.println("The boat can not overlap another boat 4");
        }
        
            gameBoard.shootAt(new Position(0, 0));
            gameBoard.shootAt(new Position(0, 1));

            gameBoard.shootAt(new Position(0, 2));
            gameBoard.shootAt(new Position(1, 2));
            gameBoard.shootAt(new Position(2, 2));
            gameBoard.shootAt(new Position(3, 2));
            gameBoard.shootAt(new Position(4, 2));

            gameBoard.shootAt(new Position(1, 1));
            gameBoard.shootAt(new Position(2, 1));
            gameBoard.shootAt(new Position(3, 1));
            gameBoard.shootAt(new Position(4, 1));

            gameBoard.shootAt(new Position(2, 0));
            gameBoard.shootAt(new Position(3, 0));

            gameBoard.shootAt(new Position(9, 2));
            gameBoard.shootAt(new Position(9, 1));
            gameBoard.shootAt(new Position(9, 0));

            System.out.println("Boat sunk?");
            // System.out.println("Boat1: " + gameBoard.sunk(0));
            // System.out.println("Boat2: " + gameBoard.sunk(1));
            // System.out.println("Boat3: " + gameBoard.sunk(2));
            // System.out.println("Boat4: " + gameBoard.sunk(3));
            // System.out.println("Boat5: " + gameBoard.sunk(4));
      
            // System.out.println("All boats sunk?: " + gameBoard.allSunk());


    }
}
