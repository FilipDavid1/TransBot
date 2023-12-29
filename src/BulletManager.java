import java.util.ArrayList;

import fri.shapesge.Manazer;

public class BulletManager {
    private ArrayList<Bullet> bullets;
    private Manazer manazer;

    private int bulletType;
    public BulletManager() {
        this.bullets = new ArrayList<>();
        this.manazer = new Manazer();
        this.bulletType = 0;
    }

    public void shoot(int x, int y) {
        Bullet bullet = new Bullet(x, y, this.bulletType);
        this.manazer.spravujObjekt(bullet);
        this.bullets.add(bullet);
        deleteBulletAfterX();
    }

    private void deleteBulletAfterX() {
        ArrayList<Bullet> bulletsToRemove = new ArrayList<>();
        for (Bullet bullet : this.bullets) {
            if (this.detectBullet(bullet, 1450)) {
                bulletsToRemove.add(bullet);
                bullet.skryObrazok();
                manazer.prestanSpravovatObjekt(bullet);
            }
        }
        this.bullets.removeAll(bulletsToRemove);
    }

    private boolean detectBullet(Bullet bullet, int x) {
        if (bullet.getX() >= x) {
            return true;
        }
        return false;
    }

    public void zmenNaboje() {
        this.bulletType++;
        if (this.bulletType > 4) {
            this.bulletType = 0;
        }
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }
}
