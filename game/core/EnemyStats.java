package game.core;

public class EnemyStats {
    private long x;
    private long y;
    private String strategy;

    public EnemyStats(long x, long y, String strategy) {
        this.x = x;
        this.y = y;
        this.strategy = strategy;
    }


    public long getX() {
        return x;
    }

    public void setX(long x) {
        this.x = x;
    }

    public long getY() {
        return y;
    }

    public void setY(long y) {
        this.y = y;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    @Override
    public String toString() {
        return "EnemyStats{" +
                "x=" + x +
                ", y=" + y +
                ", strategy='" + strategy + '\'' +
                '}';
    }
}
