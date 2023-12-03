import fri.shapesge.Obrazok;
/**
 * Write a description of class Mapa here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Mapa {
    private Obrazok mapaImg;
    private int x;
    private int y;
    private int mapWidth;
    
    public Mapa(int x, int y) {
        this.x = x;
        this.y = y;
        this.mapWidth = 1460;
        this.mapaImg = new Obrazok("src/pics/maps/robo.png");
        this.mapaImg.zmenPolohu(x, y);
        this.mapaImg.zobraz();
    }
    
    public void tik() {
        if (this.x > -1620) { //polovica obrazka - 10  1620 × 242
            this.x -= 10;
            this.mapaImg.posunVodorovne(-10);
        } else {
            this.x = 1610; // sirka obrazka + polovica obrazka
            this.mapaImg.zmenPolohu(this.x, this.y);
        }
    }


}
