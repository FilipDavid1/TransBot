import java.util.ArrayList;

public class CollisionDetectSystem {

    private ArrayList<Enemy> enemies;
    private ArrayList<Bullet> bullets;

    private TransBot transBot;

    private Score score;

    public CollisionDetectSystem(ArrayList<Enemy> enemies, ArrayList<Bullet> bullets, TransBot transBot, Score score) {
        this.enemies = enemies;
        this.bullets = bullets;
        this.transBot = transBot;
        this.score = score;
    }

    private void detectAndRemoveCollision() {
        ArrayList<Enemy> enemiesToRemove = new ArrayList<>();
        ArrayList<Bullet> bulletsToRemove = new ArrayList<>();
        for (Enemy enemy : this.enemies) {
            if (enemy.getType() == EnemyType.HILUN || enemy.getType() == EnemyType.BOASITE) {
                for (Bullet bullet : enemy.getBulletManager().getBullets()) {
                    if (this.detectCollision(transBot, bullet)) {
                        bulletsToRemove.add(bullet);
                        bullet.skryObrazok();
                        System.out.println("HIT");
                        this.transBot.getHealth().decreaseHealth();
                    }
                }
            }
            if (this.detectCollision(enemy, this.transBot)) {
                enemiesToRemove.add(enemy);
                enemy.skryObrazok();
                this.transBot.getHealth().decreaseHealth();
            }
            for (Bullet bullet : this.bullets) {
                if (this.detectCollision(enemy, bullet)) {
                    //if enemy type is gealmea move her 50 px right
                    if (enemy.getType() == EnemyType.GEALMEA && bullet.getBulletType() != BulletType.DIFFUSION_BEAM) {
                        //if enemy health is 1, remove her
                        if (enemy.getHealth() == 1) {
                            enemiesToRemove.add(enemy);
                            enemy.skryObrazok();
                            bulletsToRemove.add(bullet);
                            bullet.skryObrazok();
                            this.score.increaseScore();
                        } else {
                            enemy.setHealth(enemy.getHealth() - 1);
                            enemy.setX(enemy.getX() + 100);
                            bulletsToRemove.add(bullet);
                            bullet.skryObrazok();

                        }

                    } else if (bullet.getBulletType() == BulletType.DIFFUSION_BEAM) {
                        enemy.setIsDead(true);
                        enemiesToRemove.add(enemy);
                        enemy.skryObrazok();
                        this.score.increaseScore();
                    } else {
                        enemy.setIsDead(true);
                        enemiesToRemove.add(enemy);
                        bulletsToRemove.add(bullet);
                        bullet.skryObrazok();
                        enemy.skryObrazok();
                        this.score.increaseScore();
                    }
                }
            }
        }
        this.enemies.removeAll(enemiesToRemove);
        this.bullets.removeAll(bulletsToRemove);
    }

    public void tik() {
        this.detectAndRemoveCollision();
    }

    private boolean detectCollision(Enemy enemy, Bullet bullet) {
        int enemyLeft = enemy.getX();
        int enemyRight = enemy.getX() + enemy.getImageWidth();
        int enemyTop = enemy.getY();
        int enemyBottom = enemy.getY() + enemy.getImageHeight();

        int bulletLeft = bullet.getX();
        int bulletRight = bullet.getX() + bullet.getImageWidth();
        int bulletTop = bullet.getY();
        int bulletBottom = bullet.getY() + bullet.getImageHeight();

        return (enemyLeft - 10) < bulletRight &&
                (enemyRight + 10) > bulletLeft &&
                (enemyTop - 10) < bulletBottom &&
                (enemyBottom + 10) > bulletTop;
    }

    private boolean detectCollision(TransBot bot, Bullet bullet) {
        //detect collision between transbot and Hilun bullets - it shoots 4 bullets at once
        int botLeft = bot.getX();
        int botRight = bot.getX() + bot.getImageWidth();
        int botTop = bot.getY();
        int botBottom = bot.getY() + bot.getImageHeight();

        int bulletLeft = bullet.getX();
        int bulletRight = bullet.getX() + bullet.getImageWidth();
        int bulletTop = bullet.getY();
        int bulletBottom = bullet.getY() + bullet.getImageHeight();

        return (botLeft - 10) < bulletRight &&
                (botRight + 10) > bulletLeft &&
                (botTop - 10) < bulletBottom &&
                (botBottom + 10) > bulletTop;
    }

    private boolean detectCollision(Enemy enemy, TransBot transBot) {
        int enemyLeft = enemy.getX();
        int enemyRight = enemy.getX() + enemy.getImageWidth();
        int enemyTop = enemy.getY();
        int enemyBottom = enemy.getY() + enemy.getImageHeight();

        int transBotLeft = transBot.getX();
        int transBotRight = transBot.getX() + transBot.getImageWidth();
        int transBotTop = transBot.getY();
        int transBotBottom = transBot.getY() + transBot.getImageHeight();

        return (enemyLeft - 10) < transBotRight &&
                (enemyRight + 10) > transBotLeft &&
                (enemyTop - 10) < transBotBottom &&
                (enemyBottom + 10) > transBotTop;

    }
}
