package timelessodyssey.control.game;

import timelessodyssey.Game;
import timelessodyssey.control.Controller;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.credits.Credits;
import timelessodyssey.model.game.scene.Scene;
import timelessodyssey.model.game.scene.SceneBuilder;
import timelessodyssey.states.CreditsState;
import timelessodyssey.states.GameState;

import java.io.IOException;

import static timelessodyssey.Game.getNumberOfLevels;
import static timelessodyssey.gui.GUI.Action.QUIT;

public class SceneController extends Controller<Scene> {
    private final PlayerController playerController;
    private final ParticleController particleController;

    public SceneController(Scene scene, PlayerController playerController, ParticleController particleController) {
        super(scene);
        this.playerController = playerController;
        this.particleController = particleController;
    }

    @Override
    public void step(Game game, GUI.Action action, long frameCount) throws IOException {
        if (action == QUIT) {
            game.setState(null);
        } else {
            playerController.step(game, action, frameCount);
            if (getModel().isDying())
                getModel().getPlayer().setPosition(getModel().getStartingPosition());
            if (getModel().isAtTransitionPosition()) {
                if (getModel().getSceneCode() + 1 >= getNumberOfLevels())
                    game.setState(new CreditsState(new Credits(getModel().getPlayer().getStarCounter())));
                else
                    game.setState(new GameState(new SceneBuilder((getModel().getSceneCode() + 1)).createScene(getModel().getPlayer())));
            }
            getModel().updateStars();
            particleController.step(game, action, frameCount);
        }
    }
}
