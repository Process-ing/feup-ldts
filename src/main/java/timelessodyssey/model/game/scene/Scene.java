package timelessodyssey.model.game.scene;

import timelessodyssey.model.Position;
import timelessodyssey.model.game.elements.Player;
import timelessodyssey.model.game.elements.Tile;

import javax.management.loading.ClassLoaderRepository;
import java.util.ArrayList;
import java.util.List;

public class Scene {
    private final int width;
    private final int height;

    private Player player;

    private List<Tile> tiles;

    public Scene(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public void setTiles(List<Tile> tiles) {
        this.tiles = tiles;
    }

    public boolean isEmpty(Position position) {
        for (Tile tile : tiles) {
            for (int w = 0; w < 8; w++) {
                Position pos1 = new Position(tile.getPosition().x() + w, tile.getPosition().y());
                Position pos2 = new Position(tile.getPosition().x() + w, tile.getPosition().y() + 7);
                if (pos1.equals(position)) {
                    return false;
                }
                if (pos2.equals(position)) {
                    return false;
                }
            }
            for (int h = 0; h < 8; h++) {
                Position pos1 = new Position(tile.getPosition().x(), tile.getPosition().y() + h);
                Position pos2 = new Position(tile.getPosition().x() + 7, tile.getPosition().y() + h);
                if (pos1.equals(position)) {
                    return false;
                }
                if (pos2.equals(position)) {
                    return false;
                }
            }
        }
        return true;
    }
}
