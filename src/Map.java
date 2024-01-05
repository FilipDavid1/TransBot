import fri.shapesge.Obrazok;

/**
 * Trieda Mapa zobrazuje mapu a posúva ju.
 *
 * @author Filip Dávid
 *
 * @version 1.0
 */
public class Map {
    private final Obrazok mapaImg;
    private int x;
    private int y;

    /**
     * Konštruktor vytvorí obrazok mapy a zobrazí ho na súradniciach x a y.
     * @param x
     * @param y
     */
    public Map(int x, int y) {
        this.x = x;
        this.y = y;
        this.mapaImg = new Obrazok("src/pics/maps/map.png");
        this.mapaImg.zmenPolohu(x, y);
        this.mapaImg.zobraz();
    }

    /**
     * Metóda posunie mapu o 10 pixelov vodorovne.
     * Ak je x menšie ako -1620 tak sa x nastaví na 1610.
     */
    public void tik() {
        if (this.x > -1620) {
            this.x -= 10;
            this.mapaImg.posunVodorovne(-10);
        } else {
            this.x = 1610;
            this.mapaImg.zmenPolohu(this.x, this.y);
        }
    }


}
