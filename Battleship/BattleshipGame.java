public class BattleshipGame {

    private BattleshipPlayer player;

    BattleshipGame(BattleshipPlayer player)
    {
        this.player = player;
        player.startGame();
        player.grid.placeAllBoats();
        player.grid.gameBoard.createGrid();
    } 

    public int play()
    {
        while(player.turns != 100 && !player.grid.allSunk())
        {
            player.shoot();
        }
        return player.turns;
    }
}
