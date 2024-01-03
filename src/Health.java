/**
 * Trieda Health reprezentuje životy hráča.
 * Ak health klesne na 0 tak sa hra ukončí.
 *
 * @author Filip Dávid
 *
 * @version 1.0
 */
public class Health {
    private int health;

    /**
     * Konštruktor nastaví health na 250.
     */
    public Health() {
        this.health = 250;
    }

    /**
     * Metóda zníži health o 10.
     * Ak je health menší alebo rovný 0 tak sa health nastaví na 0 a hra sa ukončí.
     */
    public void decreaseHealth() {
        this.health -= 10;
        if (this.health <= 0) {
            this.health = 0;
            System.exit(0);
        }
    }

    /**
     * Metóda vráti health.
     * @return health
     */
    public int getHealth() {
        return this.health;
    }

}
