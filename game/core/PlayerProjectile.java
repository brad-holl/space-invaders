package game.core;

import game.App;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PlayerProjectile extends GameObject implements Projectile{
    private int damage;
    public PlayerProjectile(double xPos, double yPos) {
        super(xPos, yPos, pWidth, pHeight);
        setSpeed((int) App.gameStats.getProjectileSpeed());
        damage = (int) App.gameStats.getProjectileDamage();
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
        yPos -= speed;
        if (yPos <= 0) setAlive(false);
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(xPos, yPos, width, height);
    }


    @Override
    public void setStrategy(ProjectileMovementStrategy strategy) {
    }
}

