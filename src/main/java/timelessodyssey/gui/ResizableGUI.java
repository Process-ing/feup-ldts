package timelessodyssey.gui;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public interface ResizableGUI extends GUI {
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

    Resolution getResolution();
    void setResolution(Resolution resolution) throws IOException, URISyntaxException, FontFormatException;
}
