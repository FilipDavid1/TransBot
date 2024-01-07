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


    /**
     * Metóda vráti náhodný bullet type bez enemy bullet.
     * @return náhodný bullet type bez enemy bullet
     */
    public static BulletType getRandomBulletType() {
        //random bullet type without enemy bullet
        return values()[(int)(Math.random() * (values().length - 1))];
    }
}
