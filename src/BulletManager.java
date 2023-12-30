import java.util.ArrayList;

import fri.shapesge.Manazer;

public class BulletManager {
    private ArrayList<Bullet> bullets;
    private Manazer manazer;

    private BulletType bulletType;
    public BulletManager(BulletType bulletType) {
        this.bullets = new ArrayList<>();
        this.manazer = new Manazer();
        this.bulletType = bulletType;
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

    public void changeBulletType() {
        this.bulletType = BulletType.values()[this.bulletType.ordinal() + 1];
        if (this.bulletType.ordinal() > 4) {
            this.bulletType = BulletType.NORMAL;
        }
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }
}
