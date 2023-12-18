package timelessodyssey.gui;

import com.googlecode.lanterna.TextColor;

import java.io.IOException;

public interface GUI {
    enum Action { UP, RIGHT, DOWN, LEFT, NONE, QUIT, SELECT, JUMP, DASH }

    int getWidth();
    int getHeight();
    void drawPixel(double x, double y, TextColor color);
    void drawRectangle(double x, double y, int width, int height, TextColor color);
    void clear();
    Action getNextAction();
    void refresh() throws IOException;
    void close() throws IOException;
}
