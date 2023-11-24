package timelessodyssey.model;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PositionTest {
    @Property
    void getRight(@ForAll int x, @ForAll int y) {
        Position position = new Position(x, y);
        Position right = position.getRight();
        assertEquals(x + 1, right.x());
        assertEquals(y, right.y());
    }

    @Property
    void getLeft(@ForAll int x, @ForAll int y) {
        Position position = new Position(x, y);
        Position left = position.getLeft();
        assertEquals(x - 1, left.x());
        assertEquals(y, left.y());
    }

    @Property
    void getDown(@ForAll int x, @ForAll int y) {
        Position position = new Position(x, y);
        Position down = position.getDown();
        assertEquals(x, down.x());
        assertEquals(y + 1, down.y());
    }

    @Property
    void getUp(@ForAll int x, @ForAll int y) {
        Position position = new Position(x, y);
        Position up = position.getUp();
        assertEquals(x, up.x());
        assertEquals(y - 1, up.y());
    }
}
