package ZeeSlag;

import ZeeSlag.Actor.ComputerPlayer;
import ZeeSlag.Actor.HumanPlayer;
import ZeeSlag.Actor.Player;

public class SeaBattle {

    // todo maks so that only gets player count,create player, starts game, switshes player and play again.

    private Player player1;
    private Player player2;
    //    private boolean player1Started;
    private int playerCount;

    public SeaBattle(int playerCount) {
        this.playerCount = playerCount;
        createPlayers();
        play();
    }

    private void play() {
        new Game(player1, player2, playerCount);
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

            Player playerX = player1;
            player1 = player2;
            player2 = playerX;

            play();
        } else {
            System.out.println("Bedankt voor het spelen van het spelletje Zeeslag.\nHopelijk tot een volgende keer !");
        }
    }

    private void createPlayers() {
        //create players
        if (playerCount == 1) {
            player1 = createHumanPlayer("player");
            player2 = new ComputerPlayer("PC");
        } else {
            player1 = createHumanPlayer("player1");
            player2 = createHumanPlayer("player2");
        }
    }

    private Player createHumanPlayer(String player) {
        boolean rightName = false;
        String name = "";
        while (!rightName) {
            System.out.println("Wat is de naam van de " + player);
            name = Main.IN.nextLine();
            if (!name.trim().equals("")) {
                rightName = true;
            }
        }
        return new HumanPlayer(name);
    }
}
