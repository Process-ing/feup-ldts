package timelessodyssey.view.screens;

import com.googlecode.lanterna.TextColor;
import timelessodyssey.gui.GUI;
import timelessodyssey.gui.ResizableGUI;
import timelessodyssey.model.game.elements.Element;
import timelessodyssey.model.game.scene.Scene;
import timelessodyssey.view.ViewerProvider;
import timelessodyssey.view.elements.*;

import java.io.IOException;
import java.util.List;

public class GameViewer extends ScreenViewer<Scene> {
    public static final TextColor backgroundColor = new TextColor.RGB(28, 28, 28);

    private final PlayerViewer playerViewer;
    private final TileViewer tileViewer;
    private final SpikeViewer spikeViewer;
    private final StarViewer starViewer;
    private final ParticleViewer particleViewer;

    public GameViewer(Scene model, ViewerProvider viewerProvider) {
        super(model);
        this.playerViewer = viewerProvider.getPlayerViewer();
        this.tileViewer = viewerProvider.getTileViewer();
        this.spikeViewer = viewerProvider.getSpikeViewer();
        this.starViewer = viewerProvider.getStarViewer();
        this.particleViewer = viewerProvider.getParticleViewer();
    }

    @Override
    public void draw(ResizableGUI gui, long frameCount) throws IOException {
        gui.clear();
        gui.drawRectangle(0, 0, gui.getWidth(), gui.getHeight(), backgroundColor);

        drawElement(gui, getModel().getPlayer(), playerViewer, frameCount);
        drawElements(gui, getModel().getSpikes(), spikeViewer, frameCount);
        drawElements(gui, getModel().getTiles(), tileViewer, frameCount);
        drawElements(gui, getModel().getGoals(), tileViewer, frameCount);
        drawElements(gui, getModel().getStars(), starViewer, frameCount);
        drawElements(gui, getModel().getSnow(), particleViewer, frameCount);
        drawElements(gui, getModel().getDeathParticles(), particleViewer, frameCount);

        gui.refresh();
    }

    private <T extends Element> void drawElement(GUI gui, T element, ElementViewer<T> viewer, long frameCount) {
        viewer.draw(element, gui, frameCount);
    }

    private <T extends Element> void drawElements(GUI gui, List<T> elements, ElementViewer<T> viewer, long frameCount) {
        for (T element: elements)
            drawElement(gui, element, viewer, frameCount);
    }

    private <T extends Element> void drawElements(GUI gui, T[][] elements, ElementViewer<T> viewer, long frameCount) {
        for (T[] elementLine : elements) {
            for (T element : elementLine) {
                if (element != null)
                    drawElement(gui, element, viewer, frameCount);
            }
        }
    }
}
