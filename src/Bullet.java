import fri.shapesge.DataObrazku;
import fri.shapesge.Obrazok;

/**
 * Trieda Bullet vytvára náboj, ktorý sa pohybuje v smere, ktorý je nastavený v bulletDirection.
 *
 * @author Filip Dávid
 *
 * @version 1.0
 */
public class Bullet {
    private final Obrazok nabojImg;
    private int x;
    private int y;

    private final int imageWidth;
    private final int imageHeight;
    private final BulletType bulletType;

    private final BulletDirection bulletDirection;

    /**
     * Konštruktor nastaví atribúty na vstupné parametre a zmení pozíciu obrázku na x a y a zobrazí ho.
     * @param x súradnica x
     * @param y súradnica y
     * @param bulletType typ náboja
     * @param bulletDirection smer náboja
     */
    public Bullet(int x, int y, BulletType bulletType, BulletDirection bulletDirection) {
        this.bulletType = bulletType;
        this.nabojImg = new Obrazok("../pics/amo/" + this.getBulletName() + ".png");
        DataObrazku image = new DataObrazku("../pics/amo/" + this.getBulletName() + ".png");
        this.imageHeight = image.getVyska();
        this.imageWidth = image.getSirka();
        this.x = x;
        this.y = y;
        this.nabojImg.zmenPolohu(x, y);
        this.nabojImg.zobraz();
        this.bulletDirection = bulletDirection;
    }

    /**
     * Metóda posúva náboj smerom, ktorý je nastavený v bulletDirection.
     */
    public void tik() {
        if (this.bulletDirection == BulletDirection.UP) {
            this.moveUp();
        } else if (this.bulletDirection == BulletDirection.DOWN) {
            this.moveDown();
        } else if (this.bulletDirection == BulletDirection.LEFT) {
            this.moveLeft();
        } else if (this.bulletDirection == BulletDirection.RIGHT) {
            this.moveRight();
        } else if (this.bulletDirection == BulletDirection.UP_LEFT) {
            this.moveUp();
            this.moveLeft();
        } else if (this.bulletDirection == BulletDirection.UP_RIGHT) {
            this.moveUp();
            this.moveRight();
        } else if (this.bulletDirection == BulletDirection.DOWN_LEFT) {
            this.moveDown();
            this.moveLeft();
        } else if (this.bulletDirection == BulletDirection.DOWN_RIGHT) {
            this.moveDown();
            this.moveRight();
        }
    }

    /**
     * Metóda posúva náboj zvisle o -20px alebo -10px ak je bulletType BEAM.
     */
    private void moveUp() {
        if (this.bulletType == BulletType.BEAM) {
            this.nabojImg.posunZvisle(-10);
            this.y -= 10;
        } else {
            this.nabojImg.posunZvisle(-20);
            this.y -= 20;
        }
    }

    /**
     * Metóda posúva náboj zvisle o 20px alebo 10px ak je bulletType BEAM.
     */
    private void moveDown() {
        //if bullet type is beam than move slower
        if (this.bulletType == BulletType.BEAM) {
            this.nabojImg.posunZvisle(10);
            this.y += 10;
        } else {
            this.nabojImg.posunZvisle(20);
            this.y += 20;
        }
    }

    /**
     * Metóda posúva náboj vodorovne o -20px.
     */
    private void moveLeft() {
        this.nabojImg.posunVodorovne(-20);
        this.x -= 20;
    }

    /**
     * Metóda posúva náboj vodorovne o 20px.
     */
    private void moveRight() {

        this.nabojImg.posunVodorovne(20);
        this.x += 20;

    }

    /**
     * Metóda vráti x pozíciu náboja.
     * @return x
     */
    public int getX() {
        return this.x;
    }

    /**
     * Metóda vráti y pozíciu náboja.
     * @return y
     */
    public int getY() {
        return this.y;
    }

    /**
     * Metóda skryje obrázok náboja.
     */
    public void hideImage() {
        this.nabojImg.skry();
    }

    /**
     * Metóda vráti názov obrázku náboja.
     * @return názov obrázku náboja
     */
    public String getBulletName() {
        switch (this.bulletType) {
            case CANNON:
                return "cannon";
            case BEAM:
                return "beam";
            case SWORD_FIRE:
                return "sword_fire";
            case DIFFUSION_BEAM:
                return "diffusion_beam";
            case ENEMY_BULLET:
                return "enemy_bullet";
            default:
                return "normal";
        }
    }

    /**
     * Metóda vráti bulletType.
     * @return bulletType
     */
    public BulletType getBulletType() {
        return this.bulletType;
    }

    /**
     * Metóda vráti imageWidth / 2.
     * @return imageWidth / 2
     */
    public int getImageWidth() {
        return imageWidth / 2;
    }

    /**
     * Metóda vráti imageHeight / 2.
     * @return imageHeight / 2
     */
    public int getImageHeight() {
        return imageHeight / 2;
    }

    /**
     * Metóda zmení uhol obrázku náboja.
     * @param angle
     */
    public void changeAngle(int angle) {
        this.nabojImg.zmenUhol(angle);
    }
}
