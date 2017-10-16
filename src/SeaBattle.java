public class SeaBattle {

    private Player player1;
    private Player player2;

    private int playerCount;

    public SeaBattle(int playerCount) {
        this.playerCount = playerCount;
        createPlayers();

    }

    public void play() {
        //if one player make 1 player
        if(playerCount ==1) {
            trainingGame();
        }
        else if (playerCount == 2)
        {
game();
        }
        //if 2 players make while loop until one player wins, switch from player after player turn
    }

    public void trainingGame()
    {

    }

    private void game()
    {

    }

    private void createPlayers()
    {
        //create players
        if (playerCount == 1) {
            player1 = new Player();
        } else {
            player1 = new Player();
            player2 = new Player();
        }
    }
}
