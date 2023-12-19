package timelessodyssey.view.elements;

import timelessodyssey.gui.GUI;


public interface ElementViewer<T> {
    void draw(T model, GUI gui, long frameCount);
}
