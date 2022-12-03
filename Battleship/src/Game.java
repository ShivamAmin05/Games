
public class Game {
    
    public static void main(String[] args) {
        ComputerBattleshipPlayer player = new ComputerBattleshipPlayer();
        PlayerEvaluator computer = new PlayerEvaluator(player, 1);
        BattleshipGame game = new BattleshipGame(player);
        BattleshipPlayer player2 = new BattleshipPlayer();
        BattleshipGame game2 = new BattleshipGame(player2);
        game2.play();
    }
}
