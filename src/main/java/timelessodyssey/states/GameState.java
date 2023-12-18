package timelessodyssey.states;

import timelessodyssey.control.Controller;
import timelessodyssey.control.game.ParticleController;
import timelessodyssey.control.game.PlayerController;
import timelessodyssey.control.game.SceneController;
import timelessodyssey.model.game.scene.Scene;
import timelessodyssey.view.SpriteLoader;
import timelessodyssey.view.screens.GameViewer;
import timelessodyssey.view.screens.ScreenViewer;

import java.io.IOException;

public class GameState extends State<Scene> {
    public GameState(Scene model, SpriteLoader spriteLoader) throws IOException {
        super(model, spriteLoader);
    }

    @Override
    protected Controller<Scene> createController() {
        return new SceneController(getModel(), new PlayerController(getModel()), new ParticleController(getModel()));
    }

    @Override
    protected ScreenViewer<Scene> createScreenViewer(SpriteLoader spriteLoader) throws IOException {
        return new GameViewer(getModel(), spriteLoader);
    }

    @Override
    protected boolean allowArrowSpam() {
        return true;
    }
}
