package timelessodyssey.control.game;

import timelessodyssey.Game;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.Position;
import timelessodyssey.model.game.*;

public class PlayerController extends GameController {

    public PlayerController(Scene scene) {
        super(scene);
    }

    public void movePlayerLeft() {
        movePlayer(this.getModel().getPlayer().getPosition().getLeft());
    }

    public void movePlayerRight() {
        movePlayer(this.getModel().getPlayer().getPosition().getRight());
    }

    public void movePlayerUp() {
        movePlayer(this.getModel().getPlayer().getPosition().getUp());
    }

    public void movePlayerDown() {
        movePlayer(this.getModel().getPlayer().getPosition().getDown());
    }

    private void movePlayer(Position position) {
        this.getModel().getPlayer().setPosition(position);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) {
        switch (action){
            case GUI.ACTION.UP:
                movePlayerUp();
                break;
            case GUI.ACTION.DOWN:
                movePlayerDown();
                break;
            case GUI.ACTION.LEFT:
                movePlayerLeft();
                break;
            case GUI.ACTION.RIGHT:
                movePlayerRight();
                break;
        }
    }
}
