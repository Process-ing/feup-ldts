package timelessodyssey.states;

import timelessodyssey.Game;
import timelessodyssey.control.Controller;
import timelessodyssey.control.credits.CreditsController;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.credits.Credits;
import timelessodyssey.view.screens.CreditsViewer;
import timelessodyssey.view.screens.ScreenViewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class CreditsState extends State<Credits> {
    public CreditsState(Credits model) throws IOException {
        super(model);
    }

    @Override
    protected ScreenViewer<Credits> createScreenViewer() throws IOException {
        return new CreditsViewer(getModel());
    }

    @Override
    protected Controller<Credits> createController() {
        return new CreditsController(getModel());
    }

    @Override
    public void step(Game game, GUI gui, double time) throws IOException, URISyntaxException, FontFormatException {
        GUI.Action action = gui.getNextAction();
        controller.step(game, action, time);
        screenViewer.draw(gui);
    }
}
