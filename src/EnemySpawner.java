import java.util.ArrayList;
import java.util.Random;
import fri.shapesge.Manazer;

public class EnemySpawner {
    private final ArrayList<Enemy> enemies;
    private final Manazer manazer;

    private int time;

    private final TransBot bot;

    private Score score;


    public EnemySpawner(Manazer manazer, TransBot bot, Score score) {
        this.enemies = new ArrayList<>();
        this.manazer = manazer;
        this.time = 80;
        this.bot = bot;
        this.score = score;
    }


    private void spawnPsyball( EnemyType enemyType, int x) {
        int initialX = x;
        int initialY = 250;

        for (int i = 0; i < 5; i++) {
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

    private void spawnEnemiesByScore() {
        Random random = new Random();
        int randomNumber = random.nextInt(850);

        if (this.score.getScore() <= 2000) {
            this.spawnEnemy(EnemyType.PSYBALL, 1400, randomNumber);
        } else if (this.score.getScore() > 2000 && this.score.getScore() <= 3000) {
            this.spawnEnemy(EnemyType.ASCULE, 0, randomNumber);
        } else if (this.score.getScore() > 3000 && this.score.getScore() <= 5000) {
            this.spawnPsyball( EnemyType.PSYBALL, 1400);
            this.spawnPsyball( EnemyType.ASCULE, 0);
        } else if (this.score.getScore() > 5000 && this.score.getScore() <= 7000) {
            this.spawnEnemy(EnemyType.ELBLINK, 1400, randomNumber);
        } else if (this.score.getScore() > 7000 && this.score.getScore() <= 9000) {
            this.spawnEnemy(EnemyType.LUVOGUE, 1400, randomNumber);
            this.spawnEnemy(EnemyType.ZELNUC, 1700, randomNumber );
        } else if (this.score.getScore() > 9000 && this.score.getScore() <= 11000) {
            this.spawnGealmea();
            this.spawnEnemy(EnemyType.GELPAAR, 1400, randomNumber + 100);
        } else if (this.score.getScore() > 11000 && this.score.getScore() <= 13000) {
            this.spawnEnemy(EnemyType.BIFLER, 1400, randomNumber);
            this.spawnEnemy(EnemyType.BIFLER, 1400, randomNumber + 100);
            this.spawnEnemy(EnemyType.BIFLER, 1400, randomNumber + 200);
            this.spawnEnemy(EnemyType.ELBLINK, 1800, randomNumber);
            this.spawnGealmea();
        } else if (this.score.getScore() > 13000 && this.score.getScore() <= 15000) {
            this.spawnEnemy(EnemyType.HILUN, 1400, randomNumber);
            this.spawnPsyball( EnemyType.ASCULE, 0);
        } else if (this.score.getScore() > 15000 && this.score.getScore() <= 17000) {
            this.spawnEnemy(EnemyType.ALAPOT, 1600, randomNumber);
            this.spawnEnemy(EnemyType.HILUN, 1400, randomNumber + 100);
        } else if (this.score.getScore() > 1000 && this.score.getScore() <= 19000) {
            this.spawnEnemy(EnemyType.BOASITE, 1400, randomNumber);
            this.spawnPsyball( EnemyType.PSYBALL, 1400);
        } else {
            //get random enemy type and if it is psyball, spawn more than one
            EnemyType enemyType = EnemyType.getRandomEnemyType();
            if (enemyType == EnemyType.PSYBALL) {
                this.spawnPsyball( enemyType, 1400);
            } else if (enemyType == EnemyType.ASCULE) {
                this.spawnPsyball( enemyType, 0);
            } else if (enemyType == EnemyType.GEALMEA) {
                this.spawnGealmea();
            } else {
                this.spawnEnemy(enemyType, 1400, randomNumber);
            }
        }

    }



    private void spawnEnemy(EnemyType enemyType, int x, int y) {
        Enemy enemy = new Enemy(x, y, enemyType, this.bot);
        enemies.add(enemy);
        manazer.spravujObjekt(enemy);
    }

    public ArrayList<Enemy> getEnemies() {
        return this.enemies;
    }




    public void tik() {
        if (this.time == 0) {
            this.spawnEnemiesByScore();
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
}
