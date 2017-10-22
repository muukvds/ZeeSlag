package ZeeSlag.Actor;

import java.util.Random;

public class ComputerPlayer extends Player {

    //add smarter AI to computerPlayer

    public ComputerPlayer(String name) {
        super(name);
    }

    @Override
    public String getCoordinate() {
        String coordinate;
        System.out.print(this.getName() + ", is aan het denken...");

        try {
            Thread.sleep(3000);                 //1000 milliseconds is one second.
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        Random random = new Random();
        int X = random.nextInt(9);
        int Y = random.nextInt(10) + 1;

        char letter = 'A';
        letter += X;

        coordinate = letter + "" + Y;
        System.out.print(this.getName() + ", schiet op " + coordinate + ".");

        return coordinate;
    }
}
