public class SeaBattle {

    private Player player1;
    private Player player2;
    private boolean player1Started;
    private int playerCount;

    public SeaBattle(int playerCount) {
        this.playerCount = playerCount;
        createPlayers();
        play();

    }

    private void play() {
        //if one player make 1 player
        if (playerCount == 1) {
            trainingGame();
        } else if (playerCount == 2) {
            player1Started = true;
            game(player1, player2);
        }
    }

    private void trainingGame() {
        boolean playWon = false;
        while (!playWon) {
            playWon = playTurn(player1, player2);
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

            player1 = new Player(player1.getName());
            player2 = new Player(player2.getName());

            if (playerCount == 1) {
                trainingGame();
            } else if (playerCount == 2) {
                //make sure starting player switches if play again
                if (player1Started) {
                    game(player2, player1);
                    player1Started = false;
                } else {
                    game(player1, player2);
                }
            }
        } else {
            System.out.println("Bedankt voor het spelen van het spelletje Zeeslag.\nHopelijk tot een volgende keer !");
        }
    }

    private void game(Player p1, Player p2) {
        boolean playWon = false;
        while (!playWon) {
            playWon = playTurn(p1, p2);
            if (!playWon) {
                playWon = playTurn(p2, p1);
            }
        }
        playAgain();
    }

    private Boolean playTurn(Player playerTurn, Player playerEnemy) {
        boolean playWon = false;
        //pays one turn for one player
        System.out.println(" *** Aan de beurt: " + playerTurn.getName() + " ***");
        System.out.println();

        playerEnemy.showField();

        //todo make sure you cant set invalid coordinates and give message if already shot
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
