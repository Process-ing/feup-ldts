package timelessodyssey.control.game;

import timelessodyssey.Game;
import timelessodyssey.control.Controller;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.game.elements.player.Player;
import timelessodyssey.model.game.scene.Scene;

public class PlayerController extends Controller<Scene> {

    public PlayerController(Scene scene) {
        super(scene);
    }

    @Override
    public void step(Game game, GUI.Action action, long frameCount) {
        Player player = getModel().getPlayer();
        switch (action) {
            case LEFT:
                player.setVelocity(player.moveLeft());
                player.setFacingRight(false);
                break;
            case RIGHT:
                player.setVelocity(player.moveRight());
                player.setFacingRight(true);
                break;
            case JUMP:
                player.setVelocity(player.jump());
                break;
            case DASH:
                player.setVelocity(player.dash());
                break;
            default:
                player.setVelocity(player.updateVelocity());
        }
        player.setPosition(player.updatePosition());
        player.setState(player.getNextState());
    }
}
