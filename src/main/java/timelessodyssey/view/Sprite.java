package timelessodyssey.view;

import com.googlecode.lanterna.TextColor;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.Position;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Sprite {
    private final BufferedImage image;

    public Sprite(String filepath) throws IOException {
        URL resource = getClass().getClassLoader().getResource(filepath);
        image = ImageIO.read(new File(resource.getFile()));
    }
    public BufferedImage getImage() {
        return image;
    }

    public void draw(GUI gui, int x, int y) {
        for (int dx = 0; dx < image.getWidth(); dx++) {
            for (int dy = 0; dy < image.getHeight(); dy++) {
                int ARGB = image.getRGB(dx, dy);
                if (getTransparency(ARGB) == 0)
                    continue;
                gui.drawPixel(new Position(x + dx, y + dy), getRGB(ARGB));
            }
        }
    }

    private int getTransparency(int ARGB) {
        return ARGB >> 24;
    }

    private TextColor getRGB(int ARGB) {
        int red = ARGB >> 16 & 0xFF;
        int green = ARGB >> 8 & 0xFF;
        int blue = ARGB & 0xFF;
        return new TextColor.RGB(red, green, blue);
    }
}
