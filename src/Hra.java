import fri.shapesge.Manazer;
/**
 * Write a description of class Hra here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Hra {
    private final TransBot bot;
    private final EnemySpawner enemySpawner;
    private final Mapa mapa;
    private final Mapa mapa2;

    private final CollisionDetectSystem collisionDetectSystem;

    private Score score;

    private final GameInfo gameInfo;


    
    public Hra() {
        Manazer manazer = new Manazer();
        manazer = new Manazer();
        this.bot = new TransBot(20, 480);
        this.mapa = new Mapa(0, 800);
        this.mapa2 = new Mapa(1620 , 800);
        this.score = new Score();
        this.gameInfo = new GameInfo(this.score, this.bot.getHealth());
        this.enemySpawner = new EnemySpawner(manazer, this.bot, this.score);
        this.collisionDetectSystem = new CollisionDetectSystem(this.enemySpawner.getEnemies(), this.bot.getBulletManager().getBullets(), this.bot, this.score);
        manazer.spravujObjekt(this);
        manazer.spravujObjekt(this.mapa);
        manazer.spravujObjekt(this.mapa2);
        manazer.spravujObjekt(this.bot);
        manazer.spravujObjekt(this.collisionDetectSystem);
        manazer.spravujObjekt(this.enemySpawner);
        manazer.spravujObjekt(this.gameInfo);
    }



    public void endGame() {
        System.exit(0);
    }



    
}
