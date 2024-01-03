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

    private final TransBot bot;

    private boolean hasReachedBot;

    private int health;

    private int timer;

    private boolean isDead;

    private final BulletManager bulletManager;
    public Enemy(int x, int y, EnemyType type, TransBot bot) {
        this.type = type;
        this.enemyImg = new Obrazok("../pics/enemy/" + this.type.getEnemyImg() + ".png");
        DataObrazku image = new DataObrazku("../pics/enemy/" + this.type.getEnemyImg() + ".png");
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
        //this.bulletManager = new BulletManager();
        this.bot = bot;
        this.hasReachedBot = false;
        this.health = 3;
        this.bulletManager = new BulletManager(BulletType.ENEMY_BULLET);
        this.timer = 50;
        this.isDead = false;
    }





    public void tik() {
        if (this.type == EnemyType.PSYBALL || this.type == EnemyType.ASCULE) {
            this.psyballMovement();
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
        } else if (this.type == EnemyType.ZELNUC) {
            this.zelnucMovement();
        } else if (this.type == EnemyType.GELPAAR) {
            this.gelpaarMovement();
        } else if (this.type == EnemyType.GEALMEA) {
            this.gealmeaMovement();
        } else if (this.type == EnemyType.BOASITE) {
            this.boasiteMovement();
        }
    }

    private void psyballMovement() {
        //move in sine wave between y 0 and 900
        if (this.type == EnemyType.PSYBALL) {
            if (this.x > 10 && !this.hasReachedBot) {
                this.enemyImg.posunVodorovne(-10);
                this.x -= 10;
            } else {
                this.hasReachedBot = true;
            }

            if (this.hasReachedBot) {
                this.enemyImg.posunVodorovne(10);
                this.x += 10;
            }
        } else {
            if (this.x < 1300 && !this.hasReachedBot) {
                this.enemyImg.posunVodorovne(10);
                this.x += 10;
            } else {
                this.hasReachedBot = true;
            }

            if (this.hasReachedBot) {
                this.enemyImg.posunVodorovne(-10);
                this.x -= 10;
            }
        }
        //rotate image
        this.enemyImg.zmenUhol(angle);
        angle += 10;

        this.enemyImg.posunZvisle((int)(Math.cos(this.x * 0.005) * 11));
        this.y += (int)(Math.cos(this.x * 0.005) * 11);


    }

    private void elblinkMovement() {
        //move enemy left the moment this.x is more than this.bot.getX() set hasReachedBot to true and only move down or up depending on this.y

        if (this.x > this.bot.getX()) {
            this.enemyImg.posunVodorovne(-10);
            this.x -= 10;
        } else {
            this.hasReachedBot = true;
        }

        //send enemy one way randomly
        if (this.hasReachedBot) {
            if (this.y < 800 && this.movingDown) {
                this.enemyImg.posunZvisle(15);
                this.y += 15;

            } else {
                this.movingDown = false;
            }

            if (this.y >= 10 && !this.movingDown) {
                this.enemyImg.posunZvisle(-15);
                this.y -= 15;

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

        if (this.randomNumber / 30 <= 5) {
            this.enemyImg.posunVodorovne(-10 ) ;
            this.x -= 10;
        } else {
            this.enemyImg.posunVodorovne(-this.randomNumber / 30 ) ;
            this.x -= this.randomNumber / 30;
        }

    }

    public void hilunMovement() {
        //fly with random speed and rotate image and go after bot
        if (-this.randomNumber / 40 <= 0) {
            this.enemyImg.posunVodorovne(-5 ) ;
            this.x -= 5;
        } else {
            this.enemyImg.posunVodorovne(-this.randomNumber / 40 ) ;
            this.x -= this.randomNumber / 40;
        }

        if (!this.isDead) {
            if (this.timer == 0 && this.x > 0 ) {
                this.bulletManager.shoot4Directions(this.x - this.imageWidth / 2, this.y);
                this.timer = 50;
            } else {
                this.timer--;
            }
        }



    }

    public void alapotMovement() {
        this.enemyImg.posunVodorovne(-10) ;
        this.x -= 10;

        if (this.y < 449) {
            this.enemyImg.zmenUhol(180);
        } else {
            this.enemyImg.zmenUhol(0);
        }

        if (this.x <= this.bot.getX()) {
            if (this.y < 890 && this.movingDown) {
                this.enemyImg.posunZvisle(25);
                this.y += 25;
            } else {
                this.movingDown = false;
            }

            if (this.y >= 10 && !this.movingDown) {
                this.enemyImg.posunZvisle(-25);
                this.y -= 25;
            } else {
                this.movingDown = true;
            }
        }
    }


    public void zelnucMovement() {
        //fly from right to left and after reaching random x send diagonally to right and down or right and up

        if (-this.randomNumber / 50 <= 0) {
            this.enemyImg.posunVodorovne(-5 ) ;
            this.x -= 5;
        } else {
            this.enemyImg.posunVodorovne(-this.randomNumber / 50 ) ;
            this.x -= this.randomNumber / 50;
        }

        if (this.x <= 1000) {
            if (this.y < 700 && this.movingDown) {
                this.enemyImg.posunZvisle(15);
                this.y += 15;
                //rotate image
                this.enemyImg.zmenUhol(-45);

            } else {
                this.movingDown = false;
            }

            if (this.y >= 100 && !this.movingDown) {
                this.enemyImg.posunZvisle(-15);
                this.y -= 15;
                //rotate image
                this.enemyImg.zmenUhol(45);

            } else {
                this.movingDown = true;
            }
        }
    }

    public void gelpaarMovement() {
        int centerX = this.x;
        int centerY = this.y;

        int radius = 100;

        double angularSpeed = 0.02;

        this.enemyImg.posunVodorovne(-5);
        this.x -= 5;


        double angleCircle = this.x * angularSpeed;
        int newX = ( int )(centerX + radius * Math.cos(angleCircle));
        int newY = ( int )(centerY + radius * Math.sin(angleCircle));

        this.enemyImg.zmenPolohu(newX, newY);
    }

    public void gealmeaMovement() {
        //fly from right to left
        this.enemyImg.posunVodorovne(-10) ;
        this.x -= 10;
    }

    public void boasiteMovement() {
        //fly from right to left
        this.enemyImg.posunVodorovne(-10) ;
        this.x -= 10;
        if (!this.isDead) {
            if (this.timer == 0 && this.x > 0 ) {
                this.bulletManager.shootDown(this.x - this.imageWidth / 2, this.y);
                this.timer = 50;
            } else {
                this.timer--;
            }
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

    public EnemyType getType() {
        return this.type;
    }

    public void setX(int x) {
        this.x = x;
        this.enemyImg.zmenPolohu(this.x, this.y);
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return this.health;
    }

    public BulletManager getBulletManager() {
        return this.bulletManager;
    }

    public void setIsDead(boolean isDead) {
        this.isDead = isDead;
    }

    public boolean getHasReachedBot() {
        return this.hasReachedBot;
    }
}
