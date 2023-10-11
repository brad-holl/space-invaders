package game.core;

public interface BunkerBuilder {

    void reset();
    void buildXPos(long xPos);
    void buildYPos(long yPos);
    void buildWidth(long width);
    void buildHeight(long height);

    Bunker getBunker();

}
