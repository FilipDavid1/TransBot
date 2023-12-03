import java.util.ArrayList;
import java.util.Iterator;
import fri.shapesge.Manazer;

public class BulletManager {
    private ArrayList<Naboj> naboje;
    private Manazer manazer;

    private int typNaboja;
    public BulletManager() {
        this.naboje = new ArrayList<>();
        this.manazer = new Manazer();
        this.typNaboja = 0;
    }

    public void vystrel(int x, int y) {
        Naboj naboj = new Naboj(x, y, this.typNaboja);
        this.manazer.spravujObjekt(naboj);
        this.naboje.add(naboj);
        vymazNabojPoX(1450);
    }

    private void vymazNabojPoX(int x) {
        Iterator<Naboj> iterator = naboje.iterator();
        while (iterator.hasNext()) {
            Naboj naboj = iterator.next();
            if (naboj.getX() >= x) {
                naboj.skryObrazok();
                manazer.prestanSpravovatObjekt(naboj);
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


}
