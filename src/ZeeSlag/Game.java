package ZeeSlag;

import ZeeSlag.Actor.Player;

public class Game {

    private Player player1;
    private Player player2;
    private boolean playWon = false;

    public Game(Player player1, Player player2, int playerCount) {
        this.player1 = player1;
        this.player2 = player2;
        player1.resetField();
        player2.resetField();
        if (playerCount == 1) {
            player1.setShipsMenual();
        } else {
            player1.setShipsAuto();
        }
        player2.setShipsAuto();
        play();

    }

    private void play() {
        while (!playWon) {
            playTurn(player1, player2);
            if (!playWon) {
                playTurn(player2, player1);
            }
        }
    }

    private void playTurn(Player playerTurn, Player playerEnemy) {
        //pays one turn for one player
        System.out.println(" *** Aan de beurt: " + playerTurn.getName() + " ***");
        System.out.println();
        playerEnemy.showField();

        boolean validCoordinates = false;
        String coordinate = "";
        while (!validCoordinates) {
            coordinate = playerTurn.getCoordinate();

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
        playerEnemy.shotAt(coordinate);

        if (playerEnemy.isLost()) {
            System.out.println("Bravo " + playerTurn.getName() + ", je hebt alle schepen van je tegenstander tot zinken gebracht!");
            System.out.println("Je bent de trotse winnaar van dit spel!");
            playWon = true;
        }

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

}
