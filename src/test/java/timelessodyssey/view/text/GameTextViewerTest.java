package timelessodyssey.view.text;

import com.googlecode.lanterna.TextColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import timelessodyssey.gui.GUI;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static timelessodyssey.view.text.GameTextViewer.*;

record RectangleSize(int width, int height) {}

public class GameTextViewerTest {
    private static final Color PIXEL_COLOR = Color.BLACK;
    private static final Color RECTANGLE_COLOR = Color.BLUE;
    private static final Color PLACEHOLDER_COLOR = Color.DARK_GRAY;

    private static final int SCREEN_WIDTH = 40;
    private static final int SCREEN_HEIGHT = 30;

    private GUI gui;
    private TextColor foregroundColor;

    @BeforeEach
    public void setup() {
        gui = mock(GUI.class);
        foregroundColor = mock(TextColor.class);
    }

    private void checkPixels(String filepath, GUI gui, TextColor foregroundColor) throws IOException {
        URL resource = getClass().getClassLoader().getResource(filepath);
        assertNotNull(resource);
        BufferedImage image = ImageIO.read(new File(resource.getFile()));

        for (int y = 0; y < SCREEN_HEIGHT; y++) {
            for (int x = 0; x < SCREEN_WIDTH; x++) {
                if (image.getRGB(x, y) == PIXEL_COLOR.getRGB()) {
                    verify(gui, times(1)).drawPixel(x, y, foregroundColor);
                } else if (image.getRGB(x, y) == RECTANGLE_COLOR.getRGB()) {
                    RectangleSize size = getRectangleSize(x, y, image);
                    verify(gui, times(1))
                        .drawRectangle(x, y, size.width(), size.height(), foregroundColor);
                } else {
                    verify(gui, times(0)).drawPixel(eq(x), eq(y), any(TextColor.class));
                }
            }
        }
    }

    private RectangleSize getRectangleSize(int x, int y, BufferedImage image) {
        int width = 0, height = 0;
        do {
            width++;
        } while (x + width < image.getWidth() && image.getRGB(x + width, y) == PLACEHOLDER_COLOR.getRGB());
        do {
            height++;
        } while (y + height < image.getHeight() && image.getRGB(x, y + height) == PLACEHOLDER_COLOR.getRGB());
        return new RectangleSize(width, height);
    }

    @Test
    public void checkValues() {
        assertEquals(3, getCharWidth());
        assertEquals(5, getCharHeight());
        assertEquals(1, getSpacing());
    }

    @Test
    public void drawKnownCharacter() throws IOException {
        double x = 0, y = 0;
        char character = 'A';
        String text_image = "text/test-text1.png";
        TextViewer textViewer = new GameTextViewer();

        textViewer.draw(character, x, y, foregroundColor, gui);

        checkPixels(text_image, gui, foregroundColor);
    }

    @Test
    public void drawKnownCharacterOffset() throws IOException {
        double x = 32, y = 16;
        char character = '$';
        String text_image = "text/test-text2.png";
        TextViewer textViewer = new GameTextViewer();

        textViewer.draw(character, x, y, foregroundColor, gui);

        checkPixels(text_image, gui, foregroundColor);
    }

    @Test
    public void drawUnknownCharacter() throws IOException {
        double x = 15, y = 25;
        char character = '&';
        String text_image = "text/test-text3.png";
        TextViewer textViewer = new GameTextViewer();

        textViewer.draw(character, x, y, foregroundColor, gui);

        checkPixels(text_image, gui, foregroundColor);
    }

    @Test
    public void drawString() throws IOException {
        double x = 5, y = 14;
        String string = "+Foo\nBar";
        String text_image = "text/test-text4.png";
        TextViewer textViewer = new GameTextViewer();

        textViewer.draw(string, x, y, foregroundColor, gui);

        checkPixels(text_image, gui, foregroundColor);
    }
}
