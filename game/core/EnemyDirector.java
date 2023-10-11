package game.core;
import javafx.scene.image.Image;

public class EnemyDirector {

    private EnemyBuilder builder;

    public EnemyDirector(EnemyBuilder builder) {
        this.builder = builder;
    }

    public Enemy constructEnemy(int xPos, int yPos, int width, int height, int health, int speed, String strategy) {
        builder.buildEnemyXPos(xPos);
        builder.buildEnemyYPos(yPos);
        builder.buildEnemyWidth(width);
        builder.buildEnemyHeight(height);
        builder.buildEnemyHealth(health);
        builder.buildEnemySpeed(speed);
        builder.buildEnemyStrategy(strategy);

        return builder.getEnemy();
    }
}