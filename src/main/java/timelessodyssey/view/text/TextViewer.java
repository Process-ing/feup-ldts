package timelessodyssey.view.text;

import com.googlecode.lanterna.TextColor;
import timelessodyssey.gui.GUI;

public interface TextViewer {
    void draw(char character, double x, double y, TextColor foregroundColor, GUI gui);
    void draw(String string, double x, double y, TextColor foregroundColor, GUI gui);
}
