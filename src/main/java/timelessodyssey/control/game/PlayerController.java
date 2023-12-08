package timelessodyssey.control.game;

import timelessodyssey.Game;
import timelessodyssey.control.Controller;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.Position;
import timelessodyssey.model.game.scene.Scene;

public class PlayerController extends Controller<Scene> {

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
        if (getModel().isGrounded()) {
            getModel().getPlayer().setvy(0);
            getModel().getPlayer().setFy(-4500);
        }
        movePlayer(time);
    }

    private void continueMovement(double time) {
        if (getModel().isGrounded()){
            getModel().getPlayer().setvx(0);
        }
        movePlayer(time);
    }

    private void movePlayer(double time) {
        Position position = getModel().getPlayer().move(time, getModel().isGrounded());
        position = getModel().resolveCollisions(position);
        if (!getModel().getPlayer().getPosition().equals(position)) {
            System.out.printf("x=%f\ty=%f\n", position.x(), position.y());
        }
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
