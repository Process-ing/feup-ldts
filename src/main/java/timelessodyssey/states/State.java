package timelessodyssey.states;

import timelessodyssey.Game;
import timelessodyssey.control.Controller;
import timelessodyssey.gui.GUI;
import timelessodyssey.view.screens.ScreenViewer;

import java.io.IOException;

public abstract class State<T> {
    protected final T model;
    protected final Controller<T> controller;
    protected final ScreenViewer<T> screenViewer;

    public State(T model) {
        this.model = model;
        this.screenViewer = getScreenViewer();
        this.controller = getController();
    }

    protected abstract ScreenViewer<T> getScreenViewer();
    protected abstract Controller<T> getController();

    public T getModel() {
        return model;
    }

    public void step(Game game, GUI gui, long time) throws IOException {
        GUI.Action action = gui.getNextAction();
        controller.step(game, action, time);
        screenViewer.draw(gui);
    }
}
