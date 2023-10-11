package game.core;

import game.App;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.List;


public class Enemy extends GameObject implements EnemyPlan, Shoot, Move {

    public static double speedMultiplier = 1;
    private String strategy;
    private Image enemyImage;



    public Enemy() {
        super(100, 100, 50, 50);
        enemyImage = new Image("enemy.png");
    }


    @Override
    public boolean collisionDetected(GameObject other) {
        return false;
    }

    @Override
    public void update() {
        if (health <= 0) {

            Enemy.speedMultiplier += 0.3;
            System.out.println(Enemy.speedMultiplier);
            setAlive(false);
        }
    }


    @Override
    public void render(GraphicsContext gc) {
        if (enemyImage != null) {
            gc.drawImage(enemyImage, xPos, yPos, width, height);
        } else {
            gc.setFill(Color.BLACK);
            gc.fillRect(xPos, yPos, width, height);
        }
    }



    @Override
    public void move() {

    }

    public boolean canMove(String direction, List<Enemy> allEnemies) {
        if(direction.equals("d")) {  // right
            int maxRightX = 0;
            for(Enemy enemy : allEnemies) {
                int enemyRight = (int) (enemy.getxPos() + enemy.getWidth());
                if(enemyRight > maxRightX) {
                    maxRightX = enemyRight;
                }
            }
            return maxRightX < App.WIDTH;
        } else if(direction.equals("a")) { // left
            int minLeftX = App.WIDTH;
            for(Enemy enemy : allEnemies) {
                if(enemy.getxPos() < minLeftX) {
                    minLeftX = (int) enemy.getxPos();
                }
            }
            return minLeftX > 0;
        }
        return false;
    }





    @Override
    public void enemyXPos(int xPos) {
        if (xPos < 100){
            this.xPos = 100;
            return;
        }
        if (xPos >= App.WIDTH - 100){
            this.xPos = App.WIDTH - 100;
            return;
        }
        this.xPos = xPos;
    }

    @Override
    public void enemyYPos(int yPos) {
        if (yPos < 100){
            this.yPos = 100;
            return;
        }
        if (yPos >= App.HEIGHT - 100){
            this.yPos = App.HEIGHT - 100;
            return;
        }
        this.yPos = yPos;
    }

    @Override
    public void enemyWidth(int width) {
        this.width = width;
    }

    @Override
    public void enemyHeight(int height) {
        this.height = height;
    }

    @Override
    public void enemySprite(Image sprite) {
        if (sprite == null) return;
        this.sprite = sprite;
    }

    @Override
    public void enemyHealth(int health) {
        this.health = health;
    }

    @Override
    public void enemySpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public void enemyStrategy(String strategy) {
        this.strategy = strategy;
    }

    @Override
    public void shoot(GameScreen gameScreen) {
        if (!gameScreen.areEnemyProjectilePresent()){
            gameScreen.spawnEnemyProjectile(this);
        }
    }

    public String getStrategy() {
        return strategy;
    }


    public void takeDamage(int damage){
        health -= damage;
    }


    @Override
    public void move(String key) {
        double moveAmount =  (this.speed * Enemy.speedMultiplier);
        if (key.equals("d")) {
            this.xPos += moveAmount;
        } else if (key.equals("a")) {
            this.xPos -= moveAmount;
        } else if (key.equals("s")) {
            this.yPos += moveAmount;
        }

    }
}
