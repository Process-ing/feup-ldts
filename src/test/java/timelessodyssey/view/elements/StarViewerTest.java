package timelessodyssey.view.elements;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.game.elements.Star;
import timelessodyssey.view.Sprite;
import timelessodyssey.view.SpriteLoader;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class StarViewerTest {
    private SpriteLoader spriteLoader;
    private Sprite sprite;
    private GUI gui;

    @BeforeEach
    public void setup() throws IOException {
        this.spriteLoader = mock(SpriteLoader.class);
        this.sprite = mock(Sprite.class);
        this.gui = mock(GUI.class);

        when(spriteLoader.get("sprites/star.png")).thenReturn(sprite);
    }

    @Test
    public void draw() throws IOException {
        StarViewer starViewer = new StarViewer(spriteLoader);
        Star star = new Star(56, 8);
        long frameCount = 0;

        starViewer.draw(star, gui, frameCount);

        verify(sprite, times(1)).draw(gui, star.getPosition().x(), star.getPosition().y());
    }
}
