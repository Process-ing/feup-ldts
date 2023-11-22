package timelessodyssey.control.game;

import timelessodyssey.Game;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.menu.Menu;
import timelessodyssey.states.MenuState;

import java.io.IOException;

public class SceneController extends GameController{
    private final PlayerController playerController;

    public SceneController(Scene scene){
        super(scene);
        this.playerController = new PlayerController(scene);
    }

    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        if (action == GUI.ACTION.QUIT)
            game.setState(new MenuState(new Menu()));
        else {
            playerController.step(game, action, time);
        }
    }
}
