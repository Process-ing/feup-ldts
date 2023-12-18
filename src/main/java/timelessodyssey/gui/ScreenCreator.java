package timelessodyssey.gui;

import com.googlecode.lanterna.screen.Screen;

import java.awt.*;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URISyntaxException;

public interface ScreenCreator {
    Screen createScreen(ResizableGUI.Resolution resolution, String title, KeyListener keyListener)
            throws IOException, URISyntaxException, FontFormatException;

    int getWidth();
    int getHeight();
}
