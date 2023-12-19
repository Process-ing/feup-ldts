package timelessodyssey.view.elements;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.game.elements.player.DeadState;
import timelessodyssey.model.game.elements.player.FallingState;
import timelessodyssey.model.game.elements.player.Player;
import timelessodyssey.model.game.elements.player.RunningState;
import timelessodyssey.model.game.scene.Scene;
import timelessodyssey.view.Sprite;
import timelessodyssey.view.SpriteLoader;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class PlayerViewerTest {
    private SpriteLoader spriteLoader;
    private Scene scene;
    private GUI gui;

    @BeforeEach
    public void setup() {
        this.spriteLoader = mock(SpriteLoader.class);
        this.scene = mock(Scene.class);
        this.gui = mock(GUI.class);
    }

    @Test
    public void draw() throws IOException {
        Sprite sprite1 = mock(Sprite.class);
        Sprite sprite2 = mock(Sprite.class);
        Sprite sprite3 = mock(Sprite.class);
        Player player = new Player(20, 30, scene);

        when(spriteLoader.get("sprites/player/player-left-7.png")).thenReturn(sprite1);
        when(spriteLoader.get("sprites/player/player-right-4.png")).thenReturn(sprite2);
        when(spriteLoader.get("sprites/player/player-right-5.png")).thenReturn(sprite3);

        PlayerViewer playerViewer = new PlayerViewer(spriteLoader);
        long frameCount = 1000;
        int deathDuration = 50;


        player.setFacingRight(false);
        player.setState(new FallingState(player));
        playerViewer.draw(player, gui, frameCount);

        verify(sprite1, times(1))
                .draw(gui, player.getPosition().x(), player.getPosition().y());

        player.setFacingRight(true);
        player.setState(new RunningState(player));
        playerViewer.draw(player, gui, frameCount);

        verify(sprite2, times(1))
                .draw(gui, player.getPosition().x() - 2, player.getPosition().y());

        frameCount++;
        playerViewer.draw(player, gui, frameCount);

        verify(sprite3, times(1))
                .draw(gui, player.getPosition().x() - 2, player.getPosition().y());

        player.setState(new DeadState(player, deathDuration));
        playerViewer.draw(player, gui, frameCount);

        verifyNoMoreInteractions(sprite1);
        verifyNoMoreInteractions(sprite2);
        verifyNoMoreInteractions(sprite3);
    }
}
