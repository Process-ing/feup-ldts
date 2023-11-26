package timelessodyssey.view.elements;

import com.googlecode.lanterna.TextColor;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.game.elements.Tile;

import java.io.IOException;

public class TileViewer implements ElementViewer<Tile>  {
    @Override
    public void draw(Tile model, GUI gui) throws IOException {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                gui.drawPixel(model.getPosition().x() + x, model.getPosition().y() + y, new TextColor.RGB(255, 100, 100));
            }
        }
    }
}
