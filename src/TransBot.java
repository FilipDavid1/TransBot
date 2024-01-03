import fri.shapesge.DataObrazku;
import fri.shapesge.Obrazok;
/**
 * Write a description of class TransBot here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TransBot {
    private Obrazok transBotImg;
    private int x;
    private int y;
    private int velX;
    private int velY;

    private BulletType bulletType;

    private final BulletManager bulletManager;

    private int imageWidth;
    private int imageHeight;

    private DataObrazku dataObrazku;

    private Health health;
    
    public TransBot(int x, int y) {
        this.transBotImg = new Obrazok("../pics/transBot/transBotJet.png");
        this.x = x;
        this.y = y;
        this.transBotImg.zmenPolohu(x, y);
        this.transBotImg.zobraz();
        this.bulletType = BulletType.NORMAL;
        this.bulletManager = new BulletManager(this.bulletType);
        this.dataObrazku = new DataObrazku("../pics/transBot/transBotJet.png");
        this.imageHeight = dataObrazku.getVyska();
        this.imageWidth = dataObrazku.getSirka();
        this.health = new Health();

    }


    private void updateTransBotImg() {
        if (this.bulletType == BulletType.NORMAL || this.bulletType == BulletType.CANNON) {
            this.transBotImg.skry();
            this.transBotImg = new Obrazok("../pics/transBot/transBotJet.png");
            this.dataObrazku = new DataObrazku("../pics/transBot/transBotJet.png");
            this.imageHeight = dataObrazku.getVyska();
            this.imageWidth = dataObrazku.getSirka();
        } else {
            this.transBotImg.skry();
            this.transBotImg = new Obrazok("../pics/transBot/transBot.png");
            this.dataObrazku = new DataObrazku("../pics/transBot/transBot.png");
            this.imageHeight = dataObrazku.getVyska();
            this.imageWidth = dataObrazku.getSirka();
        }
        this.transBotImg.zmenPolohu(this.x, this.y);
        this.transBotImg.zobraz();
    }
    public void tik() {
        this.x += this.velX;
        this.y += this.velY;

        if (this.y <= -20) {
            this.y = -20;
        } else if (this.y >= 720) {
            this.y = 720;
        }
        
        if (this.x <= 0) {
            this.x = 0;
        } else if (this.x >= 1320) {
            this.x = 1320;
        }
        
        
        this.transBotImg.zmenPolohu(this.x, this.y);
    }

    private void changeBulletType() {
        this.bulletType = BulletType.values()[this.bulletType.ordinal() + 1];
        if (this.bulletType.ordinal() > 4) {
            this.bulletType = BulletType.NORMAL;
        }
        this.updateTransBotImg();
    }
    public void moveUp() {
        this.velY -= 20;
    }

    public void moveDown() {
        this.velY += 20;
    }

    public void moveToLeft() {
        this.velX -= 20;
    }

    public void moveToRight() {
        this.velX += 20;
    }

    public void shoot() {
        if (this.bulletType == BulletType.NORMAL) {
            this.bulletManager.shoot(this.x + 35, this.y + 20);
        } else if (this.bulletType == BulletType.BEAM) {
            this.bulletManager.shoot3Directions(this.x + 25, this.y - 25);
        } else if (this.bulletType == BulletType.SWORD_FIRE) {
            this.bulletManager.shoot2Directions(this.x + 50, this.y - 25);
        } else if (this.bulletType == BulletType.CANNON) {
            this.bulletManager.burst(this.x + 35, this.y + 20);
        } else {
            this.bulletManager.shoot(this.x + 50, this.y - 25);
        }
    }

    public void changeBullet() {
        this.bulletManager.changeBulletType();
        this.changeBulletType();
    }
    
    public void stopX() {
        this.setVelX(0);
    }

    public void stopY() {
        this.setVelY(0);
    }



    public BulletManager getBulletManager() {
        return this.bulletManager;
    }
    private void setVelX(int velX) {
        this.velX = velX;
    }

    private void setVelY(int velY) {
        this.velY = velY;
    }


    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getImageWidth() {
        return this.imageWidth / 2;
    }

    public int getImageHeight() {
        return this.imageHeight / 2;
    }

    public Health getHealth() {
        return this.health;
    }

}
