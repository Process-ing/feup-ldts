package timelessodyssey.view.screens;

import com.googlecode.lanterna.TextColor;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.game.scene.Scene;
import timelessodyssey.view.elements.ElementViewer;
import timelessodyssey.view.elements.PlayerViewer;
import timelessodyssey.model.game.elements.Element;

import java.io.IOException;

public class GameViewer extends ScreenViewer<Scene> {
    public GameViewer(Scene model) {
        super(model);
    }

    @Override
    public void draw(GUI gui) throws IOException {
        gui.clear();

        // BACKGROUND (NOT SCENE RELATED)
        for (int w = 0; w < 160; w++) {
            for (int h = 0; h < 90; h++) {
                gui.drawPixel(w, h, new TextColor.RGB(28, 28, 28));
            }
        }

        // DRAW PLAYER
        drawElement(gui, getModel().getPlayer(), new PlayerViewer());

        gui.refresh();
    }

    private <T extends Element> void drawElement(GUI gui, T element, ElementViewer<T> viewer) {
        viewer.draw(element, gui);
    }

}
