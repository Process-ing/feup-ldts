package timelessodyssey.view.screens;

import com.googlecode.lanterna.TextColor;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.game.elements.Element;
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
    public void draw(GUI gui, long frameCount) throws IOException {
        gui.clear();

        // Background color
        TextColor background = new TextColor.RGB(28, 28, 28);
        for (int w = 0; w < 160; w++) {
            for (int h = 0; h < 90; h++) {
                gui.drawPixel(w, h, background);
            }
        }

        drawElement(gui, getModel().getPlayer(), playerViewer, frameCount);
        drawElements(gui, getModel().getSpikes(), spikeViewer, frameCount);
        drawElements(gui, getModel().getTiles(), tileViewer, frameCount);
        drawElements(gui, getModel().getGoals(), tileViewer, frameCount);
        drawElements(gui, getModel().getStars(), starViewer, frameCount);
        drawElement(gui, getModel().getPlayer(), playerViewer, frameCount);
        drawElements(gui, getModel().getParticles(), particleViewer, frameCount);

        gui.refresh();
    }

    private <T extends Element> void drawElement(GUI gui, T element, ElementViewer<T> viewer, long frameCount) throws IOException {
        viewer.draw(element, gui, frameCount);
    }

    private <T extends Element> void drawElements(GUI gui, List<T> elements, ElementViewer<T> viewer, long frameCount) throws IOException {
        for (T element: elements)
            drawElement(gui, element, viewer, frameCount);
    }

    private <T extends Element> void drawElements(GUI gui, T[][] elements, ElementViewer<T> viewer, long frameCount) throws IOException {
        for (T[] elementLine : elements) {
            for (T element : elementLine) {
                if (element != null)
                    drawElement(gui, element, viewer, frameCount);
            }
        }
    }
}
