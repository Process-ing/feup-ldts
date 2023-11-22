package timelessodyssey.model.game.map;

import timelessodyssey.model.Position;
import timelessodyssey.model.game.elements.Player;
import timelessodyssey.model.game.elements.Tile;

import java.util.List;

public class Map {
    private final int width;
    private final int height;

    private Player player;

    private List<Tile> tiles;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getHero() {
        return player;
    }

    public void setHero(Player player) {
        this.player = player;
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public void setTiles(List<Tile> tiles) {
        this.tiles = tiles;
    }

    public boolean isEmpty(Position position) {
        for (Tile tile : tiles)
            if (tile.getPosition().equals(position))
                return false;
        return true;
    }
}
