public class SeaBattle {

    private Player player1;
    private Player player2;

    private int playerCount;

    public SeaBattle(int playerCount) {
        this.playerCount = playerCount;
        createPlayers();
        play();

    }

    public void play() {
        //if one player make 1 player
        if (playerCount == 1) {
            trainingGame();
        } else if (playerCount == 2) {
            game();
        }
    }

    private void trainingGame() {
        boolean playerLost = false;
        while (!playerLost) {
            playerLost = playTurn(player1, player2);
        }
        playAgain();

    }

    private void playAgain() {

        boolean properAnswer = false;
        String answer = "";
        while (!properAnswer) {
            System.out.print("Wil je nog een keer spelen (ja/nee)?");
            answer = Main.IN.nextLine();
            if (answer.equals("ja") || answer.equals("nee")) {
                properAnswer = true;
            }
        }

        if (answer.equals("ja")) {
            play();
        } else {
            System.out.println("Bedankt voor het spelen van het spelletje Zeeslag.\n Hopelijk tot een volgende keer !");
        }
    }

    private void game() {
        //2 players make while loop until one player wins, switch from player after player turn
        boolean playerLost = false;
        while (!playerLost) {
            playerLost = playTurn(player1, player2);
            if (!playerLost) {
                playerLost = playTurn(player2, player1);
            }
        }
        playAgain();
    }

    private Boolean playTurn(Player playerTurn, Player playerEnemy) {
        boolean playWon = false;
        //pays one turn for one player
        System.out.println(" *** Aan de beurt: "+playerTurn.getName()+" ***");
        System.out.println();

        playerEnemy.showField();

        System.out.print(playerTurn.getName() + ", geef de locatie die je wilt beschieten:");
        String coordinate = Main.IN.nextLine();
        playerEnemy.shotAt(coordinate);
        if (playerEnemy.isLost()) {
            playWon = true;
            System.out.println("Bravo " + playerTurn.getName() + "+, je hebt alle schepen van je tegenstander tot zinken gebracht!");
            System.out.println("Je bent de trotse winnaar van dit spel!");
        }
        return playWon;
    }

    private void createPlayers() {
        //create players
        if (playerCount == 1) {
            System.out.println("Wat is de naam van de speler");
            player1 = new Player(Main.IN.nextLine());
            player2 = new Player("PC");
        } else {
            System.out.println("Wat is de naam van speler1");
            player1 = new Player(Main.IN.nextLine());
            System.out.println("Wat is de naam van speler2");
            player2 = new Player(Main.IN.nextLine());
        }
    }
}
