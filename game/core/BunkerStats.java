package game.core;

public class BunkerStats {
    private long posx;
    private long posy;
    private long width;
    private long height;

    public BunkerStats(long posx, long posy, long width, long height) {
        this.posx = posx;
        this.posy = posy;
        this.width = width;
        this.height = height;
    }

    public int getPosx() {
        return (int) posx;
    }


    public long getPosy() {
        return posy;
    }


    public int getWidth() {
        return (int) width;
    }

    public void setWidth(long width) {
        this.width = width;
    }

    public long getHeight() {
        return height;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "BunkerStats{" +
                "posx=" + posx +
                ", posy=" + posy +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
