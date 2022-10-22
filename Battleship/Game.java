
public class Game {
    
    public static void main(String[] args) {
        BattleshipPlayer player = new BattleshipPlayer();
        BattleshipGame game = new BattleshipGame(player);
        game.play();
    }
}
