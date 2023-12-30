import java.util.ArrayList;

public class CollisionDetectSystem {

    private ArrayList<Enemy> enemies;
    private ArrayList<Bullet> bullets;

    private TransBot transBot;

    public CollisionDetectSystem(ArrayList<Enemy> enemies, ArrayList<Bullet> bullets, TransBot transBot) {
        this.enemies = enemies;
        this.bullets = bullets;
        this.transBot = transBot;
    }

    private void detectAndRemoveCollision() {
        ArrayList<Enemy> enemiesToRemove = new ArrayList<>();
        ArrayList<Bullet> bulletsToRemove = new ArrayList<>();
        for (Enemy enemy : this.enemies) {
            if (this.detectCollision(enemy, this.transBot)) {
                enemiesToRemove.add(enemy);
                enemy.skryObrazok();
                System.out.println("Kolizia");
            }
            for (Bullet bullet : this.bullets) {
                if (this.detectCollision(enemy, bullet)) {
                    //if enemy type is gealmea move her 50 px right
                    if (enemy.getType() == EnemyType.GEALMEA) {
                        //if enemy health is 1, remove her
                        if (enemy.getHealth() == 1) {
                            enemiesToRemove.add(enemy);
                            enemy.skryObrazok();
                            bulletsToRemove.add(bullet);
                            bullet.skryObrazok();
                        } else {
                            enemy.setHealth(enemy.getHealth() - 1);
                            enemy.setX(enemy.getX() + 100);
                            bulletsToRemove.add(bullet);
                            bullet.skryObrazok();
                        }

                    } else {
                        enemiesToRemove.add(enemy);
                        bulletsToRemove.add(bullet);
                        enemy.skryObrazok();
                        bullet.skryObrazok();
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
