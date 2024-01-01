import fri.shapesge.BlokTextu;
import fri.shapesge.StylFontu;

public class GameInfo {
    private Score score;

    private Health health;

    private BlokTextu gameInfoText;

    public GameInfo(Score score, Health health) {
        this.score = score;
        this.health = health;
        this.gameInfoText = new BlokTextu("Score: " + this.score.getScore() + "Health: " + this.health.getHealth(), 200, 15);
        this.gameInfoText.zmenFarbu("black");
        this.gameInfoText.zobraz();

//        BlokTextu levelText = new BlokTextu("Level: 1", 550, 450);
//        levelText.zmenFont("Arial", StylFontu.BOLD, 100);
//        levelText.zmenFarbu("black");
//        levelText.zobraz();

    }

    public void tik() {
        this.gameInfoText.zmenText("Score: " + this.score.getScore() + "Health: " + this.health.getHealth());
        this.gameInfoText.zobraz();
    }
}
