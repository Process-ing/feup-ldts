package timelessodyssey.control;

import com.googlecode.lanterna.TextColor;
import net.jqwik.api.*;
import net.jqwik.api.constraints.DoubleRange;
import timelessodyssey.Game;
import timelessodyssey.control.game.ParticleController;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.Position;
import timelessodyssey.model.game.elements.particles.Particle;
import timelessodyssey.model.game.elements.Tile;
import timelessodyssey.model.game.elements.particles.Snow;
import timelessodyssey.model.game.scene.Scene;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class ParticleControllerTest {
    private static final int WIDTH = 160;
    private static final int HEIGHT = 90;

    @Property
    public void snowInBounds(
            @ForAll List<@From("snow") Particle> particles,
            @ForAll @DoubleRange(max = 16) double time,
            @ForAll @From("action") GUI.Action action
    ) throws IOException {
        Game game = mock(Game.class);

        Scene scene = new Scene(WIDTH, HEIGHT, 0);
        scene.setParticles(particles);
        ParticleController particleController = new ParticleController(scene);

        particleController.step(game, action, time);

        for (Particle particle: particles) {
            Position position = particle.getPosition();
            assertTrue(0 <= position.x(), "X out of bounds: " + position.x());
            assertTrue(position.x() < WIDTH * Tile.SIZE, "X out of bounds: " + position.x());
            assertTrue(0 <= position.y(), "Y out of bounds: " + position.y());
            assertTrue(position.y() < HEIGHT * Tile.SIZE, "Y out of bounds: " + position.y());
        }
    }

    @Provide
    public Arbitrary<Snow> snow() {
        return Combinators.combine(
                Arbitraries.doubles().greaterOrEqual(0).lessThan(WIDTH * Tile.SIZE),
                Arbitraries.doubles().greaterOrEqual(0).lessThan(HEIGHT * Tile.SIZE),
                Arbitraries.integers().between(1, 10),
                Arbitraries.integers().between(0, 255),
                Arbitraries.integers().between(0, 255),
                Arbitraries.integers().between(0, 255),
                Arbitraries.doubles().between(0, 1000)
        ).as((x, y, size, r, g, b, velocity) -> new Snow(x, y, size, new TextColor.RGB(r, g, b), velocity));
    }

    @Provide
    public Arbitrary<GUI.Action> action() {
        return Arbitraries.of(GUI.Action.values());
    }
}
