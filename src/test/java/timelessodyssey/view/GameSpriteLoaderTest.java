package timelessodyssey.view;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class GameSpriteLoaderTest {
    @Test
    public void get() throws IOException {
        GameSpriteLoader spriteLoader = new GameSpriteLoader();
        String filepath1 = "sprites/test-sprite1.png";
        URL resource1 = getClass().getClassLoader().getResource("sprites/test-sprite1.png");
        assertNotNull(resource1);
        BufferedImage image1 = ImageIO.read(new File(resource1.getFile()));

        String filepath2 = "sprites/test-sprite2.png";
        URL resource2 = getClass().getClassLoader().getResource("sprites/test-sprite2.png");
        assertNotNull(resource2);
        BufferedImage image2 = ImageIO.read(new File(resource2.getFile()));

        Sprite sprite1 = spriteLoader.get(filepath1);
        Sprite sprite2 = spriteLoader.get(filepath2);
        Sprite sprite3 = spriteLoader.get(filepath1);

        assertTrue(equalImages(image1, sprite1.getImage()));
        assertTrue(equalImages(image2, sprite2.getImage()));
        assertSame(sprite1, sprite3);
    }

    private boolean equalImages(BufferedImage image1, BufferedImage image2) {
        int[] colors1 = image1.getRGB(0, 0, image1.getWidth(), image1.getHeight(), null, 0, image1.getWidth());
        int[] colors2 = image2.getRGB(0, 0, image2.getWidth(), image2.getHeight(), null, 0, image2.getWidth());
        return Arrays.equals(colors1, colors2);
    }
}
