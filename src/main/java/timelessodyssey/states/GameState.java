package timelessodyssey.states;

import timelessodyssey.Game;
import timelessodyssey.control.Controller;
import timelessodyssey.control.game.ParticleController;
import timelessodyssey.control.game.PlayerController;
import timelessodyssey.control.game.SceneController;
import timelessodyssey.gui.GUI;
import timelessodyssey.gui.LanternaGUI;
import timelessodyssey.model.game.scene.Scene;
import timelessodyssey.view.screens.GameViewer;
import timelessodyssey.view.screens.ScreenViewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class GameState extends State<Scene> {
    public GameState(Scene model) throws IOException {
        super(model);
    }

    @Override
    public void step(Game game, GUI gui, long frameCount) throws IOException, URISyntaxException, FontFormatException {
        if (gui instanceof LanternaGUI lanternaGUI)
            lanternaGUI.setArrowSpam(true);
        super.step(game, gui, frameCount);
    }

    @Override
    protected Controller<Scene> createController() {
        return new SceneController(getModel(), new PlayerController(getModel()), new ParticleController(getModel()));
    }

    @Override
    protected ScreenViewer<Scene> createScreenViewer() throws IOException {
        return new GameViewer(getModel());
    }
}
