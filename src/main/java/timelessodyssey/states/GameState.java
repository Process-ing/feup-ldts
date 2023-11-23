package timelessodyssey.states;

import timelessodyssey.control.Controller;
import timelessodyssey.control.game.SceneController;
import timelessodyssey.model.game.map.Scene;
import timelessodyssey.view.screens.GameViewer;
import timelessodyssey.view.screens.ScreenViewer;

public class GameState extends State<Scene> {
    public GameState(Scene model) {
        super(model);
    }

    @Override
    protected Controller<Scene> getController() {
        return new SceneController(model);
    }

    @Override
    protected ScreenViewer<Scene> getScreenViewer() {
        return new GameViewer(model);
    }
}
