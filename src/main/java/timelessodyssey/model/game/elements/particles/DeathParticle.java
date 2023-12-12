package timelessodyssey.model.game.elements.particles;

import com.googlecode.lanterna.TextColor;
import timelessodyssey.model.Vector;
import timelessodyssey.model.game.scene.Scene;

public class DeathParticle extends Particle {
    private static final double VELOCITY_MOD = 4.0;

    public DeathParticle(double x, double y, double velocity, double angle) {
        super(x, y, 1, TextColor.ANSI.RED, new Vector(velocity * Math.cos(angle), velocity * Math.sin(angle)));
    }

    @Override
    public Vector move(Scene scene) {
        return new Vector(
            getPosition().x() + getVelocity().x(),
            getPosition().y() + getVelocity().y()
        );
    }
}
