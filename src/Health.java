import javax.swing.JOptionPane;

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
     * Ak je health menší alebo rovný 0 tak sa health nastaví na 0 a zobrazí sa hlásenie o konci hry.
     */
    public void decreaseHealth() {
        this.health -= 10;
        if (this.health <= 0) {
            this.health = 0;
            int choice = JOptionPane.showOptionDialog(
                    null,
                    "Game Over",
                    "Game Over",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.WARNING_MESSAGE,
                    null,
                    new String[]{"Restart", "Exit"},
                    "Exit");

            if (choice == 0) {
                // Restart the game
                Game.getInstance().restartGame();
            } else {
                // Exit the game
                JOptionPane.showMessageDialog(null, "Exiting the game!");
                System.exit(0);
            }
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
