package timelessodyssey.view.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import timelessodyssey.gui.GUI;
import timelessodyssey.view.Sprite;
import timelessodyssey.view.SpriteLoader;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class LogoViewerTest {
    private SpriteLoader spriteLoader;
    private Sprite sprite;
    private GUI gui;

    @BeforeEach
    public void setup() throws IOException {
        this.spriteLoader = mock(SpriteLoader.class);
        this.sprite = mock(Sprite.class);
        this.gui = mock(GUI.class);

        when(spriteLoader.get("menu/logo.png")).thenReturn(sprite);
    }

    @Test
    public void draw() throws IOException {
        LogoViewer logoViewer = new LogoViewer(spriteLoader);
        int x = 20, y = 30;

        logoViewer.draw(gui, x, y);

        verify(sprite, times(1)).draw(gui, x, y);
    }
}
