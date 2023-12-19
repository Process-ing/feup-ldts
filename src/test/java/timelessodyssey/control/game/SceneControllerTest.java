package timelessodyssey.control.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import timelessodyssey.Game;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.game.elements.player.Player;
import timelessodyssey.model.game.scene.Scene;
import timelessodyssey.states.CreditsState;
import timelessodyssey.view.SpriteLoader;

import java.io.IOException;

public class SceneControllerTest {
    private Game game;
    private SceneController sceneController;
    private Scene scene;
    private PlayerController playerController;
    private ParticleController particleController;

    @BeforeEach
    public void setup() {
        this.game = Mockito.mock(Game.class);
        SpriteLoader spriteLoader = Mockito.mock(SpriteLoader.class);
        Mockito.when(game.getSpriteLoader()).thenReturn(spriteLoader);

        this.scene = Mockito.mock(Scene.class);
        Player player = Mockito.mock(Player.class);
        Mockito.when(scene.getPlayer()).thenReturn(player);

        this.playerController = Mockito.mock(PlayerController.class);
        this.particleController = Mockito.mock(ParticleController.class);

        this.sceneController = new SceneController(scene, playerController, particleController);
    }

    @Test
    public void stepWithoutQuit() throws IOException {
        GUI.Action action = GUI.Action.NONE;
        long frameCount = 0;
        Mockito.when(scene.isAtTransitionPosition()).thenReturn(false);

        sceneController.step(game, action, frameCount);
        Mockito.verify(playerController, Mockito.times(1))
                .step(game, action, frameCount);
        Mockito.verify(game, Mockito.times(0))
                .setState(Mockito.any());
        Mockito.verify(particleController, Mockito.times(1))
                .step(game, action, frameCount);
    }

    @Test
    public void stepWithQuit() throws IOException {
        GUI.Action action = GUI.Action.QUIT;
        long frameCount = 0;

        sceneController.step(game, action, frameCount);
        Mockito.verify(game, Mockito.times(1)).setState(null);
    }

    @Test
    public void stepWithSceneChangeNextLevel() throws IOException {
        GUI.Action action = GUI.Action.NONE;
        long frameCount = 0;
        Mockito.when(scene.isAtTransitionPosition()).thenReturn(true);
        Mockito.when(game.getNumberOfLevels()).thenReturn(2147483647);


        sceneController.step(game, action, frameCount);
        Mockito.verify(playerController, Mockito.times(1))
                .step(game, action, frameCount);
        Mockito.verify(game, Mockito.times(1))
                .setState(Mockito.any());
        Mockito.verify(particleController, Mockito.times(1))
                .step(game, action, frameCount);
    }

    @Test
    public void stepWithSceneChangeCredits() throws IOException {
        GUI.Action action = GUI.Action.NONE;
        long frameCount = 0;
        Mockito.when(scene.isAtTransitionPosition()).thenReturn(true);
        Mockito.when(game.getNumberOfLevels()).thenReturn(0);

        sceneController.step(game, action, frameCount);
        Mockito.verify(playerController, Mockito.times(1))
                .step(game, action, frameCount);
        Mockito.verify(game, Mockito.times(1))
                .setState(Mockito.any(CreditsState.class));
        Mockito.verify(particleController, Mockito.times(1))
                .step(game, action, frameCount);
    }
}
