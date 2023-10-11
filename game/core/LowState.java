package game.core;

public class LowState implements BunkerState{
    private final Bunker bunker;
    public LowState(Bunker bunker) {
        this.bunker = bunker;
    }
    @Override
    public void changeState() {
        bunker.setAlive(false);
    }
}
