/**
 * Trieda Score predstavuje skóre hráča.
 * Skóre sa zvyšuje o 100 za každého zabitie nepriateľa.
 *
 * @author Filip Dávid
 *
 * @version 1.0
 */
public class Score {

    private int score;

    /**
     * Konštruktor nastaví score na 0.
     */
    public Score() {
        this.score = 0;
    }

    /**
     * Metóda zvýši score o 100.
     */
    public void increaseScore() {
        this.score += 100;
    }

    /**
     * Metóda vráti score.
     * @return score
     */
    public int getScore() {
        return this.score;
    }

}
