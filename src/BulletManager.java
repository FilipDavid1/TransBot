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
        vymazNabojPoX();
    }

    private void vymazNabojPoX() {
        ArrayList<Bullet> bulletsToRemove = new ArrayList<>();
        for (Bullet bullet : this.naboje) {
            if (this.detectBullet(bullet, 1450)) {
                bulletsToRemove.add(bullet);
                bullet.skryObrazok();
                manazer.prestanSpravovatObjekt(bullet);
            }
        }
        this.naboje.removeAll(bulletsToRemove);
    }

    private boolean detectBullet(Bullet bullet, int x) {
        if (bullet.getX() >= x) {
            return true;
        }
        return false;
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
