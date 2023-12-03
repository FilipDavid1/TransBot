import fri.shapesge.Obrazok;
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

    private boolean movingDown;
    private boolean movingRight;
    
    public Enemy(int x, int y) {
        this.enemyImg = new Obrazok("/Users/filipdavid/Documents/INF/TransBotInteliJ/src/pics/enemy/psyball.png");
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

    public boolean kolizia(Naboj naboj) {
        return (naboj.getX() >= this.x && naboj.getY() <= this.x + 83 && naboj.getY() >= this.y && naboj.getY() <= this.y + 87);
    }

    public void skryObrazok() {
        this.enemyImg.skry();
    }

}
