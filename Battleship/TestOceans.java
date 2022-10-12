
public class TestOceans {
    
    public static void main(String[] args) {
        Oceans game = new Oceans();
        game.placeAllBoats();
        game.gameBoard.createGrid();
        for(int i = 0; i<10;i++)
        {
            for(int j = 0; j<10;j++)
            {
                game.shootAt(new Position(i, j));
            }
        }
        
        // System.out.println("Boat sunk?");
        // System.out.println("Boat1: " + game.sunk(0));
        // System.out.println("Boat2: " + game.sunk(1));
        // System.out.println("Boat3: " + game.sunk(2));
        // System.out.println("Boat4: " + game.sunk(3));
        // System.out.println("Boat5: " + game.sunk(4));
    
        // System.out.println("All boats sunk?: " + game.allSunk());

        game.gameBoard.printGrid();
        // System.out.println(game.boats);

    }
}
