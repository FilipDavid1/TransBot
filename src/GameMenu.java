import fri.shapesge.Obrazok;

public class GameMenu {
    private Obrazok startScreen;


    /**
     * Konštruktor zobrazí úvodnú obrazovku hry.
     */
    public GameMenu() {
        this.startScreen = new Obrazok("src/pics/startScreen.png");
        this.startScreen.zmenPolohu(0, -20);
        this.startScreen.zobraz();
    }

    /**
     * Metóda skryje úvodnú obrazovku hry.
     */
    public void hideMenu() {
        this.startScreen.skry();
    }
}
