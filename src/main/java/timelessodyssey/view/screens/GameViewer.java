package timelessodyssey.view.screens;

import com.googlecode.lanterna.TextColor;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.game.elements.Element;
import timelessodyssey.model.game.elements.Star;
import timelessodyssey.model.game.scene.Scene;
import timelessodyssey.view.elements.*;

import java.io.IOException;
import java.util.List;

public class GameViewer extends ScreenViewer<Scene> {
    private final PlayerViewer playerViewer;
    private final TileViewer tileViewer;
    private final SpikeViewer spikeViewer;
    private final StarViewer starViewer;
    private final ParticleViewer particleViewer;

    public GameViewer(Scene model) throws IOException {
        super(model);
        this.playerViewer = new PlayerViewer();
        this.tileViewer = new TileViewer();
        this.spikeViewer = new SpikeViewer();
        this.starViewer = new StarViewer();
        this.particleViewer = new ParticleViewer();
    }

    @Override
    public void draw(GUI gui) throws IOException {
        gui.clear();

        // Background color
        TextColor background = new TextColor.RGB(28, 28, 28);
        for (int w = 0; w < 160; w++) {
            for (int h = 0; h < 90; h++) {
                gui.drawPixel(w, h, background);
            }
        }

        drawElements(gui, getModel().getStars(), starViewer);
        drawElement(gui, getModel().getPlayer(), playerViewer);
        drawElements(gui, getModel().getTiles(), tileViewer);
        drawElements(gui, getModel().getSpikes(), spikeViewer);
        drawElements(gui, getModel().getParticles(), particleViewer);

        gui.refresh();
    }

    private <T extends Element> void drawElement(GUI gui, T element, ElementViewer<T> viewer) {
        viewer.draw(element, gui);
    }

    private <T extends Element> void drawElements(GUI gui, List<T> elements, ElementViewer<T> viewer) {
        for (T element: elements)
            drawElement(gui, element, viewer);
    }

    private <T extends Element> void drawElements(GUI gui, T[][] elements, ElementViewer<T> viewer) {
        for (T[] elementLine : elements) {
            for (T element : elementLine) {
                if (element != null)
                    drawElement(gui, element, viewer);
            }
        }
    }
}
