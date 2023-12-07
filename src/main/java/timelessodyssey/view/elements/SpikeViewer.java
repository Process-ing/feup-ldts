package timelessodyssey.view.elements;

import com.googlecode.lanterna.TextColor;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.game.elements.Spike;

public class SpikeViewer implements ElementViewer<Spike>{
    @Override
    public void draw(Spike model, GUI gui) {
        int noise = 1;
        for (int x = 0; x < TileViewer.TILE_SIZE; x++) {
            for (int y = 4; y < TileViewer.TILE_SIZE - 1; y++) {
                gui.drawPixel(model.getPosition().x() + x, model.getPosition().y() + y + noise, new TextColor.RGB(100, 255, 100));
                noise = (noise + 1) % 2;
            }
        }
    }
}
