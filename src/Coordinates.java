import java.util.ArrayList;

public class Coordinates {

    private ArrayList<String> Coordinates;
    private ArrayList<String> surroundingCoordinates;

    public Coordinates(ArrayList<String> coordinates, ArrayList<String> surroundingCoordinates) {
        this.surroundingCoordinates = surroundingCoordinates;
        Coordinates = coordinates;
    }

    public ArrayList<String> getCoordinates() {
        return Coordinates;
    }

    public ArrayList<String> getSurroundingCoordinates() {
        return surroundingCoordinates;
    }


}
