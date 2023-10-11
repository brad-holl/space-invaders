package game.core;

import java.util.ArrayList;
import java.util.List;

public class GameStats {
    private long lives;
    private long startX;
    private long startY;
    private long speed;
    private List<EnemyStats> enemyStats;
    private List<BunkerStats> bunkerStats;
    private long projectileDamage;
    private long projectileSpeed;
    private String projectileStrategy;
    private long width;
    private long height;
    private String playerColor;

    public GameStats(long lives, long startX, long startY) {
        this.lives = lives;
        this.startX = startX;
        this.startY = startY;
        enemyStats = new ArrayList<>();
        bunkerStats = new ArrayList<>();
    }

    public GameStats() {
        enemyStats = new ArrayList<>();
        bunkerStats = new ArrayList<>();
    }

    public long getLives() {
        return lives;
    }

    public void setLives(long lives) {
        this.lives = lives;
    }

    public long getStartX() {
        return startX;
    }

    public void setStartX(long startX) {
        this.startX = startX;
    }

    public long getStartY() {
        return startY;
    }

    public void setStartY(long startY) {
        this.startY = startY;
    }

    public List<EnemyStats> getEnemyStats() {
        return enemyStats;
    }

    public void setEnemyStats(List<EnemyStats> enemyStats) {
        this.enemyStats = enemyStats;
    }


    public long getProjectileDamage() {
        return projectileDamage;
    }

    public void setProjectileDamage(long projectileDamage) {
        this.projectileDamage = projectileDamage;
    }

    public long getProjectileSpeed() {
        return projectileSpeed;
    }

    public void setProjectileSpeed(long projectileSpeed) {
        this.projectileSpeed = projectileSpeed;
    }

    public String getProjectileStrategy() {
        return projectileStrategy;
    }

    public void setProjectileStrategy(String projectileStrategy) {
        this.projectileStrategy = projectileStrategy;
    }

    public List<BunkerStats> getBunkerStats() {
        return bunkerStats;
    }

    public void setBunkerStats(List<BunkerStats> bunkerStats) {
        this.bunkerStats = bunkerStats;
    }

    public long getSpeed() {
        return speed;
    }

    public void setSpeed(long speed) {
        this.speed = speed;
    }

    public long getWidth() {
        return width;
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

    public String getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(String playerColor) {
        this.playerColor = playerColor;
    }

    @Override
    public String toString() {
        return "GameStats{" +
                "lives=" + lives +
                ", startX=" + startX +
                ", startY=" + startY +
                ", speed=" + speed +
                ", enemyStats=" + enemyStats +
                ", bunkers=" + bunkerStats +
                ", projectileDamage=" + projectileDamage +
                ", projectileSpeed=" + projectileSpeed +
                ", projectileStrategy=" + projectileStrategy +
                '}';
    }
}
