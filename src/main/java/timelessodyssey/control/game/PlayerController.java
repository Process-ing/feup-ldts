package timelessodyssey.control.game;

import timelessodyssey.Game;
import timelessodyssey.control.Controller;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.Position;
import timelessodyssey.model.game.elements.Tile;
import timelessodyssey.model.game.scene.Scene;

public class PlayerController extends Controller<Scene> {

    public PlayerController(Scene scene) {
        super(scene);
    }

    private void movePlayerLeft() {
        Position position = getModel().getPlayer().getPosition();
        movePlayer(new Position(position.x() - 1, position.y()));
    }

    private void movePlayerRight() {
        Position position = getModel().getPlayer().getPosition();
        movePlayer(new Position(position.x() + 1, position.y()));
    }

    private void movePlayerUp() {
        Position position = getModel().getPlayer().getPosition();
        movePlayer(new Position(position.x(), position.y() - 1));
    }

    private void movePlayerDown() {
        Position position = getModel().getPlayer().getPosition();
        movePlayer(new Position(position.x(), position.y() + 1));
    }

    private void movePlayer(Position position) {
        Position position2 = new Position(position.x() + 5, position.y());
        Position position3 = new Position(position.x() + 5, position.y() + Tile.SIZE - 1);
        Position position4 = new Position(position.x(), position.y() + Tile.SIZE - 1);
        if (this.getModel().isEmpty(position) && this.getModel().isEmpty(position2) && this.getModel().isEmpty(position3) && this.getModel().isEmpty(position4)){
            this.getModel().getPlayer().setPosition(position);
        }
    }

    @Override
    public void step(Game game, GUI.Action action, double time) {
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
