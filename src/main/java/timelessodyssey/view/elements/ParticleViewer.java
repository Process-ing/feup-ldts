package timelessodyssey.view.elements;

import timelessodyssey.gui.GUI;
import timelessodyssey.model.game.elements.particles.Particle;

public class ParticleViewer implements ElementViewer<Particle> {
    @Override
    public void draw(Particle model, GUI gui, long frameCount) {
        gui.drawRectangle(model.getPosition().x(), model.getPosition().y(), model.getSize(), model.getSize(), model.getColor());
    }
}
