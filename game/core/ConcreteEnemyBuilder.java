package game.core;

import javafx.scene.image.Image;

public class ConcreteEnemyBuilder implements EnemyBuilder{
    private Enemy enemy;
    public ConcreteEnemyBuilder(){
        enemy = new Enemy();
    }

    @Override
    public void reset() {
        this.enemy = new Enemy();
    }
    @Override
    public void buildEnemyXPos(int xPos) {
        enemy.enemyXPos(xPos);
    }

    @Override
    public void buildEnemyYPos(int yPos) {
        enemy.enemyYPos(yPos);
    }

    @Override
    public void buildEnemyWidth(int width) {
        enemy.enemyWidth(width);
    }

    @Override
    public void buildEnemyHeight(int height) {
        enemy.enemyHeight(height);
    }

    @Override
    public void buildEnemySprite(Image sprite) {
        enemy.enemySprite(sprite);
    }

    @Override
    public void buildEnemyHealth(int health) {
        enemy.enemyHealth(health);
    }

    @Override
    public void buildEnemySpeed(int speed) {
        enemy.enemySpeed(speed);
    }

    @Override
    public void buildEnemyStrategy(String start) {
        enemy.enemyStrategy(start);
    }


    public Enemy getEnemy(){
        return enemy;
    }
}
