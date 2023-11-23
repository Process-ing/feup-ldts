package timelessodyssey.control;

import timelessodyssey.Game;
import timelessodyssey.gui.GUI;

public abstract class Controller<T> {

    private final T model;

    public Controller(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public abstract void step(Game game, GUI.Action action, long time);
}
