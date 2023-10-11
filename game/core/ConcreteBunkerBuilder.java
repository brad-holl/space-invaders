package game.core;


public class ConcreteBunkerBuilder implements BunkerBuilder {
    private Bunker bunker;

    public ConcreteBunkerBuilder() {
        this.bunker = new Bunker();
    }

    @Override
    public void reset() {
        this.bunker = new Bunker();
    }

    @Override
    public void buildXPos(long xPos) {
        bunker.setXPos((int) xPos);
    }

    @Override
    public void buildYPos(long yPos) {
        bunker.setYPos((int) yPos);
    }

    @Override
    public void buildWidth(long width) {
        bunker.setBunkerWidth((int) width);
    }

    @Override
    public void buildHeight(long height) {
        bunker.setBunkerHeight((int) height);
    }

    @Override
    public Bunker getBunker() {
        return this.bunker;
    }
}

