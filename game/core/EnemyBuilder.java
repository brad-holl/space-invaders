package game.core;

import javafx.scene.image.Image;

public interface EnemyBuilder {
    void reset();

    void buildEnemyXPos(int xPos);
    void buildEnemyYPos(int yPos);
    void buildEnemyWidth(int width);
    void buildEnemyHeight(int height);
    void buildEnemySprite(Image sprite);
    void buildEnemyHealth(int health);
    void buildEnemySpeed(int speed);
    void buildEnemyStrategy(String start);

    Enemy getEnemy();
}
