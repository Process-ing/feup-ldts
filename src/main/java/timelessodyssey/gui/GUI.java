package timelessodyssey.gui;

import com.googlecode.lanterna.TextColor;

import java.io.IOException;

public interface GUI {
    enum Action { UP, RIGHT, DOWN, LEFT, NONE, QUIT, SELECT }

    void clear();

    void drawPixel(int x, int y, TextColor.RGB color);

    Action getNextAction() throws IOException;
    void clearAction();
    void refresh() throws IOException;
    void close() throws IOException;
}
