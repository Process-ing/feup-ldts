package timelessodyssey.control.game;

import timelessodyssey.Game;
import timelessodyssey.control.Controller;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.Vector;
import timelessodyssey.model.game.elements.Player;
import timelessodyssey.model.game.scene.Scene;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static timelessodyssey.Game.PIXEL_HEIGHT;
import static timelessodyssey.Game.PIXEL_WIDTH;

public class PlayerController extends Controller<Scene> {

    public PlayerController(Scene scene) {
        super(scene);
    }

    @Override
    public void step(Game game, GUI.Action action, long frameCount) {
        Player player = getModel().getPlayer();
        double x = player.getPosition().x(), y = player.getPosition().y();
        double vx = player.getVelocity().x(), vy = player.getVelocity().y();
        vy += getModel().getGravity();
        vx *= getModel().getFriction();

        if (action == GUI.Action.LEFT) {
            vx = max(vx - player.getAcceleration(), -player.getMaxVelocity().x());
            player.setFacingRight(false);
        } if (action == GUI.Action.RIGHT) {
            vx = Math.min(vx + player.getAcceleration(), player.getMaxVelocity().x());
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

            vy = Math.min(vy, player.getMaxVelocity().y());
            if (getModel().isColliding(new Vector(x, y + vy), Scene.Direction.DOWN)) {
                player.setHasLanded(true);
                player.setFalling(false);
                do {
                    vy = max(vy - 1, 0);
                } while (getModel().isColliding(new Vector(x, y + vy), Scene.Direction.DOWN) && vy > 0);
            }
        } else if (vy < 0) {
            player.setJumping(true);
            while (getModel().isColliding(new Vector(x, y + vy), Scene.Direction.UP) && vy < 0) {
                vy = Math.min(vy + 1, 0);
            }
        }

        if (vx < 0) {
            vx = max(vx, -player.getMaxVelocity().x());
            while (getModel().isColliding(new Vector(x + vx, y + vy), Scene.Direction.LEFT) && vx < 0) {
                vx = Math.min(vx + 1, 0);
            }
        } else if (vx > 0) {
            vx = Math.min(vx, player.getMaxVelocity().y());
            while (getModel().isColliding(new Vector(x + vx, y + vy), Scene.Direction.RIGHT) && vx > 0) {
                vx = max(vx - 1, 0);
            }
        }

        if (Math.abs(vx) < 0.2)
            vx = 0;
        x += vx;
        y += vy;

        x = max(0, min(PIXEL_WIDTH - getModel().getPlayer().getWidth(), x));
        y = max(0, min(PIXEL_HEIGHT - getModel().getPlayer().getHeight(), y));

        player.setVelocity(new Vector(vx, vy));
        player.setPosition(new Vector(x, y));
    }
}
