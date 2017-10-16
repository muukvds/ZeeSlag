import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Field {

    private HashMap<String, Square> fieldSquares = new HashMap<>();

    public Field() {
        createSquares();
    }

    private void createSquares() {
        for (int i = 1; i <= 10; i++) {
            for (char letter = 'A'; letter <= 'J'; letter++) {
                String s = new String(new char[]{letter});
                fieldSquares.put(letter + "" + i, new Square());
                addShipsToField();
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
        while (!coordinatsAvalible) {


            Random random = new Random();
            int X = random.nextInt(9);

            int Y = random.nextInt(10) + 1;


            char letter = 'A';
            letter += X;
            String start = letter + "" + Y;

            Random randomDirection = new Random();
            int direction = randomDirection.nextInt(4) + 1;

            ArrayList<String> coordinates = getcoordinates(letter, Y, direction, ship.getLength());
            coordinatsAvalible = checkCoordinatesFree(coordinates);
            if (coordinatsAvalible) {
                addShipToCoordinates(coordinates, ship);
            }
        }
    }

    private ArrayList<String> getcoordinates(char X, int Y, int direction, int length) {
        ArrayList<String> coordinates = new ArrayList<>();

        coordinates.add(X + "" + Y);

        for (int i = 0; i < length; i++) {

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
            System.out.println(X + "" + Y);
        }

        return coordinates;
    }

    private boolean checkCoordinatesFree(ArrayList<String> coordinates) {
        boolean isFree = true;

        for (String coordinate : coordinates) {
            Square square = fieldSquares.get(coordinate);
            isFree = square != null && square.hasShip();
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


    public void shotAt(String coordinates) {
        Square square = fieldSquares.get(coordinates);
        square.setShotAt();
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

}
