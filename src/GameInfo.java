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

    private BlokTextu gameInfoText;

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
        this.gameInfoText = new BlokTextu(
                "Score: " + this.score.getScore() +
                        " | Health: " + this.health.getHealth() +
                        " | Bullet Type: " + this.transBot.getBulletType(), 200, 25);
        this.gameInfoText.zmenFont("Arial", StylFontu.BOLD, 20);
        this.gameInfoText.zmenFarbu("black");
        this.gameInfoText.zobraz();

    }

    /**
     * Metóda zmení text v gameInfoText.
     */
    public void tik() {
        this.gameInfoText.zmenText(
                "Score: " + this.score.getScore() +
                        " | Health: " + this.health.getHealth() +
                        " | Bullet Type: " + this.transBot.getBulletType()
        );

        this.gameInfoText.zobraz();
    }
}
