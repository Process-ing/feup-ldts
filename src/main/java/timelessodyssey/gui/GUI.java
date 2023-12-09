package timelessodyssey.gui;

import com.googlecode.lanterna.TextColor;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public interface GUI {
    enum Action { UP, RIGHT, DOWN, LEFT, NONE, QUIT, SELECT, JUMP }

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

    int getWidth();
    int getHeight();
    Resolution getResolution();
    void setResolution(Resolution resolution) throws IOException, URISyntaxException, FontFormatException;
    void drawPixel(double x, double y, TextColor color);
    void drawRectangle(double x, double y, int width, int height, TextColor color);
    void clear();
    Action getNextAction() throws IOException;
    void refresh() throws IOException;
    void close() throws IOException;
}
