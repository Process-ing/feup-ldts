package timelessodyssey.view.elements;

import timelessodyssey.gui.GUI;
import timelessodyssey.model.game.elements.Tile;

import java.io.IOException;

public class TileViewer implements ElementViewer<Tile>  {
    @Override
    public void draw(Tile model, GUI gui) throws IOException {
        gui.drawPixel(model.getPosition().x(), model.getPosition().y(), "#FF0000");
    }
}
