import fri.shapesge.BlokTextu;

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

    }

    public void tik() {
        this.gameInfoText.zmenText("Score: " + this.score.getScore() + "Health: " + this.health.getHealth());
        this.gameInfoText.zobraz();
    }
}
