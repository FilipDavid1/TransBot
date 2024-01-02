import java.util.ArrayList;
import java.util.Random;
import fri.shapesge.Manazer;

public class EnemySpawner {
    private ArrayList<Enemy> enemies;
    private Manazer manazer;

    private int time;

    private TransBot bot;

    public EnemySpawner(Manazer manazer, TransBot bot) {
        this.enemies = new ArrayList<>();
        this.manazer = manazer;
        this.time = 80;
        this.bot = bot;
    }


    private void spawnPsyball(int numberOfEnemies, EnemyType enemyType, int x) {
        int initialX = x;
        int initialY = 250;

        for (int i = 0; i < numberOfEnemies; i++) {
            Enemy enemy = new Enemy(initialX, initialY, enemyType, this.bot);
            enemies.add(enemy);
            manazer.spravujObjekt(enemy);



            if (x == 1400) {
                initialX += 100;
            } else {
                initialX -= 100;
            }

            initialY -= 10;
        }
    }

    private void spawnGealmea() {
        //spawn 6 gealmeas in a row with 100px distance
        int initialX = 1400;
        int initialY = 10;

        for (int i = 0; i < 8; i++) {
            Enemy enemy = new Enemy(initialX, initialY, EnemyType.GEALMEA, this.bot);
            enemies.add(enemy);
            manazer.spravujObjekt(enemy);

            initialY += 100;
        }
    }

    //spawn one enemy at random y and 1400 x
    private void spawnEnemy() {
        Random random = new Random();
        int randomNumber = random.nextInt(850);
        //get random enemy type and if it is psyball, spawn more than one
        EnemyType enemyType = EnemyType.getRandomEnemyType();
        if (enemyType == EnemyType.PSYBALL) {
            this.spawnPsyball(5, enemyType, 1400);
        } else if (enemyType == EnemyType.ASCULE) {
            this.spawnPsyball(5, enemyType, 0);
        } else if (enemyType == EnemyType.GEALMEA) {
            this.spawnGealmea();
        } else {
            Enemy enemy = new Enemy(1400, randomNumber, enemyType, this.bot);
            enemies.add(enemy);
            manazer.spravujObjekt(enemy);
        }
    }




    public ArrayList<Enemy> getEnemies() {
        return this.enemies;
    }




    public void tik() {
        if (this.time == 0) {
            //spawn random number of enemies
            Random random = new Random();
            int randomNumber = random.nextInt(10) + 1;
           // spawnEnemies(randomNumber);
            this.spawnEnemy();
            this.time = 80;
        } else {
            this.time--;
        }
        this.deleteEnemyAfter();
    }

    private void deleteEnemyAfter() {
        ArrayList<Enemy> enemiesToDelete = new ArrayList<>();
        for (Enemy enemy : this.enemies) {
            if (enemy.getType() == EnemyType.ASCULE) {
                if (enemy.getX() <= 0 && enemy.getHasReachedBot()) {
                    enemy.setIsDead(true);
                    enemiesToDelete.add(enemy);
                    enemy.skryObrazok();
                }

            } else if (enemy.getType() == EnemyType.PSYBALL) {
                if (enemy.getX() >= 1400  && enemy.getHasReachedBot()) {
                    enemy.setIsDead(true);
                    enemiesToDelete.add(enemy);
                    enemy.skryObrazok();
                }
            } else {
                if (enemy.getX() <= 0) {
                    enemy.setIsDead(true);
                    enemiesToDelete.add(enemy);
                    enemy.skryObrazok();
                }
            }

        }

        this.enemies.removeAll(enemiesToDelete);
    }

    //spawn enemies with movement from right to left

}
