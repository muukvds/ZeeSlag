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

    public void printField()
    {
        for(int row = 10; row > 0;row --)
        {
            System.out.print(row);
            for(char column = 'A'; column <= 'J'; column++ )
            {
                System.out.print(fieldSquares.get(((column +""+ row))).getSymbol());
            }
            System.out.println();
        }
        System.out.println("ABCDEFGHIJ");
    }

}
