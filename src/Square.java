public class Square {

    private Ship ship;
    private boolean shotAt = false;

    public Square() {
    }

    public Square(Ship ship) {
        this.ship = ship;
    }

    public boolean isShotAt() {
        return shotAt;
    }

    public void setShotAt() {
        this.shotAt = true;
        if (ship != null) {
            System.out.println("Bravo, je hebt een schip geraakt.");
            ship.hit();
        } else {
            System.out.println("Helaas, dat was een misser.");
        }
    }

    public String getSymbol(boolean cheat) {
        String symbol;
        if (cheat) {
            if (ship == null) {
                symbol = "~";
            } else {
                symbol = ship.getInitial();
            }
        } else {
            if (!shotAt) {
                symbol = ".";
            } else {

                if (ship == null) {
                    symbol = "~";
                } else {
                    if (ship.isSunk()) {
                        symbol = ship.getInitial();
                    } else {
                        symbol = "*";
                    }
                }
            }
        }
        return symbol;
    }

    public boolean isShipSunk() {
        boolean isShipSunk = false;
        if (ship != null) {
            isShipSunk = ship.isSunk();
        }

        return isShipSunk;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public boolean hasShip() {
        boolean hasShip = true;
        if (ship == null) {
            hasShip = false;
        }

        return hasShip;
    }
}
