package timelessodyssey.model.game.elements.particles;

import com.googlecode.lanterna.TextColor;
import timelessodyssey.model.Vector;
import timelessodyssey.model.game.elements.Element;
import timelessodyssey.model.game.scene.Scene;

public abstract class Particle extends Element {
    private final int size;
    private final TextColor color;
    private final Vector velocity;

    public Particle(double x, double y, int size, TextColor color, Vector velocity) {
        super(x, y);
        this.size = size;
        this.color = color;
        this.velocity = velocity;
    }

    public int getSize() {
        return size;
    }

    public TextColor getColor() {
        return color;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public abstract Vector move(Scene scene);
}
