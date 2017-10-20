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

        boolean validCoordinates = false;
        String coordinate = "";

        while (!validCoordinates) {
            System.out.print(playerTurn.getName() + ", geef de locatie die je wilt beschieten:");
            coordinate = Main.IN.nextLine();

            if (coordinate != null && !coordinate.equals("")) {
                if (checkValidCoordinate(coordinate)) {
                    if (!checkDoubleShot(coordinate, playerEnemy)) {
                        validCoordinates = true;
                    } else {
                        System.out.println("*** Die locatie heb je al eerder beschoten ***");
                    }
                }
            }
        }
        playerEnemy.shoot(coordinate);

        if (playerEnemy.isLost()) {
            playWon = true;
            System.out.println("Bravo " + playerTurn.getName() + ", je hebt alle schepen van je tegenstander tot zinken gebracht!");
            System.out.println("Je bent de trotse winnaar van dit spel!");
        }

        return playWon;
    }

    private Boolean checkDoubleShot(String coordinate, Player player) {
        return player.coordinateIsShot(coordinate);
    }

    private Boolean checkValidCoordinate(String coordinate) {
        return checkRow(coordinate.substring(1)) && checkColumn(coordinate.charAt(0));
    }

    private boolean checkRow(String row) {
        boolean rowExists = false;

        if (tryParseInt(row)) {
            int r = Integer.parseInt(row);
            if (r >= 1 && r <= 10) {
                rowExists = true;
            } else {
                System.out.println("*** Rij " + row + " bestaat niet");
            }
        } else {

            System.out.println("*** Rij " + row + " bestaat niet");
        }
        return rowExists;
    }

    private boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean checkColumn(char column) {
        boolean columnExists = false;

        if (column >= 'A' && column <= 'J') {
            columnExists = true;
        } else {
            System.out.println("*** Kolom " + column + " bestaat niet");
        }

        return columnExists;
    }

    private void createPlayers() {
        //create players
        if (playerCount == 1) {
            boolean rightName = false;
            while (!rightName) {
                System.out.println("Wat is de naam van de speler");
                String name = Main.IN.nextLine();
                if (!name.trim().equals("")) {
                    player1 = new Player(name);
                    rightName = true;
                }

            }
            player2 = new Player("PC");
        } else {
            boolean rightName = false;
            while (!rightName) {
                System.out.println("Wat is de naam van de speler1");
                String name = Main.IN.nextLine();
                if (!name.trim().equals("")) {
                    player1 = new Player(name);
                    rightName = true;
                }
            }
            rightName = false;
            while (!rightName) {
                System.out.println("Wat is de naam van de speler2");
                String name = Main.IN.nextLine();
                if (!name.trim().equals("")) {
                    player2 = new Player(name);
                    rightName = true;
                }

            }
        }
    }
}
