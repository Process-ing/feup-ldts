package timelessodyssey.view.screens;

import com.googlecode.lanterna.TextColor;
import timelessodyssey.gui.GUI;
import timelessodyssey.gui.ResizableGUI;

import java.io.IOException;

public abstract class ScreenViewer<T> {
    private final T model;

    public ScreenViewer(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public abstract void draw(ResizableGUI gui, long frameCount) throws IOException;

    protected void drawBackgroundAndFrame(GUI gui, TextColor frameColor, TextColor backgroundColor) {
        gui.drawRectangle(0, 0, gui.getWidth(), 1, frameColor);
        gui.drawRectangle(0, gui.getHeight() - 1, gui.getWidth(), 1, frameColor);
        gui.drawRectangle(0, 1, 1, gui.getHeight() - 2, frameColor);
        gui.drawRectangle(gui.getWidth() - 1, 1, 1, gui.getHeight() - 2, frameColor);
        gui.drawRectangle(1, 1, gui.getWidth() - 2, gui.getHeight() - 2, backgroundColor);
    }
}
