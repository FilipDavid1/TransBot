public class Health {
    private int health;

    public Health() {
        this.health = 100;
    }

    public void decreaseHealth() {
        this.health -= 10;
        if (this.health <= 0) {
            this.health = 0;
            System.exit(0);
        }
    }

    public int getHealth() {
        return this.health;
    }

    public void resetHealth() {
        this.health = 100;
    }
}
