package timelessodyssey.model.credits;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditsTest {

    Credits credits;
    @BeforeEach
    public void setup(){
        this.credits = new Credits(14, 15,333000);
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
