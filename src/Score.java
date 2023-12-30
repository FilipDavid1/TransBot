public class Score {

    private int score;

    public Score() {
        this.score = 0;
    }

    public void increaseScore() {
        this.score += 100;
    }

    public int getScore() {
        return this.score;
    }

    public void resetScore() {
        this.score = 0;
    }
}
