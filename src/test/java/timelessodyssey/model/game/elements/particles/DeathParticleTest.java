package timelessodyssey.model.game.elements.particles;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.DoubleRange;
import net.jqwik.api.lifecycle.BeforeTry;
import timelessodyssey.model.Vector;
import timelessodyssey.model.game.scene.Scene;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class DeathParticleTest {
    private static final int WIDTH = 160;
    private static final int HEIGHT = 90;

    private Scene scene;

    @BeforeTry
    public void setup() {
        this.scene = mock(Scene.class);
    }

    @Property
    public void moveToCorrectDirection(
        @ForAll @DoubleRange(max = WIDTH) double x,
        @ForAll @DoubleRange(max = HEIGHT) double y,
        @ForAll @DoubleRange(minIncluded = false, max = 10) double velocity,
        @ForAll @DoubleRange(max = 6.28) double angle
    ) {
        final double THRESHOLD = 0.01;
        DeathParticle deathParticle = new DeathParticle(x, y, velocity, angle);

        Vector newPosition = deathParticle.move(scene);
        double dx = newPosition.x() - deathParticle.getPosition().x();
        double dy = newPosition.y() - deathParticle.getPosition().y();

        assertTrue(angle <= Math.PI ? dy >= 0 : dy < 0);
        if(angle != Math.PI / 2 || angle != 3 * Math.PI / 2)
            assertEquals(Math.tan(angle), dy / dx, THRESHOLD);
        else
            assertEquals(0, dx);
    }
}
