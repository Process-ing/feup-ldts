package timelessodyssey.control;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import timelessodyssey.Game;
import timelessodyssey.control.game.PlayerController;
import timelessodyssey.control.game.SceneController;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.game.scene.Scene;

import java.io.IOException;

public class SceneControllerTest {
    private Game game;
    private SceneController sceneController;
    private Scene scene;
    private PlayerController playerController;

    @BeforeEach
    public void setup() {
        this.game = Mockito.mock(Game.class);
        this.scene = Mockito.mock(Scene.class);
        this.playerController = Mockito.mock(PlayerController.class);

        this.sceneController = new SceneController(scene, playerController);
    }

    @Test
    public void stepWithoutQuit() throws IOException {
        GUI.Action action = GUI.Action.NONE;
        long time = 0;
        Mockito.when(scene.isAtTransitionPosition()).thenReturn(false);

        sceneController.step(game, action, time);
        Mockito.verify(playerController, Mockito.times(1))
                .step(game, action, time);
        Mockito.verify(game, Mockito.times(0))
                .setState(Mockito.any());
    }

    @Test
    public void stepWithQuit() throws IOException {
        GUI.Action action = GUI.Action.QUIT;
        long time = 0;

        sceneController.step(game, action, time);
        Mockito.verify(game, Mockito.times(1)).setState(null);
    }

    @Test
    public void stepWithSceneChange() throws IOException {
        GUI.Action action = GUI.Action.NONE;
        long time = 0;
        Mockito.when(scene.isAtTransitionPosition()).thenReturn(true);

        sceneController.step(game, action, time);
        Mockito.verify(playerController, Mockito.times(1))
                .step(game, action, time);
        Mockito.verify(game, Mockito.times(1))
                .setState(Mockito.any());
    }
}
