package timelessodyssey.gui;

import com.googlecode.lanterna.TextColor;

import java.io.IOException;

public interface GUI {
    enum Action { UP, RIGHT, DOWN, LEFT, NONE, QUIT, SELECT }

    int getWidth();
    int getHeight();

    void drawPixel(int x, int y, TextColor color);
    void drawRectangle(int x, int y, int width, int height, TextColor color);
    void clear();
    void refresh() throws IOException;
    void close() throws IOException;

    Action getNextAction() throws IOException;
}
