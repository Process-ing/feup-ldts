package timelessodyssey.gui;

public interface GUI {
    void clear();
    void drawPixel(int x, int y, String color);
    void refresh();
    void close();
}
