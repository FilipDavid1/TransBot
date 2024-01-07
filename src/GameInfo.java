import fri.shapesge.BlokTextu;
import fri.shapesge.Obrazok;
import fri.shapesge.StylFontu;

/**
 * Trieda GameInfo zobrazuje score, health a bulletType.
 *
 * @author Filip Dávid
 *
 * @version 1.0
 */
public class GameInfo {
    private final Score score;

    private final Health health;

    private final BlokTextu scoreText;

    private final BlokTextu healthText;

    private final BlokTextu bulletTypeText;

    private final TransBot transBot;

    private final Obrazok bulletTypeImg;

    /**
     * Konštruktor vytvorí objekt GameInfo v ktorom sa zobrazuje score, health a bulletType.
     * @param score skóre
     * @param health životy
     * @param transBot transbot
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

        this.bulletTypeImg = new Obrazok(this.transBot.getBulletImg());
        this.bulletTypeImg.zmenPolohu(1320, 10);
        this.bulletTypeImg.zobraz();

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

        this.bulletTypeText.zmenText("Bullet Type:          " +  this.transBot.getTime());
        this.bulletTypeImg.zmenObrazok(this.transBot.getBulletImg());

    }
}
