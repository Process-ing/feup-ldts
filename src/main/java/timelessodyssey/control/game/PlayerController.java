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

    private void movePlayerLeft() {
        movePlayer(this.getModel().getPlayer().getPosition().getLeft());
    }

    private void movePlayerRight() {
        movePlayer(this.getModel().getPlayer().getPosition().getRight());
    }

    private void movePlayerUp() {
        movePlayer(this.getModel().getPlayer().getPosition().getUp());
    }

    private void movePlayerDown() {
        movePlayer(this.getModel().getPlayer().getPosition().getDown());
    }

    private void movePlayer(Position position) {
        Position position2 = new Position(position.x() + 7, position.y());
        Position position3 = new Position(position.x() + 7, position.y() + 7);
        Position position4 = new Position(position.x(), position.y() + 7);
        if (this.getModel().isEmpty(position) && this.getModel().isEmpty(position2) && this.getModel().isEmpty(position3) && this.getModel().isEmpty(position4)){
            this.getModel().getPlayer().setPosition(position);
        }
    }

    @Override
    public void step(Game game, GUI.Action action, long time) {
        switch (action){
            case UP:
                movePlayerUp();
                break;
            case DOWN:
                movePlayerDown();
                break;
            case LEFT:
                movePlayerLeft();
                break;
            case RIGHT:
                movePlayerRight();
                break;
            default:
        }
    }
}
