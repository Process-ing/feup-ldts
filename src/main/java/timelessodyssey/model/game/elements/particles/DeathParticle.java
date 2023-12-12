package timelessodyssey.model.game.elements.particles;

import com.googlecode.lanterna.TextColor;
import timelessodyssey.model.Vector;
import timelessodyssey.model.game.scene.Scene;

public class DeathParticle extends Particle {
    public DeathParticle(double x, double y, double velocity, double angle) {
        super(x, y, 1, TextColor.ANSI.RED_BRIGHT, new Vector(velocity * Math.cos(angle), velocity * Math.sin(angle)));
    }

    @Override
    public Vector move(Scene scene) {
        return new Vector(
            getPosition().x() + getVelocity().x(),
            getPosition().y() + getVelocity().y()
        );
    }
}
