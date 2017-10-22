package ZeeSlag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Field {

    private HashMap<String, Square> fieldSquares = new HashMap<>();
    private int shipsDestroyed = 0;

    public Field() {
        createSquares();
    }

    private void createSquares() {
        for (int i = 1; i <= 10; i++) {
            for (char letter = 'A'; letter <= 'J'; letter++) {
                fieldSquares.put(letter + "" + i, new Square());
            }
        }
    }

    public void addShipsToFieldMenual() {
        askShipCoordinates(new Ship("Vliegdekschip"));
        askShipCoordinates(new Ship("Slagschip"));
        askShipCoordinates(new Ship("Onderzeeër"));
        askShipCoordinates(new Ship("Torpedobootjager"));
        askShipCoordinates(new Ship("Patrouilleboot"));
    }

    public void addShipsToFieldAuto() {
        generateShipCoordinates(new Ship("Vliegdekschip"));
        generateShipCoordinates(new Ship("Slagschip"));
        generateShipCoordinates(new Ship("Onderzeeër"));
        generateShipCoordinates(new Ship("Torpedobootjager"));
        generateShipCoordinates(new Ship("Patrouilleboot"));
    }

    private void generateShipCoordinates(Ship ship) {

        boolean coordinateAvailable = false;
        ArrayList<String> coordinates = null;
        while (!coordinateAvailable) {

            Random random = new Random();
            int X = random.nextInt(9);
            int Y = random.nextInt(10) + 1;
            int direction = random.nextInt(4) + 1;

            char letter = 'A';
            letter += X;

            coordinates = getCoordinates(letter, Y, direction, ship.getLength());
            coordinateAvailable = checkCoordinatesFree(coordinates);
        }
        addShipToCoordinates(coordinates, ship);
    }

    private void askShipCoordinates(Ship ship) {

        boolean coordinateAvailable = false;
        ArrayList<String> coordinates = null;
        while (!coordinateAvailable) {

            System.out.println("Voer het coordinaat in waar je wilt dat het " + ship.getType() + " (" + ship.getLength() + " lang) begint BV (A1,B5).");
            String co = Main.IN.nextLine();

            if (co != null && !co.equals("")) {
                char letter = co.charAt(0);
                int Y = 0;
                if (tryParseInt(co.substring(1))) {
                    Y = Integer.parseInt(co.substring(1));
                }
                System.out.println("Voer de richting in waar het schip in komt te liggen (up,down,right,left):");

                String direction = Main.IN.nextLine();

                switch (direction) {
                    case "up":
                        coordinates = getCoordinates(letter, Y, 1, ship.getLength());
                        coordinateAvailable = checkCoordinatesFree(coordinates);
                        break;
                    case "right":
                        coordinates = getCoordinates(letter, Y, 2, ship.getLength());
                        coordinateAvailable = checkCoordinatesFree(coordinates);
                        break;
                    case "down":
                        coordinates = getCoordinates(letter, Y, 3, ship.getLength());
                        coordinateAvailable = checkCoordinatesFree(coordinates);
                        break;
                    case "left":
                        coordinates = getCoordinates(letter, Y, 4, ship.getLength());
                        coordinateAvailable = checkCoordinatesFree(coordinates);
                        break;
                    default:
                        break;
                }
            }
            if (!coordinateAvailable) {
                System.out.println("*** Er kan geen schip worden geplaatst op deze locatie in deze richting ***");
            }
        }
        addShipToCoordinates(coordinates, ship);
    }

    private ArrayList<String> getCoordinates(char X, int Y, int direction, int length) {
        ArrayList<String> coordinates = new ArrayList<>();

        coordinates.add(X + "" + Y);
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
            coordinates.add(X + "" + Y);
        }
        return coordinates;
    }

    private ArrayList<String> getSurroundingCoordinates(ArrayList<String> coordinates) {
        ArrayList<String> surroundingCoordinates = new ArrayList<>();

        for (String coordinate : coordinates) {
            char X = coordinate.charAt(0);
            int Y = Integer.parseInt(coordinate.substring(1));

            surroundingCoordinates.add(X + "" + (Y + 1));
            X++;
            surroundingCoordinates.add(X + "" + Y);
            X--;
            surroundingCoordinates.add(X + "" + (Y - 1));
            X--;
            surroundingCoordinates.add(X + "" + Y);
        }
        return surroundingCoordinates;
    }

    private boolean checkCoordinatesFree(ArrayList<String> coordinates) {
        boolean isFree = true;

        for (String coordinate : coordinates) {
            Square square = fieldSquares.get(coordinate);

            if (square == null || square.hasShip()) {
                isFree = false;
                break;
            }
        }
        if (isFree) {
            for (String coordinate : getSurroundingCoordinates(coordinates)) {
                Square square = fieldSquares.get(coordinate);

                if (square != null) {
                    if (square.hasShip()) {
                        isFree = false;
                        break;
                    }
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

    public void printField(boolean cheat) {
        for (int row = 10; row > 0; row--) {
            if (row != 10) {
                System.out.print(" " + row + " ");
            } else {
                System.out.print(row + " ");
            }

            for (char column = 'A'; column <= 'J'; column++) {
                System.out.print(fieldSquares.get(((column + "" + row))).getSymbol(cheat));
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

    public boolean squareIsShot(String coordinate) {
        return fieldSquares.get(coordinate).isShotAt();
    }

    private boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
