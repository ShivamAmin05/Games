public class PlayerEvaluator {
    public int maxTurns;
    public int minTurns = 200;
    public double avgTurns;
    PlayerEvaluator(ComputerBattleshipPlayer player, int runs)
    {
        for(int i = 0; i < runs; i++)
        {
            player = new ComputerBattleshipPlayer();
            BattleshipGame game = new BattleshipGame(player);
            int turns = game.play();
            minTurns = Math.min(turns, minTurns);
            maxTurns = Math.max(turns, maxTurns);
            avgTurns += turns;
        }
        System.out.println("The minimum number of turns is " + minTurns);
        System.out.println("The maximum number of turns is " + maxTurns);
        System.out.println("The average number of turns is " + avgTurns/runs);
    }
}
