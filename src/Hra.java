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


    
    public Hra() {
        this.manazer = new Manazer();
        this.bot = new TransBot(20, 480);
        this.mapa = new Mapa(0, 800);
        this.mapa2 = new Mapa(1620 , 800);
        this.manazer.spravujObjekt(this.mapa);
        this.manazer.spravujObjekt(this.mapa2);
        this.enemySpawner = new EnemySpawner(this.manazer);

        this.manazer.spravujObjekt(this.bot);
    }


    
}
