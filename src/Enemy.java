import fri.shapesge.DataObrazku;
import fri.shapesge.Obrazok;

import java.util.Random;

/**
 * Trieda Enemy vytvára nepriateľa, ktorý sa pohybuje podľa typu.
 * Niektoré nepriateľovia strieľajú a majú viac životov.
 *
 * @author Filip Dávid
 *
 * @version 1.0
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

    /**
     * Konštruktor vytvorí nepriateľa na súradniciach x a y a nastaví mu typ, taktiež nastaví ostatné atribúty.
     * @param x
     * @param y
     * @param type
     */
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




    /**
     * Metóda vykonáva pohyb nepriateľa podľa typu.
     */
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

    /**
     * Metóda vykonáva pohyb nepriateľa typu psyball.
     * Pohyb je vykonávaný pomocou cosinusovej funkcie a letí zprava doľava, ak dosiahne určitú x-ovú súradnicu tak letí zľava doprava.
     */
    private void psyballMovement() {
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
        this.enemyImg.zmenUhol(angle);
        angle += 10;

        this.enemyImg.posunZvisle((int)(Math.cos(this.x * 0.005) * 11));
        this.y += (int)(Math.cos(this.x * 0.005) * 11);


    }

    /**
     * Metóda vykonáva pohyb nepriateľa typu elblink.
     * Elblink sa pohybuje zprava doľava a po dosiahnutí x-ovej súradnice robota sa pohybuje nahor alebo nadol.
     */
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

    /**
     * Metóda vykonáva pohyb nepriateľa typu luvoque.
     * Luvoque sa pohybuje zprava doľava a rotuje sa.
     */
    private void luvoqueMovement() {
        this.enemyImg.zmenUhol(angle);
        angle += 10;
        this.enemyImg.posunZvisle(-2);
        this.enemyImg.posunVodorovne(-7);
        this.x -= 7;
        this.y -= 2;
    }

    /**
     * Metóda vykonáva pohyb nepriateľa typu bifler.
     * Bifler sa pohybuje zprava doľava, jeho rýchlosť je náhodná.
     */
    private void biflerMovement() {
        if (this.randomNumber / 30 <= 5) {
            this.enemyImg.posunVodorovne(-10 ) ;
            this.x -= 10;
        } else {
            this.enemyImg.posunVodorovne(-this.randomNumber / 30 ) ;
            this.x -= this.randomNumber / 30;
        }

    }

    /**
     * Metóda vykonáva pohyb nepriateľa typu hilun.
     * Hilun sa pohybuje zprava doľava a strieľa do všetkých strán.
     */
    private void hilunMovement() {
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

    /**
     * Metóda vykonáva pohyb nepriateľa typu alapot.
     * Alapot sa pohybuje zprava doľava a po dosiahnutí x-ovej súradnice robota sa pohybuje nahor alebo nadol.
     */
    private void alapotMovement() {
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

    /**
     * Metóda vykonáva pohyb nepriateľa typu zelnuc.
     * Zelnuc sa pohybuje zprava doľava a po dosiahnutí určitej x-ovej súradnice sa pohybuje šikmo nahor alebo nadol.
     */
    private void zelnucMovement() {
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

    /**
     * Metóda vykonáva pohyb nepriateľa typu gelpaar.
     * Gelpaar sa pohybuje po kružnici.
     */
    private void gelpaarMovement() {
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

    /**
     * Metóda vykonáva pohyb nepriateľa typu gealmea.
     * Gealmea sa pohybuje zprava doľava.
     */
    private void gealmeaMovement() {
        this.enemyImg.posunVodorovne(-10) ;
        this.x -= 10;
    }

    /**
     * Metóda vykonáva pohyb nepriateľa typu boasite.
     * Boasite sa pohybuje zprava doľava a strieľa dole.
     */
    private void boasiteMovement() {
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

    /**
     * Metóda vráti šírku obrázku nepriateľa / 2.
     * @return imageWidth / 2
     */
    public int getImageWidth() {
        return imageWidth / 2;
    }

    /**
     * Metóda vráti výšku obrázku nepriateľa / 2.
     * @return imageHeight / 2
     */
    public int getImageHeight() {
        return imageHeight / 2;
    }

    /**
     * Metóda vráti x-ovú súradnicu nepriateľa.
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * Metóda vráti y-ovú súradnicu nepriateľa.
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * Metóda skryje obrázok nepriateľa.
     */
    public void skryObrazok() {
        this.enemyImg.skry();
    }

    /**
     * Metóda vráti typ nepriateľa.
     * @return type
     */
    public EnemyType getType() {
        return this.type;
    }

    /**
     * Metóda nastaví x-ovú súradnicu nepriateľa.
     */
    public void setX(int x) {
        this.x = x;
        this.enemyImg.zmenPolohu(this.x, this.y);
    }

    /**
     * Metóda nastaví životy nepriateľa.
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Metóda vráti životy nepriateľa.
     * @return health
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Metóda vráti bulletManager nepriateľa.
     * @return bulletManager
     */
    public BulletManager getBulletManager() {
        return this.bulletManager;
    }

    /**
     * Metóda nastaí isDead.
     * @param isDead
     */
    public void setIsDead(boolean isDead) {
        this.isDead = isDead;
    }

    /**
     * Metóda vráti hasReachedBot.
     * @return hasReachedBot
     */
    public boolean getHasReachedBot() {
        return this.hasReachedBot;
    }
}
