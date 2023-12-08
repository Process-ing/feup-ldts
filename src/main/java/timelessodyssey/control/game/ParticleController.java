package timelessodyssey.control.game;

import timelessodyssey.Game;
import timelessodyssey.control.Controller;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.Position;
import timelessodyssey.model.game.elements.Particle;
import timelessodyssey.model.game.elements.Tile;
import timelessodyssey.model.game.scene.Scene;

import java.io.IOException;

public class ParticleController extends Controller<Scene> {
    public ParticleController(Scene model) {
        super(model);
    }

    @Override
    public void step(Game game, GUI.Action action, double time) throws IOException {
        for (Particle particle: getModel().getParticles())
            moveParticle(particle, time);
    }

    private void moveParticle(Particle particle, double time) {
        double newX = floorMod(particle.getPosition().x() + particle.getVelocity() * time, getModel().getWidth() * Tile.SIZE);
        double newY = floorMod(particle.getPosition().y() + Math.sin(newX / 10),getModel().getHeight() * Tile.SIZE);
        particle.setPosition(new Position(newX, newY));
    }

    private double floorMod(double x, int y) {
        return x - Math.floor(x / y) * y;
    }
}
