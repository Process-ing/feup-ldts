package timelessodyssey.view.elements;

import timelessodyssey.gui.GUI;

import java.io.IOException;

public interface ElementViewer<T> {
    void draw(T model, GUI gui) throws IOException;
}
