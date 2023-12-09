package timelessodyssey.control.game;

import com.sun.tools.javac.Main;
import timelessodyssey.Game;
import timelessodyssey.control.Controller;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.game.scene.Scene;
import timelessodyssey.model.game.scene.SceneBuilder;
import timelessodyssey.model.menu.MainMenu;
import timelessodyssey.states.GameState;
import timelessodyssey.states.MainMenuState;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

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
    public void step(Game game, GUI.Action action, double time) throws IOException, URISyntaxException, FontFormatException {
        if (action == QUIT) {
            game.setState(null);
        } else {
            playerController.step(game, action, time);
            if (getModel().isDying())
                getModel().getPlayer().setPosition(getModel().getStartingPosition());
            particleController.step(game, action, time);
            if (getModel().isAtTransitionPosition()) {
                if (getModel().getSceneCode() + 1 == getNumberOfLevels()) {
                    game.setState(new MainMenuState(new MainMenu()));
                } else {
                    game.setState(new GameState(new SceneBuilder((getModel().getSceneCode() + 1) % 3).createScene()));
                }
            }
        }
    }
}
