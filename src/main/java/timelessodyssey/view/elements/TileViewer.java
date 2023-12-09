package timelessodyssey.view.elements;

import com.googlecode.lanterna.TextColor;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.game.elements.Tile;

public class TileViewer implements ElementViewer<Tile>  {
    public static final int TILE_SIZE = 8;
    @Override
    public void draw(Tile model, GUI gui) {
        for (int x = 0; x < TILE_SIZE; x++) {
            for (int y = 0; y < TILE_SIZE; y++) {
                gui.drawPixel(model.getPosition().x() + x, model.getPosition().y() + y, new TextColor.RGB(255, 100, 100));
            }
        }
    }
}
