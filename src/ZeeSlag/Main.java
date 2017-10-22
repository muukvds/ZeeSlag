package ZeeSlag;

import java.util.Scanner;

public class Main {

    public static final Scanner IN = new Scanner(System.in);
    public static final boolean CHEAT = true;

    public static void main(String[] args) {
        System.out.println("Welkom bij het spelletje Zeeslag!");
        System.out.println("Probeer de oorlogsbotten van je tegenstander tot zinken te brengen");
        System.out.println("voor hij jouw boten te pakken heeft genomen.");

        //get players and start ZeeSlag.SeaBattle
        int playerCount = 1;
        boolean playersSet = false;
        while (!playersSet) {
            System.out.print("Geef aantal spelers (1/2):");
            String sPlayerCount = IN.nextLine();
            //dont allow string value or <1 >2
            try {
                playerCount = Integer.parseInt(sPlayerCount);
                if (playerCount == 1 || playerCount == 2) {
                    playersSet = true;
                } else {
                    System.out.println("*** Dat is geen geldig aantal spelers! ***");
                }
            } catch (Exception e) {
                System.out.println("*** Dat is geen geldig aantal spelers! ***");
            }
        }
        new SeaBattle(playerCount);
    }
}
