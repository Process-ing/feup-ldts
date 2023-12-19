package timelessodyssey.view;

import com.googlecode.lanterna.TextColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import timelessodyssey.gui.GUI;

import java.io.IOException;

public class SpriteTest {
    private GUI gui;

    @BeforeEach
    public void setup() {
        gui = Mockito.mock(GUI.class);
    }

    @Test
    public void drawNoTransparentPixels() throws IOException {
        Sprite sprite = new Sprite("sprites/test-sprite1.png");
        double x = 0, y = 0;

        sprite.draw(gui, x, y);

        Mockito.verify(gui, Mockito.times(1)).drawPixel(x, y, new TextColor.RGB(0, 0, 0));
        Mockito.verify(gui, Mockito.times(1)).drawPixel(x + 1, y, new TextColor.RGB(0, 0, 255));
        Mockito.verify(gui, Mockito.times(1)).drawPixel(x, y + 1, new TextColor.RGB(0, 255, 0));
        Mockito.verify(gui, Mockito.times(1)).drawPixel(x + 1, y + 1, new TextColor.RGB(255, 0, 0));

        x = 32;
        y = 16;

        sprite.draw(gui, x, y);

        Mockito.verify(gui, Mockito.times(1)).drawPixel(x, y, new TextColor.RGB(0, 0, 0));
        Mockito.verify(gui, Mockito.times(1)).drawPixel(x + 1, y, new TextColor.RGB(0, 0, 255));
        Mockito.verify(gui, Mockito.times(1)).drawPixel(x, y + 1, new TextColor.RGB(0, 255, 0));
        Mockito.verify(gui, Mockito.times(1)).drawPixel(x + 1, y + 1, new TextColor.RGB(255, 0, 0));
    }

    @Test
    public void drawWithTransparency() throws IOException {
        Sprite sprite = new Sprite("sprites/test-sprite2.png");
        int x = 0, y = 0;

        sprite.draw(gui, x, y);

        Mockito.verify(gui, Mockito.times(1)).drawPixel(x, y, new TextColor.RGB(0, 255, 255));
        Mockito.verify(gui, Mockito.times(0))
                .drawPixel(Mockito.eq(x + 1), Mockito.eq(y), Mockito.any(TextColor.RGB.class));
        Mockito.verify(gui, Mockito.times(1)).drawPixel(x + 2, y, new TextColor.RGB(255, 0, 255));
        Mockito.verify(gui, Mockito.times(0))
                .drawPixel(Mockito.eq(x), Mockito.eq(y + 1), Mockito.any(TextColor.RGB.class));
        Mockito.verify(gui, Mockito.times(1)).drawPixel(x + 1, y + 1, new TextColor.RGB(255, 255, 255));
        Mockito.verify(gui, Mockito.times(0))
                .drawPixel(Mockito.eq(x + 2), Mockito.eq(y + 1), Mockito.any(TextColor.RGB.class));
        Mockito.verify(gui, Mockito.times(1)).drawPixel(x, y + 2, new TextColor.RGB(255, 255, 0));
        Mockito.verify(gui, Mockito.times(0))
                .drawPixel(Mockito.eq(x + 1), Mockito.eq(y + 2), Mockito.any(TextColor.RGB.class));
        Mockito.verify(gui, Mockito.times(1)).drawPixel(x + 2, y + 2, new TextColor.RGB(0, 0, 0));
    }
}
