package timelessodyssey.view.screens;

import timelessodyssey.gui.GUI;

public abstract class ScreenViewer<T> {
    final protected T model;

    public ScreenViewer(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public abstract void draw(GUI gui);
}
