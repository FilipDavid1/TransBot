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
    private int zbran;

    private BulletManager bulletManager;

    private int imageWidth;
    private int imageHeight;

    private DataObrazku dataObrazku;
    
    public TransBot(int x, int y) {
        this.transBotImg = new Obrazok("src/pics/transBot/transBotJet.png");
        this.x = x;
        this.y = y;
        this.transBotImg.zmenPolohu(x, y);
        this.transBotImg.zobraz();
        this.zbran = 0;
        this.bulletManager = new BulletManager();
        this.dataObrazku = new DataObrazku("src/pics/transBot/transBotJet.png");
        this.imageHeight = dataObrazku.getVyska();
        this.imageWidth = dataObrazku.getSirka();
    }


    private void updateTransBotImg() {
        if (zbran == 0 || zbran == 1) {
            this.transBotImg.skry();
            this.transBotImg = new Obrazok("src/pics/transBot/transBotJet.png");
            this.dataObrazku = new DataObrazku("src/pics/transBot/transBotJet.png");
            this.imageHeight = dataObrazku.getVyska();
            this.imageWidth = dataObrazku.getSirka();
        } else {
            this.transBotImg.skry();
            this.transBotImg = new Obrazok("src/pics/transBot/transBot.png");
            this.dataObrazku = new DataObrazku("src/pics/transBot/transBot.png");
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

    private void pripocitajZbran() {
        this.zbran++;
        if (this.zbran > 4) {
            this.zbran = 0;
        }
        this.updateTransBotImg();
    }
    public void posunHore() {
        this.velY -= 20;
    }

    public void posunDole() {
        this.velY += 20;
    }

    public void posunVlavo() {
        this.velX -= 20;
    }

    public void posunVpravo() {
        this.velX += 20;
    }

    public void aktivuj() {
        if (this.zbran >= 2) {
            this.bulletManager.shoot(this.x + 35, this.y - 20);
        } else {
            this.bulletManager.shoot(this.x + 50, this.y + 10);
        }
    }

    public void zmenNaboj() {
        this.bulletManager.zmenNaboje();
        this.pripocitajZbran();
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

}
