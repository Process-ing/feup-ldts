package timelessodyssey.control.game;

import net.jqwik.api.*;
import net.jqwik.api.constraints.Positive;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import timelessodyssey.Game;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.Vector;
import timelessodyssey.model.game.elements.Tile;
import timelessodyssey.model.game.elements.particles.DeathParticle;
import timelessodyssey.model.game.elements.particles.Particle;
import timelessodyssey.model.game.elements.particles.Snow;
import timelessodyssey.model.game.scene.Scene;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class ParticleControllerTest {
    private static final int WIDTH = 160;
    private static final int HEIGHT = 90;

    @Property
    public void snowInBounds(
            @ForAll List<@From("snow") Particle> particles,
            @ForAll @Positive long frameCount,
            @ForAll @From("action") GUI.Action action
    ) {
        Game game = mock(Game.class);

        Scene scene = new Scene(WIDTH, HEIGHT, 0);
        scene.setSnow(particles);
        ParticleController particleController = new ParticleController(scene);

        particleController.step(game, action, frameCount);

        for (Particle particle: particles) {
            Vector position = particle.getPosition();
            assertTrue(0 <= position.x(), "X out of bounds: " + position.x());
            assertTrue(position.x() < WIDTH * Tile.SIZE, "X out of bounds: " + position.x());
            assertTrue(0 <= position.y(), "Y out of bounds: " + position.y());
            assertTrue(position.y() < HEIGHT * Tile.SIZE, "Y out of bounds: " + position.y());
        }
    }

    @Provide
    public Arbitrary<Snow> snow() {
        return Combinators.combine(
                Arbitraries.doubles().greaterOrEqual(0).lessThan(WIDTH),
                Arbitraries.doubles().greaterOrEqual(0).lessThan(HEIGHT),
                Arbitraries.integers().between(1, 10),
                Arbitraries.doubles().between(0, 1000),
                Arbitraries.doubles().greaterOrEqual(0).lessThan(WIDTH)
        ).as(Snow::new);
    }

    @Provide
    public Arbitrary<GUI.Action> action() {
        return Arbitraries.of(GUI.Action.values());
    }

    @Test
    public void particlePositionCalls(){
        Game game = Mockito.mock(Game.class);
        Scene scene = Mockito.mock(Scene.class);
        ParticleController particleController = new ParticleController(scene);

        Snow snowParticle = Mockito.mock(Snow.class);
        DeathParticle deathParticle = Mockito.mock(DeathParticle.class);


        List<Particle> snow = Arrays.asList(snowParticle, snowParticle, snowParticle);
        when(scene.getSnow()).thenReturn(snow);
        List<Particle> death = Arrays.asList(deathParticle, deathParticle, deathParticle);
        when(scene.getDeathParticles()).thenReturn(death);

        particleController.step(game, GUI.Action.NONE, 0);

        verify(snowParticle, times(3)).move(scene);
        verify(snowParticle, times(3)).setPosition(Mockito.any());


        verify(deathParticle, times(3)).move(scene);
        verify(deathParticle, times(3)).setPosition(Mockito.any());
    }
}
