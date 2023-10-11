package game.core;

public interface ProjectileMovementStrategy {

    double moveProjectile(int speed, double yPos, boolean reverse);
}
