import fri.shapesge.BlokTextu;
import fri.shapesge.Obrazok;
import fri.shapesge.StylFontu;

public class GameMenu {
    private final Obrazok startScreen;

    private final BlokTextu controlsText;


    /**
     * Konštruktor zobrazí úvodnú obrazovku hry a text s ovládaním.
     */
    public GameMenu() {
        this.startScreen = new Obrazok("src/pics/startScreen.png");
        this.startScreen.zmenPolohu(0, -20);
        this.startScreen.zobraz();
        this.controlsText = new BlokTextu("Enter - start game" +
                                                "\nArrow up - up" +
                                                "\nArrow down - down" +
                                                "\nArrow left - left" +
                                                "\nArrow left - left" +
                                                "\nA - change bullet" +
                                                "\nR - restart game" +
                                                "\nSPACE - shoot", 635, 570);
        this.controlsText.zmenFont("Helvetica", StylFontu.BOLD, 20);
        this.controlsText.zmenFarbu("white");
        this.controlsText.zobraz();
    }

    /**
     * Metóda skryje úvodnú obrazovku hry.
     */
    public void hideMenu() {
        this.startScreen.skry();
        this.controlsText.skry();
    }
}
