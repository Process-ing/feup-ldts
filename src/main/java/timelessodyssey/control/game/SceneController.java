package timelessodyssey.control.game;

import timelessodyssey.Game;
import timelessodyssey.control.Controller;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.game.scene.Scene;
import timelessodyssey.model.game.scene.SceneBuilder;
import timelessodyssey.states.GameState;

import java.io.IOException;

import static timelessodyssey.gui.GUI.Action.QUIT;

public class SceneController extends Controller<Scene> {
    private final PlayerController playerController;

    public SceneController(Scene scene, PlayerController playerController) {
        super(scene);
        this.playerController = playerController;
    }

    @Override
    public void step(Game game, GUI.Action action, double time) throws IOException {
        if (action == QUIT) {
            game.setState(null);
        } else {
            playerController.step(game, action, time);
            if (getModel().isAtTransitionPosition())
                game.setState(new GameState(new SceneBuilder((getModel().getSceneCode() + 1) % 3).createScene()));
        }
    }
}
