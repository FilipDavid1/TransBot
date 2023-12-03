import java.util.ArrayList;
import java.util.Iterator;
import fri.shapesge.Manazer;

public class BulletManager {
    private ArrayList<Bullet> naboje;
    private Manazer manazer;

    private int typNaboja;
    public BulletManager() {
        this.naboje = new ArrayList<>();
        this.manazer = new Manazer();
        this.typNaboja = 0;
    }

    public void vystrel(int x, int y) {
        Bullet bullet = new Bullet(x, y, this.typNaboja);
        this.manazer.spravujObjekt(bullet);
        this.naboje.add(bullet);
        vymazNabojPoX(1450);
    }

    private void vymazNabojPoX(int x) {
        Iterator<Bullet> iterator = naboje.iterator();
        while (iterator.hasNext()) {
            Bullet bullet = iterator.next();
            if (bullet.getX() >= x) {
                bullet.skryObrazok();
                manazer.prestanSpravovatObjekt(bullet);
                iterator.remove();
            }
        }
    }


    public void zmenNaboje() {
        this.typNaboja++;
        if (this.typNaboja > 4) {
            this.typNaboja = 0;
        }
    }

    public ArrayList<Bullet> getNaboje() {
        return naboje;
    }
}
