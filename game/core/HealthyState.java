package game.core;

import javafx.scene.paint.Color;

public class HealthyState implements BunkerState{
    private final Bunker bunker;
    public HealthyState(Bunker bunker){
        this.bunker = bunker;
    }
    @Override
    public void changeState() {
        bunker.setColor(Color.YELLOW);
        bunker.setState(bunker.getHalf());
    }
}
