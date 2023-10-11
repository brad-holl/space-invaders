
package game.core;

import javafx.scene.canvas.GraphicsContext;

public class FastProjectile implements ProjectileMovementStrategy  {
    private static final double SPEED_MULTIPLIER = 2;

    @Override
    public double moveProjectile(int speed, double yPos, boolean reverse) {
        yPos -= speed * SPEED_MULTIPLIER * (reverse ? -1 : 1);
        return yPos;
    }


}

