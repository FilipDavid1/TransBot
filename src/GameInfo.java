import fri.shapesge.BlokTextu;
import fri.shapesge.StylFontu;

/**
 * Trieda GameInfo zobrazuje score, health a bulletType.
 *
 * @author Filip Dávid
 *
 * @version 1.0
 */
public class GameInfo {
    private Score score;

    private Health health;

    private BlokTextu scoreText;

    private BlokTextu healthText;

    private BlokTextu bulletTypeText;

    private TransBot transBot;

    /**
     * Konštruktor vytvorí objekt GameInfo v ktorom sa zobrazuje score, health a bulletType.
     * @param score
     * @param health
     * @param transBot
     */
    public GameInfo(Score score, Health health, TransBot transBot) {
        this.score = score;
        this.health = health;
        this.transBot = transBot;

        this.scoreText = new BlokTextu("Score: " + this.score.getScore(), 670, 30);
        this.scoreText.zmenFont("Helvetica", StylFontu.PLAIN, 20);
        this.scoreText.zobraz();

        this.healthText = new BlokTextu("Health: " + this.health.getHealth(), 20, 30);
        this.healthText.zmenFont("Helvetica", StylFontu.PLAIN, 20);
        this.healthText.zobraz();

        this.bulletTypeText = new BlokTextu("Bullet Type: " + this.transBot.getBulletType(), 1200, 30);
        this.bulletTypeText.zmenFont("Helvetica", StylFontu.PLAIN, 20);
        this.bulletTypeText.zobraz();

    }

    /**
     * Metóda skryje score, health a bulletType.
     */
    public void hideInfo() {
        this.scoreText.skry();
        this.healthText.skry();
        this.bulletTypeText.skry();
    }

    /**
     * Metóda zmení text v gameInfoText.
     */
    public void tik() {
        this.scoreText.zmenText("Score: " + this.score.getScore());
        this.healthText.zmenText("Health: " + this.health.getHealth());
        if (this.transBot.getBulletType() == BulletType.DIFFUSION_BEAM) {
        //move bulletTypeText to the left
            this.bulletTypeText.zmenPolohu(1100, 30);
        } else {
            this.bulletTypeText.zmenPolohu(1200, 30);
        }
        this.bulletTypeText.zmenText("Bullet Type: " + this.transBot.getBulletType());
    }
}
