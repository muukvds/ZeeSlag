public class Player {

    private Field playField;
    private String name;
    private boolean lost = false;

    public Player(String name) {
        this.name = name;
        makeField();
    }

    public String getName() {
        return name;
    }

    public boolean isLost() {
        return lost;
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

    private void makeField() {
        playField = new Field();
    }

    private void checkIfLost() {
        lost = playField.allShipsSunk();
    }
}
