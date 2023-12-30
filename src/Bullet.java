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
    private BulletType bulletType;
    
    public Bullet(int x, int y, BulletType bulletType) {
        this.bulletType = bulletType;
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
        switch (this.bulletType) {
            case CANNON:
                return "cannon";
            case BEAM:
                return "beam";
            case SWORD_FIRE:
                return "sword_fire";
            case DIFFUSION_BEAM:
                return "diffusion_beam";
            default:
                return "normal";
        }
    }

    public int getImageWidth() {
        return imageWidth / 2;
    }

    public int getImageHeight() {
        return imageHeight / 2;
    }
}
