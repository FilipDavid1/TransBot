import java.util.ArrayList;
import fri.shapesge.Manazer;

public class EnemySpawner {
    private ArrayList<Enemy> enemies;
    private Manazer manazer;

    public EnemySpawner(Manazer manazer) {
        this.enemies = new ArrayList<>();
        this.manazer = manazer;
        spawnEnemies(5);
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
        System.out.println(this.enemies.size());
    }




    public ArrayList<Enemy> getNepriatelov() {
        //System.out.println(this.enemies.size());
        return this.enemies;
    }




    public void tik() {
        if (this.enemies.size() == 0) {
            spawnEnemies(5);
        }
    }


    //spawn enemies with movement from right to left

}
