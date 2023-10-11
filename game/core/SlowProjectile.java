
package game.core;

public class SlowProjectile implements ProjectileMovementStrategy{
    private static final double SPEED_MULTIPLIER = 1;
    @Override
    public double moveProjectile(int speed, double yPos, boolean reverse) {
        yPos -= speed * SPEED_MULTIPLIER * (reverse ? -1 : 1);
        return yPos;
    }



}

