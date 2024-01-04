/**
 * Trieda BulletType slúži na definíciu Enum prvkov
 *
 * @author Filip Dávid
 *
 * @version 1.0
 */

public enum BulletType {
    NORMAL,
    CANNON,
    BEAM,
    SWORD_FIRE,
    DIFFUSION_BEAM,
    ENEMY_BULLET;


    public BulletType getRandomBulletType() {
        return values()[(int)(Math.random() * values().length)];
    }
}
