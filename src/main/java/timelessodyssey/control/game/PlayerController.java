package timelessodyssey.control.game;

import timelessodyssey.Game;
import timelessodyssey.control.Controller;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.Position;
import timelessodyssey.model.game.scene.Scene;

public class PlayerController extends Controller<Scene> {

    private boolean isGrounded = true;

    public PlayerController(Scene scene) {
        super(scene);
    }

    private void movePlayerLeft(double time) {
        if (getModel().getPlayer().getVx() >= 0){
            getModel().getPlayer().setvx(-20);
        }
        getModel().getPlayer().setFx(-20);
        movePlayer(time);
    }

    private void movePlayerRight(double time) {
        if (getModel().getPlayer().getVx() <= 0){
            getModel().getPlayer().setvx(20);
        }
        getModel().getPlayer().setFx(20);
        movePlayer(time);
    }

    private void jump(double time) {
        if (isGrounded) {
            getModel().getPlayer().setvy(0);
            getModel().getPlayer().setFy(-4500);
            isGrounded = false;
        }
        movePlayer(time);
    }

    private void continueMovement(double time) {
        if (isGrounded){
            getModel().getPlayer().setvx(0);
        }
        movePlayer(time);
    }

    private void movePlayer(double time) {
        Position position = getModel().getPlayer().move(time);
        if (getModel().getPlayer().getPosition().y() >= 71.5){
            isGrounded = true;
        }
        position = getModel().checkColisions(position);

        this.getModel().getPlayer().setPosition(position);
    }

    @Override
    public void step(Game game, GUI.Action action, double time) {
        switch (action){
            case LEFT:
                movePlayerLeft(time);
                break;
            case RIGHT:
                movePlayerRight(time);
                break;
            case JUMP:
                jump(time);
                break;
            default:
                continueMovement(time);
                break;
        }
    }
}
