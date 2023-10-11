package game.core;

import javafx.scene.paint.Color;

public class HalfState implements BunkerState{
    private final Bunker bunker;
    public HalfState(Bunker bunker) {
        this.bunker = bunker;
    }

    @Override
    public void changeState() {
        bunker.setColor(Color.RED);
        bunker.setState(bunker.getLow());
    }
}
