package timelessodyssey.control.game;

import timelessodyssey.Game;
import timelessodyssey.control.Controller;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.game.elements.particles.Particle;
import timelessodyssey.model.game.scene.Scene;

import java.io.IOException;

public class ParticleController extends Controller<Scene> {
    public ParticleController(Scene model) {
        super(model);
    }

    @Override
    public void step(Game game, GUI.Action action, double time) throws IOException {
        for (Particle particle: getModel().getParticles())
            particle.setPosition(particle.move(time, getModel()));
    }
}
