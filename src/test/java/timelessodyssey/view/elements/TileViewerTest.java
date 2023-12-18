package timelessodyssey.view.elements;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.game.elements.Tile;
import timelessodyssey.view.Sprite;
import timelessodyssey.view.SpriteLoader;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class TileViewerTest {
    private SpriteLoader spriteLoader;
    private GUI gui;

    @BeforeEach
    public void setup() {
        this.spriteLoader = mock(SpriteLoader.class);
        this.gui = mock(GUI.class);
    }

    @Test
    public void draw() throws IOException {
        Sprite sprite1 = mock(Sprite.class);
        Sprite sprite2 = mock(Sprite.class);
        when(spriteLoader.get("sprites/tiles/futuristic/Gray.png")).thenReturn(sprite1);
        when(spriteLoader.get("sprites/tiles/goals/Cake.png")).thenReturn(sprite2);
        TileViewer tileViewer = new TileViewer(spriteLoader);
        Tile tile1 = new Tile(16, 24, 'G');
        Tile tile2 = new Tile(40, 32, 'W');
        long frameCount = 0;

        tileViewer.draw(tile1, gui, frameCount);
        tileViewer.draw(tile2, gui, frameCount);

        verify(sprite1, times(1)).draw(gui, tile1.getPosition().x(), tile1.getPosition().y());
        verify(sprite2, times(1)).draw(gui, tile2.getPosition().x(), tile2.getPosition().y());
    }
}
