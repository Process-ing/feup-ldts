package timelessodyssey.states;

import timelessodyssey.Game;
import timelessodyssey.control.Controller;
import timelessodyssey.gui.GUI;
import timelessodyssey.view.SpriteLoader;
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
        this.screenViewer = createScreenViewer(spriteLoader);
        this.controller = createController();
    }

    protected abstract ScreenViewer<T> createScreenViewer(SpriteLoader spriteLoader) throws IOException;
    protected abstract Controller<T> createController();

    public T getModel() {
        return model;
    }

    public void step(Game game, GUI gui, long frameCount) throws IOException, URISyntaxException, FontFormatException {
        GUI.Action action = gui.getNextAction();
        controller.step(game, action, frameCount);
        screenViewer.draw(gui, frameCount);
    }
}
