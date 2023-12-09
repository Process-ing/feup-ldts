package timelessodyssey.control.game;

import timelessodyssey.Game;
import timelessodyssey.control.Controller;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.game.elements.Player;
import timelessodyssey.model.game.scene.Scene;

public class PlayerController extends Controller<Scene> {

    public PlayerController(Scene scene) {
        super(scene);
    }

    @Override
    public void step(Game game, GUI.Action action, double time) {
        Player player = getModel().getPlayer();
        player.setPosition(player.updatePosition(action, getModel()));
    }
}
