package timelessodyssey.gui;

import com.googlecode.lanterna.screen.Screen;

import java.awt.*;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URISyntaxException;

public interface ScreenCreator {
    enum Resolution {
        WXGA(1280, 720),
        FHD(1920, 1080),
        WQHD(2560, 1440),
        FOUR_K(3840, 2160);

        private final int width;
        private final int height;

        Resolution(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }
    }

    Screen createScreen(Resolution resolution, String title, KeyListener keyListener)
            throws IOException, URISyntaxException, FontFormatException;

    int getWidth();
    int getHeight();
}
