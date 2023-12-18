package timelessodyssey.model.game.elements.particles;

import net.jqwik.api.*;
import net.jqwik.api.lifecycle.BeforeTry;
import timelessodyssey.model.Vector;
import timelessodyssey.model.game.scene.Scene;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SnowTest {
    private static final int WIDTH = 160;
    private static final int HEIGHT = 90;

    private Scene scene;

    @BeforeTry
    public void setup() {
        this.scene = mock(Scene.class);
        when(scene.getWidth()).thenReturn(WIDTH);
        when(scene.getHeight()).thenReturn(HEIGHT);
    }

    @Property
    public void moveToCorrectDirection(@ForAll @From("snow") Snow snow) {
        Vector newPosition = snow.move(scene);

        assertTrue(newPosition.x() > snow.getPosition().x());
    }

    @Provide
    public Arbitrary<Snow> snow() {
        return Combinators.combine(
                Arbitraries.doubles().greaterOrEqual(0).lessThan(WIDTH),
                Arbitraries.doubles().greaterOrEqual(0).lessThan(HEIGHT),
                Arbitraries.integers().between(1, 10),
                Arbitraries.doubles().greaterThan(0).lessThan(10),
                Arbitraries.doubles().greaterOrEqual(0).lessThan(WIDTH)
        ).as(Snow::new);
    }
}
