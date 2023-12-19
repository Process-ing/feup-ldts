package timelessodyssey.view.elements;

import com.googlecode.lanterna.TextColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.Vector;
import timelessodyssey.model.game.elements.particles.Particle;
import timelessodyssey.model.game.scene.Scene;

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
        double x = 72.0, y = 88.0, vx = 0.2, vy = 0.5;
        long frameCount = 0;
        TextColor color = TextColor.ANSI.BLUE;
        Particle particle = new Particle(x, y, size, color, new Vector(vx, vy)) {
            @Override
            public Vector move(Scene scene) {
                return null;
            }
        };

        particleViewer.draw(particle, gui, frameCount);

        verify(gui).drawRectangle(x, y, size, size, color);
    }
}
