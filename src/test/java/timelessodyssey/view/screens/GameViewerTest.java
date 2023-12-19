package timelessodyssey.view.screens;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import timelessodyssey.gui.ResizableGUI;
import timelessodyssey.model.game.elements.Spike;
import timelessodyssey.model.game.elements.Star;
import timelessodyssey.model.game.elements.Tile;
import timelessodyssey.model.game.elements.particles.DeathParticle;
import timelessodyssey.model.game.elements.particles.Snow;
import timelessodyssey.model.game.elements.player.Player;
import timelessodyssey.model.game.scene.Scene;
import timelessodyssey.view.ViewerProvider;
import timelessodyssey.view.elements.*;

import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.*;

public class GameViewerTest {
    private Scene scene;
    private ViewerProvider viewerProvider;
    private ParticleViewer particleViewer;
    private PlayerViewer playerViewer;
    private SpikeViewer spikeViewer;
    private StarViewer starViewer;
    private TileViewer tileViewer;
    private ResizableGUI gui;

    @BeforeEach
    public void setup() {
        this.scene = new Scene(2, 2, 0);
        scene.setPlayer(new Player(0, 0, scene));
        scene.setTiles(new Tile[][] { { null, null },
                { new Tile(0, 8, 'T') }});
        scene.setGoals(new Tile[][] { { new Tile(0, 8, 'W'), null },
                { null, null }});
        scene.setSpikes(new Spike[][] { { null, null },
                { null, new Spike(8, 8, '^') }});
        scene.setStars(new Star[][] { { null, new Star(8, 0) }, { null, null }});
        scene.setSnow(List.of(new Snow(0, 0, 2, 2, 0)));
        scene.setDeathParticles(List.of(new DeathParticle(0, 8, 2, 0)));

        this.viewerProvider = mock(ViewerProvider.class);
        this.particleViewer = mock(ParticleViewer.class);
        this.playerViewer = mock(PlayerViewer.class);
        this.spikeViewer = mock(SpikeViewer.class);
        this.starViewer = mock(StarViewer.class);
        this.tileViewer = mock(TileViewer.class);
        this.gui = mock(ResizableGUI.class);

        when(viewerProvider.getParticleViewer()).thenReturn(particleViewer);
        when(viewerProvider.getPlayerViewer()).thenReturn(playerViewer);
        when(viewerProvider.getSpikeViewer()).thenReturn(spikeViewer);
        when(viewerProvider.getStarViewer()).thenReturn(starViewer);
        when(viewerProvider.getTileViewer()).thenReturn(tileViewer);
    }

    @Test
    public void draw() throws IOException {
        GameViewer gameViewer = new GameViewer(scene, viewerProvider);
        long frameCount = 100;
        int screenWidth = 16, screenHeight = 16;
        when(gui.getWidth()).thenReturn(screenWidth);
        when(gui.getHeight()).thenReturn(screenHeight);

        gameViewer.draw(gui, frameCount);

        verify(gui, times(1)).clear();
        verify(gui, times(1))
                .drawRectangle(0, 0, screenWidth, screenHeight, GameViewer.backgroundColor);
        verify(playerViewer, times(1)).draw(scene.getPlayer(), gui, frameCount);
        verify(particleViewer, times(1)).draw(scene.getSnow().get(0), gui, frameCount);
        verify(particleViewer, times(1)).draw(scene.getDeathParticles().get(0), gui, frameCount);
        verify(tileViewer, times(1)).draw(scene.getTiles()[1][0], gui, frameCount);
        verify(tileViewer, times(1)).draw(scene.getGoals()[0][0], gui, frameCount);
        verify(spikeViewer, times(1)).draw(scene.getSpikes()[1][1], gui, frameCount);
        verify(starViewer, times(1)).draw(scene.getStars()[0][1], gui, frameCount);
        verify(gui, times(1)).refresh();
    }
}
