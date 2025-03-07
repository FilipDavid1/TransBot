import java.util.ArrayList;

/**
 * Trieda CollisionDetectSystem detekuje kolíziu medzi nepriateľmi a TransBotom, medzi nepriateľmi a TransBotovými nábojmi a medzi TransBotom a nepriateľovými nábojmi.
 * Zároveň zisťuje či nepriateľ zomrel a ak áno, tak ho odstráni z ArrayListu enemies.
 * Zároveň zisťuje či náboj zasiahol nepriateľa a ak áno, tak ho odstráni z ArrayListu enemies.
 *
 * @author Filip Dávid
 *
 * @version 1.0
 */
public class CollisionDetectSystem {

    private final ArrayList<Enemy> enemies;
    private final ArrayList<Bullet> bullets;

    private final TransBot transBot;

    private final Score score;

    /**
     * Konštruktor nastaví vstupné parametre na atribúty.
     * @param enemies arraylist nepriateľov
     * @param bullets arraylist nábojov
     * @param transBot transbot
     * @param score skóre
     */
    public CollisionDetectSystem(ArrayList<Enemy> enemies, ArrayList<Bullet> bullets, TransBot transBot, Score score) {
        this.enemies = enemies;
        this.bullets = bullets;
        this.transBot = transBot;
        this.score = score;
    }

    /**
     * Metóda detekuje kolíziu medzi nepriateľmi a TransBotom, medzi nepriateľmi a TransBotovými nábojmi a medzi TransBotom a nepriateľovými nábojmi.
     */
    private void detectAndRemoveCollision() {
        ArrayList<Enemy> enemiesToRemove = new ArrayList<>();
        ArrayList<Bullet> bulletsToRemove = new ArrayList<>();
        for (Enemy enemy : this.enemies) {
            if (enemy.getType() == EnemyType.HILUN || enemy.getType() == EnemyType.BOASITE) {
                for (Bullet bullet : enemy.getBulletManager().getBullets()) {
                    if (this.detectCollision(transBot, bullet)) {
                        bulletsToRemove.add(bullet);
                        bullet.hideImage();
                        this.transBot.getHealth().decreaseHealth();
                    }
                }
            }
            if (this.detectCollision(enemy, this.transBot)) {
                enemiesToRemove.add(enemy);
                enemy.hideImage();
                this.transBot.getHealth().decreaseHealth();
            }
            for (Bullet bullet : this.bullets) {
                if (this.detectCollision(enemy, bullet)) {
                    if (enemy.getType() == EnemyType.GEALMEA && bullet.getBulletType() != BulletType.DIFFUSION_BEAM) {
                        if (enemy.getHealth() == 1) {
                            enemiesToRemove.add(enemy);
                            enemy.hideImage();
                            bulletsToRemove.add(bullet);
                            bullet.hideImage();
                            this.score.increaseScore();
                        } else {
                            enemy.setHealth(enemy.getHealth() - 1);
                            enemy.setX(enemy.getX() + 100);
                            bulletsToRemove.add(bullet);
                            bullet.hideImage();

                        }

                    } else if (bullet.getBulletType() == BulletType.DIFFUSION_BEAM) {
                        enemy.setIsDead(true);
                        enemiesToRemove.add(enemy);
                        enemy.hideImage();
                        this.score.increaseScore();
                    } else {
                        enemy.setIsDead(true);
                        enemiesToRemove.add(enemy);
                        bulletsToRemove.add(bullet);
                        bullet.hideImage();
                        enemy.hideImage();
                        this.score.increaseScore();
                    }
                }
            }
        }
        this.enemies.removeAll(enemiesToRemove);
        this.bullets.removeAll(bulletsToRemove);
    }

    /**
     * Metóda zavolá metódu detectAndRemoveCollision, ktorá detekuje kolízie.
     */
    public void tik() {
        this.detectAndRemoveCollision();
    }

    /**
     * Metóda detekuje kolíziu medzi nepriateľom a nábojom.
     * @param enemy nepriateľ
     * @param bullet náboj
     * @return true ak nastala kolízia, inak false
     */
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

    /**
     * Metóda detekuje kolíziu medzi TransBotom a nábojom.
     * @param bot transbot
     * @param bullet náboj
     * @return true ak nastala kolízia, inak false
     */
    private boolean detectCollision(TransBot bot, Bullet bullet) {
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

    /**
     * Metóda detekuje kolíziu medzi nepriateľom a TransBotom.
     * @param enemy nepriateľ
     * @param transBot transbot
     * @return true ak nastala kolízia, inak false
     */
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
