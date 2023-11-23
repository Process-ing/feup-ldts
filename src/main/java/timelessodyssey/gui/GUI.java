package timelessodyssey.gui;

import java.io.IOException;

public interface GUI {
    enum Action { UP, RIGHT, DOWN, LEFT, NONE, QUIT, SELECT };

    void clear();
    void drawPixel(int x, int y, String color);
    Action getAction() throws IOException;
    void refresh() throws IOException;
    void close() throws IOException;
}
