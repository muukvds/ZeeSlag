package ZeeSlag.Actor;

import ZeeSlag.Field;
import ZeeSlag.Game;
import ZeeSlag.Main;

public abstract class Player {

    // todo add askCoordinats methode
    // todo make sub classes

    private Field playField;
    private String name;
    private boolean lost = false;
    private Game game;

    public Player(String name) {
        this.name = name;
        // makeField();
    }

    public String getName() {
        return name;
    }

    public String getCoordinate() {
        return "";
    }

    public boolean isLost() {
        return lost;
    }

    public boolean coordinateIsShot(String coordinate) {
        return playField.squareIsShot(coordinate);
    }

    public void showField() {
        playField.printField(false);
        if(Main.CHEAT)
        {
            System.out.println();
            playField.printField(true);
        }
    }

    public void shotAt(String coordinates) {
        playField.shootAt(coordinates);
        checkIfLost();
    }

    public void makeField() {
        playField = new Field();
    }

    private void checkIfLost() {
        lost = playField.allShipsSunk();
    }
}
