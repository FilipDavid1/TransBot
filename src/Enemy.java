import fri.shapesge.DataObrazku;
import fri.shapesge.Obrazok;
import fri.shapesge.DataObrazku;
/**
 * Write a description of class Enemy here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Enemy {
    private Obrazok enemyImg;
    private int x;
    private int y;
    private int imageWidth;
    private int imageHeight;
    private boolean movingDown;
    private boolean movingRight;
    
    public Enemy(int x, int y) {
        this.enemyImg = new Obrazok("/Users/filipdavid/Documents/INF/TransBotInteliJ/src/pics/enemy/psyball.png");
        DataObrazku image = new DataObrazku("/Users/filipdavid/Documents/INF/TransBotInteliJ/src/pics/enemy/psyball.png");
        this.imageHeight = image.getVyska();
        this.imageWidth = image.getSirka();
        this.x = x;
        this.y = y;
        this.enemyImg.zmenPolohu(this.x, this.y);   //nefunguje v novej kniznici
        this.enemyImg.zobraz();
        this.movingDown = false;
        this.movingRight = false;
    }


    public void tik() {
        this.moveDown();
        this.moveUp();
        this.moveLeft();
    }

    private void moveDown() {
        if (this.y < 500 && this.movingDown) {
            this.enemyImg.posunZvisle(10);
            this.y += 10;

        } else {
            this.movingDown = false;
        }
    }

    private void moveUp() {
        if (this.y >= 100 && !this.movingDown) {
            this.enemyImg.posunZvisle(-10);
            this.y -= 10;

        } else {
            this.movingDown = true;
        }
    }

    private void moveLeft() {
        this.enemyImg.posunVodorovne(-3);
        this.x -= 3;
        if (this.x <= -30) {
            this.x = 1440;
            this.enemyImg.zmenPolohu(this.x, this.y);
        }
    }

    public boolean kolizia(Bullet bullet) {
        return (bullet.getX() >= this.x && bullet.getY() <= this.x + 83 && bullet.getY() >= this.y && bullet.getY() <= this.y + 87);
    }

    public int getImageWidth() {
        return imageWidth / 2;
    }

    public int getImageHeight() {
        return imageHeight / 2;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void skryObrazok() {
        this.enemyImg.skry();
    }

}
