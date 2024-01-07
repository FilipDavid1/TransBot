import java.util.ArrayList;
import java.util.Random;
import fri.shapesge.Manazer;

/**
 * Trieda EnemySpawner spawnuje nepriateľov podľa skóre.
 * Zároveň zmaže nepriateľov, ktorí prešli za herné pole.
 *
 * @author Filip Dávid
 *
 * @version 1.0
 */

public class EnemySpawner {
    private final ArrayList<Enemy> enemies;
    private final Manazer manazer;

    private int time;

    private final TransBot bot;

    private final Score score;


    /**
     * Konštruktor nastaví enemies na prázdny ArrayList, time na 80 a nastaví ostatné atribúty na vstupné parametre.
     * @param manazer spravuje objekt EnemySpawner
     * @param bot TransBot
     * @param score skóre
     */
    public EnemySpawner(Manazer manazer, TransBot bot, Score score) {
        this.enemies = new ArrayList<>();
        this.manazer = manazer;
        this.time = 80;
        this.bot = bot;
        this.score = score;
    }


    /**
     * Metóda spawnuje nepriateľov typu Psyball alebo Ascule tak, že spawnuje 5 nepriateľov v rade s 100px rozostupom.
     * @param enemyType typ nepriateľa
     * @param x súradnica x
     */
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

    /**
     * Metóda spawnuje nepriateľov typu Gealmea tak, že spawnuje 6 nepriateľov v rade s 100px rozostupom na Y.
     */
    private void spawnGealmea() {
        int initialX = 1400;
        int initialY = 10;

        for (int i = 0; i < 8; i++) {
            Enemy enemy = new Enemy(initialX, initialY, EnemyType.GEALMEA, this.bot);
            enemies.add(enemy);
            manazer.spravujObjekt(enemy);

            initialY += 100;
        }
    }

    /**
     * Metóda spawnuje nepriateľov podľa skóre.
     */
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



    /**
     * Metóda spawnuje nepriateľa na zadaných súradniciach.
     * @param enemyType typ nepriateľa
     * @param x súradnica x
     * @param y súradnica y
     */
    private void spawnEnemy(EnemyType enemyType, int x, int y) {
        Enemy enemy = new Enemy(x, y, enemyType, this.bot);
        enemies.add(enemy);
        manazer.spravujObjekt(enemy);
    }

    /**
     * Metóda vráti ArrayList nepriateľov.
     * @return enemies
     */
    public ArrayList<Enemy> getEnemies() {
        return this.enemies;
    }




    /**
     * Metóda zavolá metódu spawnEnemiesByScore ak je time 0, inak time zníži o 1.
     * Metóda zavolá metódu deleteEnemyAfter.
     */
    public void tik() {
        if (this.time == 0) {
            this.spawnEnemiesByScore();
            this.time = 80;
        } else {
            this.time--;
        }
        this.deleteEnemyAfter();
    }

    /**
     * Metóda zmaže nepriateľov, ktorí prešli za herné pole.
     */
    private void deleteEnemyAfter() {
        ArrayList<Enemy> enemiesToDelete = new ArrayList<>();
        for (Enemy enemy : this.enemies) {
            if (enemy.getType() == EnemyType.ASCULE) {
                if (enemy.getX() <= 0 && enemy.getHasReachedBot()) {
                    enemy.setIsDead(true);
                    enemiesToDelete.add(enemy);
                    enemy.hideImage();
                }

            } else if (enemy.getType() == EnemyType.PSYBALL) {
                if (enemy.getX() >= 1400  && enemy.getHasReachedBot()) {
                    enemy.setIsDead(true);
                    enemiesToDelete.add(enemy);
                    enemy.hideImage();
                }
            } else {
                if (enemy.getX() <= 0) {
                    enemy.setIsDead(true);
                    enemiesToDelete.add(enemy);
                    enemy.hideImage();
                }
            }

        }

        this.enemies.removeAll(enemiesToDelete);
    }
}
