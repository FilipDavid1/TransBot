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
        this.time = 50;
        this.bot = bot;
    }


    private void spawnPsyball(int numberOfEnemies) {
        int initialX = 1400;
        int initialY = 100;

        for (int i = 0; i < numberOfEnemies; i++) {
            Enemy enemy = new Enemy(initialX, initialY, EnemyType.PSYBALL, this.bot);
            enemies.add(enemy);
            manazer.spravujObjekt(enemy);


            initialX += 100;
            initialY += 50;
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
        EnemyType enemyType = EnemyType.GEALMEA;
        if (enemyType == EnemyType.PSYBALL || enemyType == EnemyType.ASCULE) {
            this.spawnPsyball(6);
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
            this.time = 50;
        } else {
            this.time--;
        }
        this.deleteEnemyAfterX();
    }

    private void deleteEnemyAfterX() {
        ArrayList<Enemy> enemiesToDelete = new ArrayList<>();
        for (Enemy enemy : this.enemies) {
            if (enemy.getX() <= 0) {
                enemiesToDelete.add(enemy);
                enemy.skryObrazok();
            }
        }

        this.enemies.removeAll(enemiesToDelete);
    }

    //spawn enemies with movement from right to left

}
