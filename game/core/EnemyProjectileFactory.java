
package game.core;

public class EnemyProjectileFactory implements ProjectileFactory {
    @Override
    public Projectile createProjectile(double xPos, double yPos, String strategy) {
        return new EnemyProjectile(xPos, yPos, strategy);
    }
}

