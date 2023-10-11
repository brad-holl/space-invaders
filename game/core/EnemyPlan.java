package game.core;

import javafx.scene.image.Image;

import java.util.List;

public interface EnemyPlan {

    void move();

    void enemyXPos(int xPos);
    void enemyYPos(int yPos);
    void enemyWidth(int width);
    void enemyHeight(int height);

    void enemySprite(Image sprite);

    void enemyHealth(int health);
    void enemySpeed(int speed);
    void enemyStrategy(String start);
}
