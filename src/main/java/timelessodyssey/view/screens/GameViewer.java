package timelessodyssey.view.screens;

import com.googlecode.lanterna.TextColor;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.game.elements.Element;
import timelessodyssey.model.game.elements.Spike;
import timelessodyssey.model.game.scene.Scene;
import timelessodyssey.view.elements.ElementViewer;
import timelessodyssey.view.elements.SpikeViewer;
import timelessodyssey.view.elements.TileViewer;

import java.io.IOException;
import java.util.List;
import timelessodyssey.view.elements.PlayerViewer;
import timelessodyssey.view.Sprite;

public class GameViewer extends ScreenViewer<Scene> {
    public GameViewer(Scene model) {
        super(model);
    }

    @Override
    public void draw(GUI gui) throws IOException {
        gui.clear();

        // Background color
        TextColor.RGB background = new TextColor.RGB(28, 28, 28);
        for (int w = 0; w < 160; w++) {
            for (int h = 0; h < 90; h++) {
                gui.drawPixel(w, h, background);
            }
        }

        drawElement(gui, getModel().getPlayer(), new PlayerViewer());
        drawElements(gui, getModel().getTiles(), new TileViewer());
        drawElements(gui, getModel().getSpikes(), new SpikeViewer());

        gui.refresh();
    }

    private <T extends Element> void drawElement(GUI gui, T element, ElementViewer<T> viewer) throws IOException {
        viewer.draw(element, gui);
    }

    private <T extends Element> void drawElements(GUI gui, List<T> elements, ElementViewer<T> viewer) throws IOException {
        for (T element : elements)
            drawElement(gui, element, viewer);
    }
}
