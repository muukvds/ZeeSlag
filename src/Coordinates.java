import java.util.ArrayList;

public class Coordinates {

    private ArrayList<String> surroundingCoordinates;
    private ArrayList<String> Coordinates;

    public ArrayList<String> getSurroundingCoordinates() {
        return surroundingCoordinates;
    }

    public void setSurroundingCoordinates(ArrayList<String> surroundingCoordinates) {
        this.surroundingCoordinates = surroundingCoordinates;
    }

    public ArrayList<String> getCoordinates() {
        return Coordinates;
    }

    public void setCoordinates(ArrayList<String> coordinates) {
        Coordinates = coordinates;
    }
}
