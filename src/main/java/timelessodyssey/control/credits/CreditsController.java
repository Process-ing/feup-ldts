package timelessodyssey.control.credits;

import timelessodyssey.Game;
import timelessodyssey.control.Controller;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.credits.Credits;
import timelessodyssey.model.menu.MainMenu;
import timelessodyssey.states.MainMenuState;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static timelessodyssey.gui.GUI.Action.*;

public class CreditsController extends Controller<Credits> {
    public CreditsController(Credits model) {
        super(model);
    }

    @Override
    public void step(Game game, GUI.Action action, double time) throws IOException, URISyntaxException, FontFormatException {
        if (action == QUIT) {
            game.setState(new MainMenuState(new MainMenu()));
        }
    }
}
