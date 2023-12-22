package timelessodyssey.states;

import timelessodyssey.Game;
import timelessodyssey.control.Controller;
import timelessodyssey.gui.GUI;
import timelessodyssey.gui.ResizableGUI;
import timelessodyssey.view.SpriteLoader;
import timelessodyssey.view.ViewerProvider;
import timelessodyssey.view.screens.ScreenViewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public abstract class State<T> {
    private final T model;
    protected final Controller<T> controller;
    protected final ScreenViewer<T> screenViewer;

    public State(T model, SpriteLoader spriteLoader) throws IOException {
        this.model = model;
        this.screenViewer = createScreenViewer(new ViewerProvider(spriteLoader));
        this.controller = createController();
    }

    protected abstract ScreenViewer<T> createScreenViewer(ViewerProvider viewerProvider);
    protected abstract Controller<T> createController();
    protected abstract boolean allowArrowSpam();

    public T getModel() {
        return model;
    }

    public void step(Game game, ResizableGUI gui, long frameCount) throws IOException, URISyntaxException, FontFormatException {
        game.setKeySpam(allowArrowSpam());
        GUI.Action action = gui.getNextAction();
        controller.step(game, action, frameCount);
        screenViewer.draw(gui, frameCount);
    }
}
