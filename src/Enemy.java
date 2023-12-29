import fri.shapesge.DataObrazku;
import fri.shapesge.Obrazok;

import java.util.Random;

/**
 * Write a description of class Enemy here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Enemy {
    private final Obrazok enemyImg;
    private int x;
    private int y;
    private final int imageWidth;
    private final int imageHeight;
    private boolean movingDown;

    private EnemyType type;

    private int randomNumber;

    private int angle;

    private BulletManager bulletManager;
    
    public Enemy(int x, int y, EnemyType type) {
        this.type = type;
        this.enemyImg = new Obrazok("src/pics/enemy/" + this.type.getEnemyImg() + ".png");
        DataObrazku image = new DataObrazku("src/pics/enemy/" + this.type.getEnemyImg() + ".png");
        this.imageHeight = image.getVyska();
        this.imageWidth = image.getSirka();
        this.x = x;
        this.y = y;
        this.enemyImg.zmenPolohu(this.x, this.y);
        this.enemyImg.zobraz();
        this.movingDown = false;
        Random random = new Random();
        this.randomNumber = random.nextInt(800) + 1;
        this.angle = 0;
        this.bulletManager = new BulletManager();
    }





    public void tik() {
        if (this.type == EnemyType.PSYBALL || this.type == EnemyType.ASCULE) {
            this.moveLeft();
            this.moveDown();
            this.moveUp();
        }  else if (this.type == EnemyType.ELBLINK) {
            this.elblinkMovement();
        } else if (this.type == EnemyType.LUVOGUE) {
            this.luvoqueMovement();
        } else if (this.type == EnemyType.BIFLER) {
            this.biflerMovement();
        } else if (this.type == EnemyType.HILUN) {
            this.hilunMovement();
        } else if (this.type == EnemyType.ALAPOT) {
            this.alapotMovement();
        }
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
        this.enemyImg.posunVodorovne(-5);
        this.x -= 5;
        if (this.x <= -30) {
            this.x = 1440;
            this.enemyImg.zmenPolohu(this.x, this.y);
        }
    }

    private void elblinkMovement() {
        //move enemy left until it reaches x that is same as random x then move quickly up or down
        if (this.x > this.randomNumber) {
            this.moveLeft();
        } else {
            if (this.y < 800 && this.movingDown) {
                this.enemyImg.posunZvisle(20);
                this.y += 20;

            } else {
                this.movingDown = false;
            }
            if (this.y >= 0 && !this.movingDown) {
                this.enemyImg.posunZvisle(-20);
                this.y -= 20;

            } else {
                this.movingDown = true;
            }
        }
    }

    private void luvoqueMovement() {
        //spin image constantly and slowly move up and left
        this.enemyImg.zmenUhol(angle);
        angle += 10;
        this.enemyImg.posunZvisle(-2);
        this.enemyImg.posunVodorovne(-7);
        this.x -= 7;
        this.y -= 2;
    }

    public void biflerMovement() {
        //fly in from right to left with random speed

        this.enemyImg.posunVodorovne(-this.randomNumber / 50 ) ;
        this.x -= this.randomNumber / 50;

    }

    public void hilunMovement() {
        //fly from right to left with random speed and rotate image
        this.enemyImg.posunVodorovne(-this.randomNumber / 40 ) ;
        this.x -= this.randomNumber / 40;

        this.enemyImg.zmenUhol(angle);
        angle += 10;

    }

    public void alapotMovement() {
        //random rotation - 0 or 180 degrees after random time send to top or bottom of screen depending on rotation
        this.enemyImg.posunVodorovne(-10) ;
        this.x -= 10;
        if (this.y <= 449) {
            this.enemyImg.zmenUhol(180);
            //this.enemyImg.posunZvisle(800);
            //this.y += 800;
        } else {
            this.enemyImg.zmenUhol(0);
            //this.enemyImg.posunZvisle(-800);
            //this.y -= 800;
        }
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
