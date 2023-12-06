package timelessodyssey.states;

import timelessodyssey.Game;
import timelessodyssey.control.Controller;
import timelessodyssey.gui.GUI;
import timelessodyssey.view.screens.ScreenViewer;

import java.io.IOException;

public abstract class State<T> {
    private final T model;
    protected Controller<T> controller;
    protected ScreenViewer<T> screenViewer;

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

    public abstract void step(Game game, GUI gui, long time) throws IOException;
}
