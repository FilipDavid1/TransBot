import fri.shapesge.Manazer;

/**
 * Trieda Game vytvára objekt hry, taktiež obsahuje metódy na spustenie, reštartovanie a ukončenie hry.
 * Trieda je implementovaná ako Singleton.
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
    private final GameMenu gameMenu;

    private boolean isRunning;

    /**
     * Privátny konštruktor nastaví premennú isRunning na false a vytvorí objekt GameMenu.
     */
    private Game() {
        initializeGameObjects();
        this.isRunning = false;
        this.gameMenu = new GameMenu();
    }

    /**
     * Vráti inštanciu triedy Game.
     * @return instance
     */
    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    /**
     * Spustí hru tým, že zobrazí objekty a skryje menu.
     */
    public void startGame() {
        if (!this.isRunning) {
            addObjectsToManager();
            this.gameMenu.hideMenu();
            this.isRunning = true;
        }
    }

    /**
     * Reštartuje hru tým, že zastaví bežiace procesy, vymaže objekty a reštartuje premenné.
     */
    public void restartGame() {
        stopProcessesAndClearObjects();
        initializeGameObjects();
        addObjectsToManager();
    }

    /**
     * Ukončí hru tým, že ukončí program.
     */
    public void endGame() {
        System.exit(0);
    }

    /**
     * Metóda inicializuje herné objekty.
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
     * Metóda pridá herné objekty do manazéra.
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
     * Metóda zastaví bežiace procesy a vymaže herné objekty.
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
