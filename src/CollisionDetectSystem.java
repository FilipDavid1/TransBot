import java.util.ArrayList;

public class CollisionDetectSystem {

    private ArrayList<Enemy> enemies;
    private ArrayList<Bullet> bullets;

    public CollisionDetectSystem(ArrayList<Enemy> enemies, ArrayList<Bullet> bullets) {
        this.enemies = enemies;
        this.bullets = bullets;
    }

    private void detectAndRemoveCollision() {
        ArrayList<Enemy> enemiesToRemove = new ArrayList<>();
        ArrayList<Bullet> bulletsToRemove = new ArrayList<>();
        for (Enemy enemy : this.enemies) {
            for (Bullet bullet : this.bullets) {
                if (this.detectCollision(enemy, bullet)) {
                    enemiesToRemove.add(enemy);
                    bulletsToRemove.add(bullet);
                    enemy.skryObrazok();
                    bullet.skryObrazok();
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
}
