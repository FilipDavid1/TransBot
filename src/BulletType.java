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
        return values()[(int)(Math.random() * (values().length - 1))];
    }

    /**
     * Metóda vráti názov obrázku náboja.
     * @return názov obrázku náboja
     */
    public String getBulletName() {
        switch (this) {
            case CANNON:
                return "cannon";
            case BEAM:
                return "beam";
            case SWORD_FIRE:
                return "sword_fire";
            case DIFFUSION_BEAM:
                return "diffusion_beam";
            default:
                return "normal";
        }
    }
}
