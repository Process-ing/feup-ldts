package timelessodyssey.states;

import timelessodyssey.Game;
import timelessodyssey.control.Controller;
import timelessodyssey.gui.GUI;
import timelessodyssey.view.screens.ScreenViewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public abstract class State<T> {
    private final T model;
    protected final Controller<T> controller;
    protected final ScreenViewer<T> screenViewer;

    public State(T model) throws IOException {
        this.model = model;
        this.screenViewer = createScreenViewer();
        this.controller = createController();
    }

    protected abstract ScreenViewer<T> createScreenViewer() throws IOException;
    protected abstract Controller<T> createController();

    public T getModel() {
        return model;
    }

    public abstract void step(Game game, GUI gui, double time) throws IOException, URISyntaxException, FontFormatException;
}
