package timelessodyssey.control.game;

import timelessodyssey.control.Controller;
import timelessodyssey.model.game.map.Scene;

public abstract class GameController extends Controller<Scene> {

    public GameController(Scene scene) {
        super(scene);
    }
}
