import fri.shapesge.Manazer;
/**
 * Trieda Hra vytvára objekty hry a pridáva ich do manazera.
 * Zároveň ukončuje hru.
 *
 * @author Filip Dávid
 * @version 1.0
 */
public class Game {
    private final TransBot bot;
    private final EnemySpawner enemySpawner;
    private final Map map;
    private final Map map2;

    private final CollisionDetectSystem collisionDetectSystem;

    private Score score;

    private final GameInfo gameInfo;


    /**
     * Konštruktor vytvorí objekty hry a pridá ich do manazera.
     */
    public Game() {
        Manazer manazer = new Manazer();
        manazer = new Manazer();
        this.bot = new TransBot(20, 480);
        this.map = new Map(0, 800);
        this.map2 = new Map(1620 , 800);
        this.score = new Score();
        this.gameInfo = new GameInfo(this.score, this.bot.getHealth(), this.bot);
        this.enemySpawner = new EnemySpawner(manazer, this.bot, this.score);
        this.collisionDetectSystem = new CollisionDetectSystem(this.enemySpawner.getEnemies(), this.bot.getBulletManager().getBullets(), this.bot, this.score);
        manazer.spravujObjekt(this);
        manazer.spravujObjekt(this.map);
        manazer.spravujObjekt(this.map2);
        manazer.spravujObjekt(this.bot);
        manazer.spravujObjekt(this.collisionDetectSystem);
        manazer.spravujObjekt(this.enemySpawner);
        manazer.spravujObjekt(this.gameInfo);
    }


    /**
     * Metóda ukončí hru.
     */
    public void endGame() {
        System.exit(0);
    }



    
}
