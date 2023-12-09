package timelessodyssey.control.game;

import timelessodyssey.Game;
import timelessodyssey.control.Controller;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.Vector;
import timelessodyssey.model.game.elements.Player;
import timelessodyssey.model.game.scene.Scene;

public class PlayerController extends Controller<Scene> {

    public PlayerController(Scene scene) {
        super(scene);
    }

    @Override
    public void step(Game game, GUI.Action action, double time) {
        Player player = getModel().getPlayer();
        double x = player.getPosition().x(), y = player.getPosition().y();
        double vx = player.getVelocity().x(), vy = player.getVelocity().y();
        vy += getModel().getGravity();
        vx *= getModel().getFriction();

        double MAX_VELOCITY_X = 2;
        if (action == GUI.Action.LEFT) {
            vx = Math.max(vx - player.getAcceleration(), -MAX_VELOCITY_X);
            player.setFacingRight(false);
        } if (action == GUI.Action.RIGHT) {
            vx = Math.min(vx + player.getAcceleration(), MAX_VELOCITY_X);
            player.setFacingRight(true);
        }

        if (action == GUI.Action.JUMP && player.hasLanded()) {
            vy = -player.getBoost();
            player.setHasLanded(false);
        }

        if (vy > 0) {
            player.setFalling(true);
            player.setHasLanded(false);
            player.setJumping(false);

            double MAX_VELOCITY_Y = 3;
            vy = Math.min(vy, MAX_VELOCITY_Y);
            if (getModel().isPlayerColliding(new Vector(x, y + vy), Scene.Direction.DOWN)) {
                player.setHasLanded(true);
                player.setFalling(false);
                vy = 0;
            }
        } else if (vy < 0) {
            player.setJumping(true);
            if (getModel().isPlayerColliding(new Vector(x, y + vy), Scene.Direction.UP)) {
                vy = 0;
            }
        }

        if (vx < 0) {
            vx = Math.max(vx, -MAX_VELOCITY_X);
            if (getModel().isPlayerColliding(new Vector(x + vx, y + vy), Scene.Direction.LEFT)) {
                vx = 0;
            }
        } else if (vx > 0) {
            vx = Math.min(vx, MAX_VELOCITY_X);
            if (getModel().isPlayerColliding(new Vector(x + vx, y + vy), Scene.Direction.RIGHT)) {
                vx = 0;
            }
        }

        if (Math.abs(vx) < 0.2)
            vx = 0;
        x += vx;
        y += vy;

        player.setVelocity(new Vector(vx, vy));
        player.setPosition(new Vector(x, y));
    }
}
