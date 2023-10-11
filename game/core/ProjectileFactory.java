package game.core;

public interface ProjectileFactory {

    Projectile createProjectile(double xPos, double yPos, String strategy);

}
