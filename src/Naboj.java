import fri.shapesge.Obrazok;
/**
 * Write a description of class Naboj here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Naboj {
    private Obrazok nabojImg;
    private int x;
    private int y;
    private int aktualnyNaboj;
    
    public Naboj(int x, int y, int aktualny) {
        this.aktualnyNaboj = aktualny;
        this.nabojImg = new Obrazok("/Users/filipdavid/Documents/INF/TransBotInteliJ/src/pics/amo/" + this.getNazovNaboja() + ".png");
        this.x = x;
        this.y = y;
        this.nabojImg.zmenPolohu(x, y);
        this.nabojImg.zobraz();
    }
    
    public void tik() {
        if (this.x <= 1430) {
            this.x += 30;
            this.nabojImg.posunVodorovne(30);
        } else {
            this.nabojImg.skry();
        }
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void skryObrazok() {
        this.nabojImg.skry();
    }
    
    public String getNazovNaboja() {
        switch (this.aktualnyNaboj) {
            case 0:
                return "normal";
            
            case 1:
                return "cannon";
            
            case 2:
                return "beam";
                
            case 3:
                return "sword_fire";
                
            case 4:
                return "diffusion_beam";
            
            default:
                return String.valueOf(this.aktualnyNaboj);
        }
    }


}
