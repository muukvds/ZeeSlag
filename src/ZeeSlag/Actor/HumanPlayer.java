package ZeeSlag.Actor;

import ZeeSlag.Main;

public class HumanPlayer extends Player {

    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public String getCoordinate() {
        System.out.print(this.getName() + ", geef de locatie die je wilt beschieten:");
        return Main.IN.nextLine();
    }
}
