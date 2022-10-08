
public class TestOceans {
    
    public static void main(String[] args) {
        Oceans game = new Oceans();
        try {
            game.placeBoat("Destroyer", "horizontal", new Position(0, 1));
        } catch (Exception ex) {
            System.out.println("The boat can not overlap another boat or be off of the grid");
        }
        try {
            game.placeBoat("Aircraft Carrier", "vertical", new Position(0, 2));
        } catch (Exception ex) {
            System.out.println("The boat can not overlap another boat or be off of the grid");
        }
        try {
            game.placeBoat("Battleship", "vertical", new Position(1, 1));
        } catch (Exception ex) {
            System.out.println("The boat can not overlap another boat or be off of the grid");
        }
        try {
            game.placeBoat("Destroyer", "vertical", new Position(2, 0));
        } catch (Exception ex) {
            System.out.println("The boat can not overlap another boat or be off of the grid");
        }
        try {
            game.placeBoat("Submarine", "horizontal", new Position(9, 2));
        } catch (Exception ex) {
            System.out.println("The boat can not overlap another boat or be off of the grid");
        }
        
            game.gameBoard.createGrid();

            game.shootAt(new Position(0, 0));
            game.shootAt(new Position(0, 1));

            game.shootAt(new Position(0, 2));
            game.shootAt(new Position(1, 2));
            game.shootAt(new Position(2, 2));
            game.shootAt(new Position(3, 2));
            game.shootAt(new Position(4, 2));

            game.shootAt(new Position(1, 1));
            game.shootAt(new Position(2, 1));
            game.shootAt(new Position(3, 1));
            game.shootAt(new Position(4, 1));

            game.shootAt(new Position(2, 0));
            game.shootAt(new Position(3, 0));

            game.shootAt(new Position(9, 2));
            game.shootAt(new Position(9, 1));
            game.shootAt(new Position(9, 0));

            game.shootAt(new Position(9, 9));
            game.shootAt(new Position(9, 8));
            game.shootAt(new Position(9, 0));
            game.shootAt(new Position(0, 9));
            game.shootAt(new Position(5, 5));
            game.shootAt(new Position(4, 4));
            game.shootAt(new Position(4, 3));
            
            System.out.println("Boat sunk?");
            System.out.println("Boat1: " + game.sunk(0));
            System.out.println("Boat2: " + game.sunk(1));
            System.out.println("Boat3: " + game.sunk(2));
            System.out.println("Boat4: " + game.sunk(3));
            System.out.println("Boat5: " + game.sunk(4));
      
            System.out.println("All boats sunk?: " + game.allSunk());

            game.gameBoard.printGrid();


    }
}
