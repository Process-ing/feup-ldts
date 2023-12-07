package timelessodyssey.control.game;

import timelessodyssey.Game;
import timelessodyssey.control.Controller;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.Position;
import timelessodyssey.model.game.elements.Particle;
import timelessodyssey.model.game.scene.Scene;
import timelessodyssey.view.elements.TileViewer;

import java.io.IOException;

public class ParticleController extends Controller<Scene> {
    public ParticleController(Scene model) {
        super(model);
    }

    @Override
    public void step(Game game, GUI.Action action, long time) throws IOException {
        for (Particle particle: getModel().getParticles())
            moveParticle(particle, time);
        System.out.printf("%d, %d\n", getModel().getParticles().get(0).getPosition().x(), getModel().getParticles().get(0).getPosition().y());
    }

    private void moveParticle(Particle particle, long time) {
        double newX = (particle.getPosition().x() + particle.getVelocity() * time) % (getModel().getWidth() * TileViewer.TILE_SIZE);
        double newY = particle.getPosition().y() + Math.sin(newX / 10);
        particle.setPosition(new Position((int)newX, (int)newY));
    }
}
