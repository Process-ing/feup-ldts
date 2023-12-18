package timelessodyssey.model.game.elements.particles;

import com.googlecode.lanterna.TextColor;
import timelessodyssey.model.Vector;
import timelessodyssey.model.game.elements.Tile;
import timelessodyssey.model.game.scene.Scene;

public class Snow extends Particle {
    private final double initOffset;

    public Snow(double x, double y, int size, double velocity, double initOffset) {
        super(x, y, size, TextColor.ANSI.WHITE_BRIGHT, new Vector(velocity, 0));
        this.initOffset = initOffset;
    }

    @Override
    public Vector move(Scene scene) {
        double newX = floorMod(getPosition().x() + getVelocity().x(), scene.getWidth() * Tile.SIZE);
        double newY = floorMod(getPosition().y() + Math.sin((newX + initOffset) / 20),scene.getHeight() * Tile.SIZE);
        return new Vector(newX, newY);
    }

    private double floorMod(double x, int y) {
        return x - Math.floor(x / y) * y;
    }
}
