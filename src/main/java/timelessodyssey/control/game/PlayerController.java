package timelessodyssey.control.game;

import timelessodyssey.Game;
import timelessodyssey.control.Controller;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.Position;
import timelessodyssey.model.game.scene.Scene;

public class PlayerController extends Controller<Scene> {

    private boolean isGrounded = true;
    private boolean isJumping = false;

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
        if (isGrounded && !isJumping) {
            getModel().getPlayer().setvy(0);
            getModel().getPlayer().setFy(-400);
            isGrounded = false;
            isJumping = true;
        }
        movePlayer(time);
    }

    private void movePlayer(double time) {
        Position position = getModel().getPlayer().move(time);
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
                getModel().getPlayer().setvx(0);
                getModel().getPlayer().setvy(0);
                movePlayer(time);
                break;
        }
    }
}
