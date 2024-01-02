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
        this.manazer.spravujObjekt(this);
    }

    public void tik() {
        this.deleteBulletAfter();
    }
    public void shoot(int x, int y) {
        if (this.bullets.size() < 10) {
            Bullet bullet = new Bullet(x, y, this.bulletType, BulletDirection.RIGHT);
            this.manazer.spravujObjekt(bullet);
            this.bullets.add(bullet);
        }
    }

    public void burst(int x, int y) {
        //reduce shoot cooldown
        if (this.bullets.size() < 10) {
            this.shoot(x, y);
            this.shoot(x + 150, y);
        }
    }

    public void shootDown(int x, int y) {
        if (this.bullets.size() < 10) {
            Bullet bullet = new Bullet(x, y, this.bulletType, BulletDirection.DOWN);
            bullet.zmenUhol(90);
            this.manazer.spravujObjekt(bullet);
            this.bullets.add(bullet);
        }
    }

    public void shoot2Directions(int x, int y) {
        if (this.bullets.size() < 10) {
            Bullet bullet = new Bullet(x, y, this.bulletType, BulletDirection.RIGHT);
            this.manazer.spravujObjekt(bullet);
            this.bullets.add(bullet);
            Bullet bullet2 = new Bullet(x, y, this.bulletType, BulletDirection.LEFT);
            bullet2.zmenUhol(180);
            this.manazer.spravujObjekt(bullet2);
            this.bullets.add(bullet2);
        }
    }

    public void shoot4Directions(int x, int y) {
        if (this.bullets.size() < 10) {
            Bullet bullet = new Bullet(x, y, this.bulletType, BulletDirection.RIGHT);
            this.manazer.spravujObjekt(bullet);
            this.bullets.add(bullet);
            Bullet bullet2 = new Bullet(x, y, this.bulletType, BulletDirection.LEFT);
            bullet2.zmenUhol(180);
            this.manazer.spravujObjekt(bullet2);
            this.bullets.add(bullet2);
            Bullet bullet3 = new Bullet(x, y, this.bulletType, BulletDirection.UP);
            bullet3.zmenUhol(-90);
            this.manazer.spravujObjekt(bullet3);
            this.bullets.add(bullet3);
            Bullet bullet4 = new Bullet(x, y, this.bulletType, BulletDirection.DOWN);
            bullet4.zmenUhol(90);
            this.manazer.spravujObjekt(bullet4);
            this.bullets.add(bullet4);
        }
    }

    public void shoot3Directions(int x, int y) {
        if (this.bullets.size() < 10) {
            Bullet bullet = new Bullet(x, y, this.bulletType, BulletDirection.RIGHT);
            this.manazer.spravujObjekt(bullet);
            this.bullets.add(bullet);
            Bullet bullet2 = new Bullet(x, y, this.bulletType, BulletDirection.UP_RIGHT);
            bullet2.zmenUhol(-45);
            this.manazer.spravujObjekt(bullet2);
            this.bullets.add(bullet2);
            Bullet bullet3 = new Bullet(x, y, this.bulletType, BulletDirection.DOWN_RIGHT);
            bullet3.zmenUhol(45);
            this.manazer.spravujObjekt(bullet3);
            this.bullets.add(bullet3);
        }
    }

    private void deleteBulletAfter() {
        ArrayList<Bullet> bulletsToRemove = new ArrayList<>();
        for (Bullet bullet : this.bullets) {
            if (this.detectBullet(bullet, 1450, 900) ) {
                bulletsToRemove.add(bullet);
                bullet.skryObrazok();
                manazer.prestanSpravovatObjekt(bullet);
            }
        }
        this.bullets.removeAll(bulletsToRemove);
    }

    private boolean detectBullet(Bullet bullet, int x, int y) {
        if (bullet.getX() >= x || bullet.getY() >= y || bullet.getX() <= -100 ) {
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

    public int getSize() {
        return this.bullets.size();
    }
}
