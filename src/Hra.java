import fri.shapesge.Manazer;
/**
 * Write a description of class Hra here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Hra {
    private Manazer manazer;
    private TransBot bot;
    private EnemySpawner enemySpawner;
    private Mapa mapa;
    private Mapa mapa2;

    private CollisionDetectSystem collisionDetectSystem;

    private Score score;

    private GameInfo gameInfo;


    
    public Hra() {
        this.manazer = new Manazer();
        this.bot = new TransBot(20, 480);
        this.mapa = new Mapa(0, 800);
        this.mapa2 = new Mapa(1620 , 800);
        this.score = new Score();
        this.gameInfo = new GameInfo(this.score, this.bot.getHealth());
        this.enemySpawner = new EnemySpawner(this.manazer, this.bot);
        this.collisionDetectSystem = new CollisionDetectSystem(this.enemySpawner.getEnemies(), this.bot.getBulletManager().getBullets(), this.bot, this.score);
        this.manazer.spravujObjekt(this);
        this.manazer.spravujObjekt(this.mapa);
        this.manazer.spravujObjekt(this.mapa2);
        this.manazer.spravujObjekt(this.bot);
        this.manazer.spravujObjekt(this.collisionDetectSystem);
        this.manazer.spravujObjekt(this.enemySpawner);
        this.manazer.spravujObjekt(this.gameInfo);
    }



    public void endGame() {
        System.exit(0);
    }



    
}
