package timelessodyssey.view.screens;

import timelessodyssey.gui.GUI;
import timelessodyssey.view.Model;

public abstract class ScreenViewer<T extends Model> {
    final protected GUI gui;
    final protected T model;

    public ScreenViewer(GUI gui, T model) {
        this.gui = gui;
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public abstract void draw();
}
