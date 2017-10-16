import java.util.HashMap;

public class Field {

    private HashMap<String,Square> fieldSquares = new HashMap<>();

    public Field() {

    }

    public void shotAt(String coordinates)
    {
        Square square = fieldSquares.get(coordinates);
        if (square != null)
        {
            square.setShotAt();
        }
    }

}
