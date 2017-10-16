public class Square {

    private Ship ship;
    private boolean shotAt = false;

    public Square() {
    }

    public Square(Ship ship) {
        this.ship = ship;
    }

    public void setShotAt() {
        this.shotAt = true;
    }

    public String getSymbol() {
        String symbol;

        if (!shotAt) {
            symbol = ".";
        } else {

            if (ship == null) {
                symbol = "~";
            } else {
                if (ship.isDied()) {
                    symbol = ship.getInitial();
                } else {
                    symbol = "*";
                }
            }
        }
        return symbol;
    }
}
