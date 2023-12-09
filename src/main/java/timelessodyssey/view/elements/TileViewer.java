package timelessodyssey.view.elements;

import com.googlecode.lanterna.TextColor;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.Vector;
import timelessodyssey.model.game.elements.Tile;

public class TileViewer implements ElementViewer<Tile>  {
    @Override
    public void draw(Tile model, GUI gui) {
        for (int x = 0; x < Tile.SIZE; x++) {
            for (int y = 0; y < Tile.SIZE; y++) {
                gui.drawPixel(model.getPosition().x() + x, model.getPosition().y() + y, new TextColor.RGB(255, 100, 100));
            }
        }
    }
}
