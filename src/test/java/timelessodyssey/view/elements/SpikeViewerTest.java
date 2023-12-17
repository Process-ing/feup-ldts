package timelessodyssey.view.elements;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.game.elements.Spike;
import timelessodyssey.view.Sprite;
import timelessodyssey.view.SpriteLoader;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class SpikeViewerTest {
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
        when(spriteLoader.get("sprites/spikes/futuristic/Bottom_Spike.png")).thenReturn(sprite1);
        when(spriteLoader.get("sprites/spikes/cave/Bottom_Spike2.png")).thenReturn(sprite2);
        SpikeViewer spikeViewer = new SpikeViewer(spriteLoader);
        Spike spike1 = new Spike(10, 10, '^');
        Spike spike2 = new Spike(20, 0, '-');
        long frameCount = 0;

        spikeViewer.draw(spike1, gui, frameCount);
        spikeViewer.draw(spike2, gui, frameCount);

        verify(sprite1, times(1)).draw(gui, spike1.getPosition().x(), spike1.getPosition().y());
        verify(sprite2, times(1)).draw(gui, spike2.getPosition().x(), spike2.getPosition().y());
    }
}
