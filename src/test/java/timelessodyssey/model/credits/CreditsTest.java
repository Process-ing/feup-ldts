package timelessodyssey.model.credits;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import timelessodyssey.model.game.elements.player.Player;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditsTest {
    private Credits credits;
    private Player player;

    @BeforeEach
    public void setup() {
        player = new Player(0, 0, null);
        while (player.getStarCounter() != 14)
            player.increaseStars();
        while (player.getNumberOfDeaths() != 15)
            player.increaseDeaths();
        player.setBirthTime(System.currentTimeMillis() - 333000);
        this.credits = new Credits(player);
    }

    @Test
    public void equals(){
        assertEquals(15, credits.getDeaths());
        assertEquals(14, credits.getScore());
        assertEquals(5, credits.getMinutes());
        assertEquals(33, credits.getSeconds());

        credits.setScore(18);

        String[] names = new String[1];
        names[0] = "name test 1";
        credits.setNames(names);

        String[] messages = new String[1];
        messages[0] = "message test 1";
        credits.setMessages(messages);

        assertEquals(18, credits.getScore());
        assertEquals(names, credits.getNames());
        assertEquals(messages, credits.getMessages());
    }
}
