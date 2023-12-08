package timelessodyssey.view.elements;

import com.googlecode.lanterna.TextColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.game.elements.Particle;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ParticleViewerTest {
    private ParticleViewer particleViewer;
    private GUI gui;

    @BeforeEach
    public void setup() {
        this.gui = mock(GUI.class);
        this.particleViewer = new ParticleViewer();
    }

    @Test
    public void draw() {
        int size = 4;
        double x = 72.0, y = 88.0, velocity = 0.2;
        TextColor color = TextColor.ANSI.BLUE;
        Particle particle = new Particle(x, y, size, color, velocity);

        particleViewer.draw(particle, gui);

        verify(gui).drawRectangle(x, y, size, size, color);
    }
}
