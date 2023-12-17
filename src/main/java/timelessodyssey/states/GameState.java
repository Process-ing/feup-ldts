package timelessodyssey.states;

import timelessodyssey.control.Controller;
import timelessodyssey.control.game.ParticleController;
import timelessodyssey.control.game.PlayerController;
import timelessodyssey.control.game.SceneController;
import timelessodyssey.model.game.scene.Scene;
import timelessodyssey.view.screens.GameViewer;
import timelessodyssey.view.screens.ScreenViewer;

import java.io.IOException;

public class GameState extends State<Scene> {
    public GameState(Scene model) throws IOException {
        super(model);
    }

    @Override
    protected Controller<Scene> createController() {
        return new SceneController(getModel(), new PlayerController(getModel()), new ParticleController(getModel()));
    }

    @Override
    protected ScreenViewer<Scene> createScreenViewer() throws IOException {
        return new GameViewer(getModel());
    }

    @Override
    protected boolean allowArrowSpam() {
        return true;
    }
}
