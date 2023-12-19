package timelessodyssey.control.credits;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import timelessodyssey.Game;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.credits.Credits;
import timelessodyssey.view.SpriteLoader;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.Mockito.when;

public class CreditsControllerTest {
    private Game game;
    private Credits credits;
    private SpriteLoader spriteLoader;
    private CreditsController creditsController;

    @BeforeEach
    public void setup() {
        this.game = Mockito.mock(Game.class);
        this.credits = Mockito.mock(Credits.class);
        this.spriteLoader = Mockito.mock(SpriteLoader.class);
        when(game.getSpriteLoader()).thenReturn(spriteLoader);

        this.creditsController = new CreditsController(credits);
    }

    @Test
    public void stepWithoutQuit() throws IOException, URISyntaxException, FontFormatException {
        GUI.Action action = GUI.Action.NONE;
        long frameCount = 0;

        creditsController.step(game, action, frameCount);
        Mockito.verify(game, Mockito.times(0))
                .setState(Mockito.any());
    }

    @Test
    public void stepWithQuit() throws IOException, URISyntaxException, FontFormatException {
        GUI.Action action = GUI.Action.QUIT;
        long frameCount = 0;

        creditsController.step(game, action, frameCount);
        Mockito.verify(game, Mockito.times(1))
                .setState(Mockito.any());
    }
}
