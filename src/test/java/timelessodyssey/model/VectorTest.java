package timelessodyssey.model;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PositionTest {
    @Test
    public void equals() {
        Vector position1 = new Vector(32, 15);
        Vector position2 = new Vector(32, 15);
        Vector position3 = new Vector(-56, 15);
        Vector position4 = new Vector(32, Integer.MAX_VALUE);
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
        Vector position1 = new Vector(x, y);
        Vector position2 = new Vector(x, y);

        assertEquals(position1.hashCode(), position2.hashCode());
    }
}
