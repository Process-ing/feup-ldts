package timelessodyssey.model.game.elements.particles;

import com.googlecode.lanterna.TextColor;
import timelessodyssey.model.Position;
import timelessodyssey.model.game.elements.Tile;
import timelessodyssey.model.game.scene.Scene;

public class Snow extends Particle {
    public Snow(double x, double y, int size, TextColor color, double velocity) {
        super(x, y, size, color, velocity);
    }

    @Override
    public Position move(double time, Scene scene) {
        double newX = floorMod(getPosition().x() + getVelocity() * time, scene.getWidth() * Tile.SIZE);
        double newY = floorMod(getPosition().y() + Math.sin(newX / 20),scene.getHeight() * Tile.SIZE);
        return new Position(newX, newY);
    }

    private double floorMod(double x, int y) {
        return x - Math.floor(x / y) * y;
    }
}
