import java.util.ArrayList;
import java.util.Random;

import fri.shapesge.Manazer;

public class EnemySpawner {
    private ArrayList<Enemy> enemies;
    private Manazer manazer;

    private int time;

    public EnemySpawner(Manazer manazer) {
        this.enemies = new ArrayList<>();
        this.manazer = manazer;
        this.time = 50;
    }


    private void spawnEnemies(int numberOfEnemies) {
        int initialX = 1000;
        int initialY = 100;

        for (int i = 0; i < numberOfEnemies; i++) {
            Enemy enemy = new Enemy(initialX, initialY, EnemyType.PSYBALL);
            enemies.add(enemy);
            manazer.spravujObjekt(enemy);


            initialX += 100;
            initialY += 50;
        }
    }

    //spawn one enemy at random y and 1400 x
    private void spawnEnemy() {
        Random random = new Random();
        int randomNumber = random.nextInt(900);
        Enemy enemy = new Enemy(1400, randomNumber, EnemyType.LUVOGUE);
        enemies.add(enemy);
        manazer.spravujObjekt(enemy);
    }




    public ArrayList<Enemy> getNepriatelov() {
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
    }


    //spawn enemies with movement from right to left

}
