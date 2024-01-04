import java.io.IOException;

/**
 * Trieda Main je hlavná trieda hry. Vytvorí objekt hra.
 *
 * @author Filip Dávid
 *
 * @version 1.0
 */

public class Main {

    /**
     * Metóda main vytvorí objekt hra.
     * @param args
     */
    public static void main(String[] args) throws IOException {
        Game game = Game.getInstance();
    }
}