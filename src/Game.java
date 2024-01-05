import fri.shapesge.Manazer;

/**
 * The Game class creates game objects and adds them to the manager. It also handles game termination.
 *
 * @author Filip Dávid
 * @version 1.0
 */
public class Game {

    private static Game instance = null;

    private Manazer manazer;
    private TransBot bot;
    private EnemySpawner enemySpawner;
    private Map map;
    private Map map2;
    private CollisionDetectSystem collisionDetectSystem;
    private Score score;
    private GameInfo gameInfo;
    private GameMenu gameMenu;

    private boolean isRunning;

    /**
     * Private constructor to initialize game objects and add them to the manager.
     */
    private Game() {
        initializeGameObjects();
        this.isRunning = false;
        this.gameMenu = new GameMenu();
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    /**
     * Starts the game by adding objects to the manager and hiding the menu.
     */
    public void startGame() {
        if (!this.isRunning) {
            addObjectsToManager();
            this.gameMenu.hideMenu();
            this.isRunning = true;
        }
    }

    /**
     * Restarts the game by stopping processes, clearing objects, and reinitializing.
     */
    public void restartGame() {
        stopProcessesAndClearObjects();
        initializeGameObjects();
        addObjectsToManager();
    }

    /**
     * Ends the game by exiting the program.
     */
    public void endGame() {
        System.exit(0);
    }

    /**
     * Initializes game objects.
     */
    private void initializeGameObjects() {
        this.manazer = new Manazer();
        manazer.spravujObjekt(this);
        this.bot = new TransBot(20, 480);
        this.map = new Map(0, 800);
        this.map2 = new Map(1620, 800);
        this.score = new Score();
        this.gameInfo = new GameInfo(this.score, this.bot.getHealth(), this.bot);
        this.enemySpawner = new EnemySpawner(manazer, this.bot, this.score);
        this.collisionDetectSystem = new CollisionDetectSystem(
                this.enemySpawner.getEnemies(),
                this.bot.getBulletManager().getBullets(),
                this.bot,
                this.score
        );
    }

    /**
     * Adds game objects to the manager.
     */
    private void addObjectsToManager() {
        manazer.spravujObjekt(this.map);
        manazer.spravujObjekt(this.map2);
        manazer.spravujObjekt(this.bot);
        manazer.spravujObjekt(this.collisionDetectSystem);
        manazer.spravujObjekt(this.enemySpawner);
        manazer.spravujObjekt(this.gameInfo);
    }

    /**
     * Stops running processes, clears game objects, and resets variables.
     */
    private void stopProcessesAndClearObjects() {
        manazer.prestanSpravovatObjekt(this.map);
        manazer.prestanSpravovatObjekt(this.map2);
        manazer.prestanSpravovatObjekt(this.bot);
        manazer.prestanSpravovatObjekt(this.collisionDetectSystem);
        manazer.prestanSpravovatObjekt(this.enemySpawner);
        manazer.prestanSpravovatObjekt(this.gameInfo);
        this.bot.hideImage();
        this.gameInfo.hideInfo();
        this.bot = null;
        this.map = null;
        this.map2 = null;
        this.score = null;
        this.gameInfo = null;
        this.enemySpawner = null;
        this.collisionDetectSystem = null;
    }
}
