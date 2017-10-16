import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Field {

    private HashMap<String, Square> fieldSquares = new HashMap<>();
    private int shipsDestroyed = 0;

    public Field() {
        createSquares();
        addShipsToField();
    }

    private void createSquares() {
        for (int i = 1; i <= 10; i++) {
            for (char letter = 'A'; letter <= 'J'; letter++) {
                fieldSquares.put(letter + "" + i, new Square());
            }
        }
    }

    private void addShipsToField() {
        generateCoordinates(new Ship("Vliegdekschip"));
        generateCoordinates(new Ship("Slagschip"));
        generateCoordinates(new Ship("Onderzeeër"));
        generateCoordinates(new Ship("Torpedobootjager"));
        generateCoordinates(new Ship("Patrouilleboot"));
    }

    private void generateCoordinates(Ship ship) {

        boolean coordinatsAvalible = false;
        Coordinates coordinates = new Coordinates();
        while (!coordinatsAvalible) {

            Random random = new Random();
            int X = random.nextInt(9);
            int Y = random.nextInt(10) + 1;
            int direction = random.nextInt(4) + 1;

            char letter = 'A';
            letter += X;

            coordinates = getCoordinates(letter, Y, direction, ship.getLength());
            coordinatsAvalible = checkCoordinatesFree(coordinates);
        }
        addShipToCoordinates(coordinates.getCoordinates(), ship);
    }

    private Coordinates getCoordinates(char X, int Y, int direction, int length) {
        ArrayList<String> mainCoordinates = new ArrayList<>();
        ArrayList<String> surroundingCoordinates = new ArrayList<>();

        mainCoordinates.add(X + "" + Y);
        surroundingCoordinates.add(X + "" + (Y + 1));
        X++;
        surroundingCoordinates.add(X + "" + Y);
        X--;
        surroundingCoordinates.add(X + "" + (Y - 1));
        X--;
        surroundingCoordinates.add(X + "" + Y);
        X++;

        for (int i = 1; i < length; i++) {

            switch (direction) {
                // 1 UP
                case 1:
                    Y++;
                    break;

                // 2 RIGHT
                case 2:
                    X++;
                    break;

                // 3 DOWN
                case 3:
                    Y--;
                    break;

                // 4 LEFT
                case 4:
                    X--;
                    break;
            }
            mainCoordinates.add(X + "" + Y);
            surroundingCoordinates.add(X + "" + (Y + 1));
            X++;
            surroundingCoordinates.add(X + "" + Y);
            X--;
            surroundingCoordinates.add(X + "" + (Y - 1));
            X--;
            surroundingCoordinates.add(X + "" + Y);
            X++;

            System.out.println(X + "" + Y);
        }

        Coordinates coordinates = new Coordinates();
        coordinates.setCoordinates(mainCoordinates);
        coordinates.setSurroundingCoordinates(surroundingCoordinates);

        return coordinates;
    }

    private boolean checkCoordinatesFree(Coordinates coordinates) {
        boolean isFree = true;

        for (String coordinate : coordinates.getCoordinates()) {
            Square square = fieldSquares.get(coordinate);

            if (square == null || square.hasShip()) {
                isFree = false;
            }
        }

        for (String coordinate : coordinates.getSurroundingCoordinates()) {
            Square square = fieldSquares.get(coordinate);

            if (square != null) {
                if (square.hasShip()) {
                    isFree = false;
                }
            }
        }
        return isFree;
    }

    private void addShipToCoordinates(ArrayList<String> coordinates, Ship ship) {
        //get all coordinates between start and end and add ship to square

        for (String coordinate : coordinates) {
            Square square = fieldSquares.get(coordinate);
            square.setShip(ship);
        }
    }

    public void shootAt(String coordinates) {
        Square square = fieldSquares.get(coordinates);
        if (square != null) {
            if (!square.isShotAt()) {
                square.setShotAt();
                if (square.isShipSunk()) {
                    shipsDestroyed++;
                }
            }
        }
    }

    public void printField() {
        for (int row = 10; row > 0; row--) {
            if (row != 10) {
                System.out.print(" " + row + " ");
            } else {
                System.out.print(row + " ");
            }

            for (char column = 'A'; column <= 'J'; column++) {
                System.out.print(fieldSquares.get(((column + "" + row))).getSymbol());
            }
            System.out.println();
        }
        System.out.println("   ABCDEFGHIJ");
    }

    public boolean allShipsSunk() {
        boolean allShipsSunk = false;
        if (Main.CHEAT) {
            if (shipsDestroyed >= 1) {
                allShipsSunk = true;
                System.out.println("*** Vanwege de cheat mode is het spel nu al afgelopen ***");
            }
        } else {
            if (shipsDestroyed == 5) {
                allShipsSunk = true;
            }
        }
        return allShipsSunk;
    }
}
