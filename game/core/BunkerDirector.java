package game.core;

public class BunkerDirector {

    private BunkerBuilder builder;

    public BunkerDirector(BunkerBuilder builder) {
        this.builder = builder;
    }


    public Bunker createBunker(long xPos, long yPos, long width, long height) {
        builder.buildHeight(height);
        builder.buildWidth(width);
        builder.buildXPos(xPos);
        builder.buildYPos(yPos);
        return builder.getBunker();
    }
}