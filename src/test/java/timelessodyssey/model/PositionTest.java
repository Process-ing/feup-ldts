package timelessodyssey.model;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PositionTest {
    @Test
    public void equals() {
        Position position1 = new Position(32, 15);
        Position position2 = new Position(32, 15);
        Position position3 = new Position(-56, 15);
        Position position4 = new Position(32, Integer.MAX_VALUE);
        Object obj = new Object();

        assertEquals(position1, position1);
        assertNotEquals(position4, null);
        assertNotEquals(position2, obj);
        assertEquals(position1, position2);
        assertNotEquals(position1, position3);
        assertNotEquals(position1, position4);
    }

    @Property
    public void equalHashes(@ForAll double x, @ForAll double y) {
        Position position1 = new Position(x, y);
        Position position2 = new Position(x, y);

        assertEquals(position1.hashCode(), position2.hashCode());
    }
}
