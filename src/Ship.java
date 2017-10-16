public class Ship {

    private String type;
    private int length;
    private int hits = 0;
    private boolean sunk = false;

    public Ship(String type) {
        this.type = type;
        switch (type) {
            case "Vliegdekschip":
                length = 5;
                break;
            case "Slagschip":
                length = 4;
                break;
            case "Onderzeeër":
                length = 3;
                break;
            case "Torpedobootjager":
                length = 3;
                break;
            case "Patrouilleboot":
                length = 2;
                break;
        }

    }

    public boolean isSunk() {
        return sunk;
    }

    public void hit() {
        hits++;
        if (hits >= length) {
            sunk = true;
        }
    }

    public String getInitial() {
        return type.substring(0, 1);
    }
}
