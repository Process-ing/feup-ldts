package timelessodyssey.view.text;

import com.googlecode.lanterna.TextColor;
import timelessodyssey.gui.GUI;

public interface TextViewer {
    void draw(char character, int x, int y, TextColor.RGB foregroundColor, GUI gui);
    void draw(String string, int x, int y, TextColor.RGB foregroundColor, GUI gui);
}
