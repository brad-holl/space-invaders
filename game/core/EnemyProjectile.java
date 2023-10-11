
package game.core;

import game.App;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class EnemyProjectile extends GameObject implements Projectile{

    private int damage;


    private ProjectileMovementStrategy strategy;

    public EnemyProjectile(double xPos, double yPos, String strategy) {
        super(xPos, yPos, pWidth, pHeight);

        setSpeed((int) App.gameStats.getProjectileSpeed());
        damage = (int) App.gameStats.getProjectileDamage();

    }




    public void setStrategy(ProjectileMovementStrategy strategy){
        this.strategy = strategy;
    }

    public void executeStrategy(){
        if (strategy != null) {
            this.yPos = strategy.moveProjectile(this.getSpeed(), this.getyPos(), true);
        }
    }


    @Override
    public boolean collisionDetected(GameObject other) {
        boolean colliding = true;
        if (this.getxPos() + this.getWidth() < other.getxPos()) colliding = false;
        if (this.getxPos() > other.getxPos() + other.getWidth()) colliding = false;
        if (this.getyPos() + this.getHeight() < other.getyPos()) colliding = false;
        if (this.getyPos() > other.getyPos() + other.getHeight()) colliding = false;
        return colliding;
    }

    @Override
    public void update() {
        executeStrategy();
//        yPos = movementStrategy.moveProjectile(speed, yPos, true);
        if (yPos >= App.HEIGHT) setAlive(false);
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(xPos, yPos, pWidth, pHeight);
    }


}

