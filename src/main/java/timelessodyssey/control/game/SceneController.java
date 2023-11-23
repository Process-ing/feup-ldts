package timelessodyssey.control.game;

import timelessodyssey.Game;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.game.map.Scene;
import timelessodyssey.model.menu.Menu;
import timelessodyssey.states.MenuState;

import java.io.IOException;

import static timelessodyssey.gui.GUI.Action.QUIT;

public class SceneController extends GameController{
    private final PlayerController playerController;

    public SceneController(Scene scene){
        super(scene);
        this.playerController = new PlayerController(scene);
    }

    @Override
    public void step(Game game, GUI.Action action, long time) {
        if (action == QUIT)
            game.setState(new MenuState(new Menu()));
        else {
            playerController.step(game, action, time);
        }
    }
}
