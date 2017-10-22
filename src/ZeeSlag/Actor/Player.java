package ZeeSlag.Actor;

import ZeeSlag.Field;
import ZeeSlag.Main;

public abstract class Player {

    private Field playField;
    private String name;
    private boolean lost = false;

    public Player(String name) {
        this.name = name;
        playField = new Field();
    }

    public void resetField() {
        playField = new Field();

    }
    public String getName() {
        return name;
    }

    //generate or put coordinates to shoot at other player.
    public abstract String getCoordinate();

    public void setShipsAuto() {
        playField.addShipsToFieldAuto();
    }

    public void setShipsManual() {
        playField.addShipsToFieldMenual();
    }

    public boolean isLost() {
        return lost;
    }

    public boolean coordinateIsShot(String coordinate) {
        return playField.squareIsShot(coordinate);
    }

    public void showField() {
        playField.printField(false);
        if (Main.CHEAT) {
            System.out.println();
            playField.printField(true);
        }
    }

    public boolean coordinateIsShot(String coordinate) {
        return playField.squareIsShot(coordinate);
    }

    public void shoot(String coordinates) {
        playField.shootAt(coordinates);
        checkIfLost();
    }

    private void checkIfLost() {
        lost = playField.allShipsSunk();
    }
}
