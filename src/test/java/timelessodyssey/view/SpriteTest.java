package timelessodyssey.view;

import com.googlecode.lanterna.TextColor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.Position;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

public class SpriteTest {
    GUI gui;

    @BeforeEach
    public void setup() {
        gui = Mockito.mock(GUI.class);
    }

    private boolean equalImages(BufferedImage image1, BufferedImage image2) {
        int[] colors1 = image1.getRGB(0, 0, image1.getWidth(), image1.getHeight(), null, 0, image1.getWidth());
        int[] colors2 = image2.getRGB(0, 0, image2.getWidth(), image2.getHeight(), null, 0, image2.getWidth());
        return Arrays.equals(colors1, colors2);
    }

    @Test
    public void constructor() throws IOException {
        Sprite sprite1 = new Sprite("sprites/test-sprite1.png");
        URL resource1 = getClass().getClassLoader().getResource("sprites/test-sprite1.png");
        Assertions.assertNotNull(resource1);
        BufferedImage image1 = ImageIO.read(new File(resource1.getFile()));

        Assertions.assertTrue(equalImages(image1, sprite1.getImage()));

        Sprite sprite2 = new Sprite("sprites/test-sprite2.png");
        URL resource2 = getClass().getClassLoader().getResource("sprites/test-sprite2.png");
        Assertions.assertNotNull(resource2);
        BufferedImage image2 = ImageIO.read(new File(resource2.getFile()));

        Assertions.assertTrue(equalImages(image2, sprite2.getImage()));
    }

    @Test
    public void drawNoTransparentPixels() throws IOException {
        Sprite sprite = new Sprite("sprites/test-sprite1.png");
        int x = 0, y = 0;

        sprite.draw(gui, x, y);

        Mockito.verify(gui, Mockito.times(1)).drawPixel(new Position(x, y), new TextColor.RGB(0, 0, 0));
        Mockito.verify(gui, Mockito.times(1)).drawPixel(new Position(x + 1, y), new TextColor.RGB(0, 0, 255));
        Mockito.verify(gui, Mockito.times(1)).drawPixel(new Position(x, y + 1), new TextColor.RGB(0, 255, 0));
        Mockito.verify(gui, Mockito.times(1)).drawPixel(new Position(x + 1, y + 1), new TextColor.RGB(255, 0, 0));

        x = 32;
        y = 16;

        sprite.draw(gui, x, y);

        Mockito.verify(gui, Mockito.times(1)).drawPixel(new Position(x, y), new TextColor.RGB(0, 0, 0));
        Mockito.verify(gui, Mockito.times(1)).drawPixel(new Position(x + 1, y), new TextColor.RGB(0, 0, 255));
        Mockito.verify(gui, Mockito.times(1)).drawPixel(new Position(x, y + 1), new TextColor.RGB(0, 255, 0));
        Mockito.verify(gui, Mockito.times(1)).drawPixel(new Position(x + 1, y + 1), new TextColor.RGB(255, 0, 0));
    }

    @Test
    public void drawWithTransparency() throws IOException {
        Sprite sprite = new Sprite("sprites/test-sprite2.png");
        int x = 0, y = 0;

        sprite.draw(gui, x, y);

        Mockito.verify(gui, Mockito.times(1)).drawPixel(new Position(x, y), new TextColor.RGB(0, 255, 255));
        Mockito.verify(gui, Mockito.times(0))
                .drawPixel(new Position(Mockito.eq(x + 1), Mockito.eq(y)), Mockito.any(TextColor.RGB.class));
        Mockito.verify(gui, Mockito.times(1)).drawPixel(new Position(x + 2, y), new TextColor.RGB(255, 0, 255));
        Mockito.verify(gui, Mockito.times(0))
                .drawPixel(new Position(Mockito.eq(x), Mockito.eq(y + 1)), Mockito.any(TextColor.RGB.class));
        Mockito.verify(gui, Mockito.times(1)).drawPixel(new Position(x + 1, y + 1), new TextColor.RGB(255, 255, 255));
        Mockito.verify(gui, Mockito.times(0))
                .drawPixel(new Position(Mockito.eq(x + 2), Mockito.eq(y + 1)), Mockito.any(TextColor.RGB.class));
        Mockito.verify(gui, Mockito.times(1)).drawPixel(new Position(x, y + 2), new TextColor.RGB(255, 255, 0));
        Mockito.verify(gui, Mockito.times(0))
                .drawPixel(new Position(Mockito.eq(x + 1), Mockito.eq(y + 2)), Mockito.any(TextColor.RGB.class));
        Mockito.verify(gui, Mockito.times(1)).drawPixel(new Position(x + 2, y + 2), new TextColor.RGB(0, 0, 0));
    }
}
