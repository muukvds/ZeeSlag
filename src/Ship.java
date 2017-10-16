public class Ship {

    private String type;
    private int length;
    private int hits = 0;
    private boolean died = false;

    public Ship(String type, int length) {
        this.type = type;
        this.length = length;
    }

    public boolean isDied() {
        return died;
    }

    public void hit() {
        hits++;
        if (hits >= length) {
            died = true;
        }
    }
}
