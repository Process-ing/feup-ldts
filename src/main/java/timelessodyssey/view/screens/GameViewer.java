package timelessodyssey.view.screens;

import timelessodyssey.gui.GUI;
import timelessodyssey.model.game.elements.Element;
import timelessodyssey.model.game.scene.Scene;
import timelessodyssey.view.elements.ElementViewer;
import timelessodyssey.view.elements.TileViewer;

import java.io.IOException;
import java.util.List;

public class GameViewer extends ScreenViewer<Scene> {
    public GameViewer(Scene model) {
        super(model);
    }

    @Override
    public void draw(GUI gui) throws IOException {
        gui.clear();
        drawElements(gui, getModel().getTiles(), new TileViewer());
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
