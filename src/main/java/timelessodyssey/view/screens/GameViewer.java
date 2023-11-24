package timelessodyssey.view.screens;

import com.googlecode.lanterna.TextColor;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.game.scene.Scene;

import java.io.IOException;

public class GameViewer extends ScreenViewer<Scene> {
    public GameViewer(Scene model) {
        super(model);
    }

    @Override
    public void draw(GUI gui) throws IOException {
        gui.clear();
        for (int w = 0; w < 160; w++){
            TextColor.RGB current = new TextColor.RGB(0,0, w*255/160);
            TextColor.RGB opposite = new TextColor.RGB(0,0, 255 - (w*255/160));
            gui.drawPixel(w, 0, current);
            gui.drawPixel(w, 89, opposite);
        }
        for (int h = 1; h < 90-1; h++){
            TextColor.RGB current = new TextColor.RGB(0,0, h*255/90);
            TextColor.RGB opposite = new TextColor.RGB(0,0, 255 - (h*255/90));
            gui.drawPixel(0, h, current);
            gui.drawPixel(159, h, opposite);
        }
        gui.refresh();
    }
}
