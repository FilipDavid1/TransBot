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

    private BulletDirection bulletDirection;

    public Bullet(int x, int y, BulletType bulletType, BulletDirection bulletDirection) {
        this.bulletType = bulletType;
        this.nabojImg = new Obrazok("src/pics/amo/" + this.getNazovNaboja() + ".png");
        DataObrazku image = new DataObrazku("src/pics/amo/" + this.getNazovNaboja() + ".png");
        this.imageHeight = image.getVyska();
        this.imageWidth = image.getSirka();
        this.x = x;
        this.y = y;
        this.nabojImg.zmenPolohu(x, y);
        this.nabojImg.zobraz();
        this.bulletDirection = bulletDirection;
    }
    
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

    private void moveUp() {
        //if bullet type is beam than move slower
        if (this.bulletType == BulletType.BEAM) {
            this.nabojImg.posunZvisle(-10);
            this.y -= 10;
        } else {
            this.nabojImg.posunZvisle(-20);
            this.y -= 20;
        }
    }

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

    private void moveLeft() {
        this.nabojImg.posunVodorovne(-20);
        this.x -= 20;
    }

    private void moveRight() {

        this.nabojImg.posunVodorovne(20);
        this.x += 20;

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
            case ENEMY_BULLET:
                return "enemy_bullet";
            default:
                return "normal";
        }
    }

    public BulletType getBulletType() {
        return this.bulletType;
    }

    public int getImageWidth() {
        return imageWidth / 2;
    }

    public int getImageHeight() {
        return imageHeight / 2;
    }

    public void zmenUhol(int uhol) {
        this.nabojImg.zmenUhol(uhol);
    }
}
