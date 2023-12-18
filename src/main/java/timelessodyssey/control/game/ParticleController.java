package timelessodyssey.control.game;

import timelessodyssey.Game;
import timelessodyssey.control.Controller;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.game.elements.particles.Particle;
import timelessodyssey.model.game.scene.Scene;

public class ParticleController extends Controller<Scene> {
    public ParticleController(Scene model) {
        super(model);
    }

    @Override
    public void step(Game game, GUI.Action action, long frameCount) {
        for (Particle particle: getModel().getSnow())
            particle.setPosition(particle.move(getModel()));
        for (Particle particle: getModel().getDeathParticles())
            particle.setPosition(particle.move(getModel()));
    }
}
