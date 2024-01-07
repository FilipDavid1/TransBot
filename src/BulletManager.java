import java.util.ArrayList;

import fri.shapesge.Manazer;

/**
 * Trieda BulletManager spravuje náboje.
 * Vytvára náboje a pridáva ich do ArrayListu bullets.
 * Zároveň zmaže náboje ktoré sú mimo obrazovky.
 *
 * @author Filip Dávid
 *
 * @version 1.0
 */
public class BulletManager {
    private final ArrayList<Bullet> bullets;
    private Manazer manazer;

    private BulletType bulletType;

    /**
     * Konštruktor nastaví bullets na prázdny ArrayList, manazer na nový Manazer a bulletType na vstupný parameter.
     * Manazer spravuje objekt BulletManager.
     * @param bulletType
     */
    public BulletManager(BulletType bulletType) {
        this.bullets = new ArrayList<>();
        this.manazer = new Manazer();
        this.bulletType = bulletType;
        this.manazer.spravujObjekt(this);
    }

    /**
     * Metóda zavolá metódu deleteBulletAfter(), ktorá zmaže náboje ktoré sú mimo obrazovky.
     */
    public void tik() {
        this.deleteBulletAfter();
    }

    /**
     * Metóda zavolá metódu vystrelí náboj na zadanej pozícii x a y.
     * Náboj sa pridá do ArrayListu bullets a manazer spravuje objekt náboja.
     * Ak je veľkosť ArrayListu bullets väčšia ako 10, tak sa náboj nevystrelí.
     * @param x
     * @param y
     */
    public void shoot(int x, int y) {
        if (this.bullets.size() < 10 && this.bulletType != BulletType.DIFFUSION_BEAM) {
            Bullet bullet = new Bullet(x, y, this.bulletType, BulletDirection.RIGHT);
            this.manazer.spravujObjekt(bullet);
            this.bullets.add(bullet);
        } else if (this.bullets.size() < 3 && this.bulletType == BulletType.DIFFUSION_BEAM) {
            Bullet bullet = new Bullet(x, y, this.bulletType, BulletDirection.RIGHT);
            this.manazer.spravujObjekt(bullet);
            this.bullets.add(bullet);
        }
    }

    /**
     * Metóda vystrelí náboj na pozícii x a y a zároveň vystrelí náboj na pozícii x + 150 a y.
     * @param x
     * @param y
     */
    public void burst(int x, int y) {
        this.shoot(x, y);
        this.shoot(x + 150, y);
    }

    /**
     * Metóda vystrelí náboj smerom nadol na pozícii x a y.
     * Zároveň zmení uhol náboja na 90 stupňov.
     * Ak je veľkosť ArrayListu bullets väčšia ako 10, tak sa náboj nevystrelí.
     * @param x
     * @param y
     */
    public void shootDown(int x, int y) {
        if (this.bullets.size() < 10) {
            Bullet bullet = new Bullet(x, y, this.bulletType, BulletDirection.DOWN);
            bullet.changeAngle(90);
            this.manazer.spravujObjekt(bullet);
            this.bullets.add(bullet);
        }
    }

    /**
     * Metóda vystrelí dva náboje smerom doprava a doľava na pozícii x a y.
     * Zároveň zmení uhol druhého náboja na 180 stupňov.
     * Ak je veľkosť ArrayListu bullets väčšia ako 10, tak sa náboj nevystrelí.
     * @param x
     * @param y
     */
    public void shoot2Directions(int x, int y) {
        if (this.bullets.size() < 5) {
            Bullet bullet = new Bullet(x, y, this.bulletType, BulletDirection.RIGHT);
            this.manazer.spravujObjekt(bullet);
            this.bullets.add(bullet);
            Bullet bullet2 = new Bullet(x, y, this.bulletType, BulletDirection.LEFT);
            bullet2.changeAngle(180);
            this.manazer.spravujObjekt(bullet2);
            this.bullets.add(bullet2);
        }
    }

    /**
     * Metóda vystrelí štyri náboje a to smerom dole, hore, doľava, doprava na pozícií x a y.
     * Náboje sa otočia podľa ich smeru.
     * Ak je veľkosť ArrayListu bullets väčšia ako 10, tak sa náboj nevystrelí.
     * @param x
     * @param y
     */
    public void shoot4Directions(int x, int y) {
        if (this.bullets.size() < 10) {
            Bullet bullet = new Bullet(x, y, this.bulletType, BulletDirection.RIGHT);
            this.manazer.spravujObjekt(bullet);
            this.bullets.add(bullet);
            Bullet bullet2 = new Bullet(x, y, this.bulletType, BulletDirection.LEFT);
            bullet2.changeAngle(180);
            this.manazer.spravujObjekt(bullet2);
            this.bullets.add(bullet2);
            Bullet bullet3 = new Bullet(x, y, this.bulletType, BulletDirection.UP);
            bullet3.changeAngle(-90);
            this.manazer.spravujObjekt(bullet3);
            this.bullets.add(bullet3);
            Bullet bullet4 = new Bullet(x, y, this.bulletType, BulletDirection.DOWN);
            bullet4.changeAngle(90);
            this.manazer.spravujObjekt(bullet4);
            this.bullets.add(bullet4);
        }
    }

    /**
     * Metóda vystrelí tri náboje a to smerom doprava, doprava hore a doprava dole na pozícií x a y.
     * Náboje sa otočia podľa ich smeru.
     * Ak je veľkosť ArrayListu bullets väčšia ako 10, tak sa náboj nevystrelí.
     * @param x
     * @param y
     */
    public void shoot3Directions(int x, int y) {
        if (this.bullets.size() < 5) {
            Bullet bullet = new Bullet(x, y, this.bulletType, BulletDirection.RIGHT);
            this.manazer.spravujObjekt(bullet);
            this.bullets.add(bullet);
            Bullet bullet2 = new Bullet(x, y, this.bulletType, BulletDirection.UP_RIGHT);
            bullet2.changeAngle(-45);
            this.manazer.spravujObjekt(bullet2);
            this.bullets.add(bullet2);
            Bullet bullet3 = new Bullet(x, y, this.bulletType, BulletDirection.DOWN_RIGHT);
            bullet3.changeAngle(45);
            this.manazer.spravujObjekt(bullet3);
            this.bullets.add(bullet3);
        }
    }

    /**
     * Metóda zmaže náboj ak metóda detectBullet vráti true.
     */
    private void deleteBulletAfter() {
        ArrayList<Bullet> bulletsToRemove = new ArrayList<>();
        for (Bullet bullet : this.bullets) {
            if (this.detectBullet(bullet, 1450, 900) ) {
                bulletsToRemove.add(bullet);
                bullet.hideImage();
                manazer.prestanSpravovatObjekt(bullet);
            }
        }
        this.bullets.removeAll(bulletsToRemove);
    }

    /**
     * Metóda detekuje či náboj je mimo obrazovky.
     * @param bullet
     * @param x
     * @param y
     * @return true ak je náboj mimo obrazovky, inak false
     */
    private boolean detectBullet(Bullet bullet, int x, int y) {
        if (bullet.getX() >= x || bullet.getY() >= y || bullet.getX() <= -100 ) {
            return true;
        }
        return false;
    }

    /**
     * Metóda zmení bulletType na ďalší typ.
     */
    public void changeBulletType(BulletType bulletType) {
        //toto nie je môj kód, ale kód z internetu
        this.bulletType = bulletType;
    }

    /**
     * Metóda vráti ArrayList nábojov.
     * @return bullets
     */
    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    /**
     * Metóda vráti bulletType.
     * @return bulletType
     */
    public BulletType getBulletType() {
        return this.bulletType;
    }

    public String getBulletName() {
        return this.bulletType.getBulletName();
    }

}
