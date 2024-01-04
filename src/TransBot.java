import fri.shapesge.DataObrazku;
import fri.shapesge.Obrazok;
/**
 * Trieda TransBot vytvára objekt transbota, ktorým hráč ovláda.
 * TransBot sa pohybuje po mape a strieľa na nepriateľov.
 * Zároveň má životy a bulletType.
 *
 * @author Filip Dávid
 *
 * @version 1.0
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

    /**
     * Konštruktor vytvorí objekt transbota na súradniciach x a y.
     */
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


    /**
     * Metóda updatne obrázok transbota podľa toho aký bulletType má.
     */
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

    /**
     * Metóda mení polohu transbota podľa toho či sa pohol.
     */
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

    /**
     * Metóda updatne obrázok transbota podľa toho aký bulletType má.
     */
    private void changeBulletType() {
        this.bulletType = BulletType.values()[this.bulletType.ordinal() + 1];
        if (this.bulletType.ordinal() > 4) {
            this.bulletType = BulletType.NORMAL;
        }
        this.updateTransBotImg();
    }

    /**
     * Metoda zníži atribút velY o 20.
     */
    public void moveUp() {
        this.velY -= 20;
    }

    /**
     * Metoda zvýši atribút velY o 20.
     */
    public void moveDown() {
        this.velY += 20;
    }

    /**
     * Metoda zníži atribút velX o 20.
     */
    public void moveToLeft() {
        this.velX -= 20;
    }

    /**
     * Metoda zvýši atribút velX o 20.
     */
    public void moveToRight() {
        this.velX += 20;
    }

    /**
     * Metoda zavolá metódu shoot z bulletManagera podľa toho aký bulletType má.
     */
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

    /**
     * Metoda zavolá metódu changeBullet z bulletManagera a zavolá metódu changeBulletType čím zmení bulletType.
     */
    public void changeBullet() {
        this.bulletManager.changeBulletType();
        this.changeBulletType();
    }

    /**
     * Metóda nastaví velX na 0.
     */
    public void stopX() {
        this.setVelX(0);
    }

    /**
     * Metóda nastaví velY na 0.
     */
    public void stopY() {
        this.setVelY(0);
    }



    /**
     * Metóda zavolá metódu getBulletManager z bulletManagera.
     * @return bulletManager
     */
    public BulletManager getBulletManager() {
        return this.bulletManager;
    }

    /**
     * Metóda nastaví velX na zadanú hodnotu.
     * @param velX
     */
    private void setVelX(int velX) {
        this.velX = velX;
    }

    /**
     * Metóda nastaví velY na zadanú hodnotu.
     * @param velY
     */
    private void setVelY(int velY) {
        this.velY = velY;
    }


    /**
     * Metóda vráti súradnicu X.
     * @return this.x
     */
    public int getX() {
        return this.x;
    }

    /**
     * Metóda vráti súradnicu Y.
     * @return this.y
     */
    public int getY() {
        return this.y;
    }

    /**
     * Metóda vráti šírku obrázku transbota / 2.
     * @return transBotImg / 2
     */
    public int getImageWidth() {
        return this.imageWidth / 2;
    }

    /**
     * Metóda vráti výšku obrázku transbota / 2.
     * @return transBotImg / 2
     */
    public int getImageHeight() {
        return this.imageHeight / 2;
    }

    /**
     * Metóda vráti životy transbota.
     * @return health
     */
    public Health getHealth() {
        return this.health;
    }

    /**
     * Metóda vráti bulletType.
     * @return bulletType
     */
    public BulletType getBulletType() {
        return this.bulletManager.getBulletType();
    }

    /**
     * Metóda skryje obrázok transbota.
     */
    public void hideImage() {
        this.transBotImg.skry();
    }

}
