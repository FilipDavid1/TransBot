import fri.shapesge.Manazer;


/**
 * Trieda Game je singleton, ktorý sa stará o vytvorenie a správu herných objektov.
 * Vytvára herné objekty a pridáva ich do manazéra.
 *
 * @author Filip Dávid
 *
 * @version 1.0
 */
public class Game {
    private static Game instance;

    private TransBot bot;
    private EnemySpawner enemySpawner;
    private Map map;
    private Map map2;
    private CollisionDetectSystem collisionDetectSystem;
    private Score score;
    private GameInfo gameInfo;
    private Manazer manazer;
    private boolean isRunning;
    private GameMenu gameMenu;

    /**
     * Súkromný konštruktor nastaví všetky herné objekty pomocou metódy initializeGameObjects().
     */
    private Game() {
        initializeGameObjects();
    }

    /**
     * Metóda vráti inštanciu triedy Game.
     */
    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    /**
     * Metóda inicializuje herné objekty.
     */
    private void initializeGameObjects() {
        this.manazer = new Manazer();
        manazer.spravujObjekt(this);


        this.map = new Map(0, 720);
        this.map2 = new Map(1620, 720);
        this.bot = new TransBot(20, 480);
        this.score = new Score();
        this.gameInfo = new GameInfo(this.score, this.bot.getHealth(), this.bot);
        this.enemySpawner = new EnemySpawner(manazer, this.bot, this.score);
        this.collisionDetectSystem = new CollisionDetectSystem(
                this.enemySpawner.getEnemies(),
                this.bot.getBulletManager().getBullets(),
                this.bot,
                this.score
        );

        this.isRunning = false;
        this.gameMenu = new GameMenu();
    }



    /**
     * Metóda zapne hru ak ešte nebola spustená.
     */
    public void startGame() {
        if (!this.isRunning) {
            addGameObjectsToManager();
            hideStartScreen();
            this.isRunning = true;
        }
    }

    /**
     * Metóda pridá herné objekty do manazéra.
     */
    private void addGameObjectsToManager() {
        manazer.spravujObjekt(this.map);
        manazer.spravujObjekt(this.map2);
        manazer.spravujObjekt(this.bot);
        manazer.spravujObjekt(this.collisionDetectSystem);
        manazer.spravujObjekt(this.enemySpawner);
        manazer.spravujObjekt(this.gameInfo);
    }

    /**
     * Metóda skryje štartovací screen.
     */
    private void hideStartScreen() {
        this.gameMenu.hideMenu();
    }

    /**
     * Metóda reštartuje hru.
     */
    public void restartGame() {
        stopRunningProcesses();
        clearGameObjects();
        reinitializeGameObjects();
        addGameObjectsToManager();
        this.isRunning = false;
    }

    /**
     * Metóda zastaví všetky procesy tým, že prestane spravovať herné objekty a skryje ich.
     */
    private void stopRunningProcesses() {
        manazer.prestanSpravovatObjekt(this.map);
        manazer.prestanSpravovatObjekt(this.map2);
        manazer.prestanSpravovatObjekt(this.bot);
        manazer.prestanSpravovatObjekt(this.collisionDetectSystem);
        manazer.prestanSpravovatObjekt(this.enemySpawner);
        manazer.prestanSpravovatObjekt(this.gameInfo);
        this.bot.hideImage();
        this.gameInfo.hideInfo();
    }

    /**
     * Metóda vymaže herné objekty z pamäte.
     */
    private void clearGameObjects() {
        this.bot = null;
        this.map = null;
        this.map2 = null;
        this.score = null;
        this.gameInfo = null;
        this.enemySpawner = null;
        this.collisionDetectSystem = null;
    }

    /**
     * Metóda reštartuje herné objekty.
     */
    private void reinitializeGameObjects() {
        initializeGameObjects();
    }

    /**
     * Metóda ukončí hru.
     */
    public void endGame() {
        System.exit(0);
    }
}
