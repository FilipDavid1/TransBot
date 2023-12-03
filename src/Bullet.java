import fri.shapesge.DataObrazku;
import fri.shapesge.Obrazok;
/**
 * Write a description of class Naboj here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Bullet {
    private Obrazok nabojImg;
    private int x;
    private int y;

    private int imageWidth;
    private int imageHeight;
    private int aktualnyNaboj;
    
    public Bullet(int x, int y, int aktualny) {
        this.aktualnyNaboj = aktualny;
        this.nabojImg = new Obrazok("src/pics/amo/" + this.getNazovNaboja() + ".png");
        DataObrazku image = new DataObrazku("src/pics/amo/" + this.getNazovNaboja() + ".png");
        this.imageHeight = image.getVyska();
        this.imageWidth = image.getSirka();
        this.x = x;
        this.y = y;
        this.nabojImg.zmenPolohu(x, y);
        this.nabojImg.zobraz();
    }
    
    public void tik() {
        if (this.x <= 1430) {
            this.x += 30;
            this.nabojImg.posunVodorovne(30);
        } else {
            this.nabojImg.skry();
        }
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void skryObrazok() {
        this.nabojImg.skry();
    }
    
    public String getNazovNaboja() {
        switch (this.aktualnyNaboj) {
            case 0:
                return "normal";
            
            case 1:
                return "cannon";
            
            case 2:
                return "beam";
                
            case 3:
                return "sword_fire";
                
            case 4:
                return "diffusion_beam";
            
            default:
                return String.valueOf(this.aktualnyNaboj);
        }
    }

    public int getImageWidth() {
        return imageWidth / 2;
    }

    public int getImageHeight() {
        return imageHeight / 2;
    }
}
