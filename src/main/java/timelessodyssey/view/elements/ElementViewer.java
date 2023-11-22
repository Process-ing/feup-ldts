package timelessodyssey.view.elements;

import timelessodyssey.gui.GUI;

public abstract class ElementViewer<T> {
    final protected GUI gui;

    public ElementViewer(GUI gui) {
        this.gui = gui;
    }

    public abstract void draw(T model);
}
