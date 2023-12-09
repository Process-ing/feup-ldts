package timelessodyssey.states;

import timelessodyssey.Game;
import timelessodyssey.control.Controller;
import timelessodyssey.control.menu.EntryController;
import timelessodyssey.control.menu.MainMenuController;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.menu.MainMenu;
import timelessodyssey.view.screens.MenuViewer;
import timelessodyssey.view.screens.ScreenViewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class MainMenuState extends State<MainMenu> {

    public MainMenuState(MainMenu model) throws IOException { super(model); }

    @Override
    protected ScreenViewer<MainMenu> createScreenViewer() throws IOException {
        return new MenuViewer<>(getModel());
    }

    @Override
    protected Controller<MainMenu> createController() {
        return new MainMenuController(getModel(), new EntryController(getModel()));
    }

    @Override
    public void step(Game game, GUI gui, double time) throws IOException, URISyntaxException, FontFormatException {
        GUI.Action action = gui.getNextAction();
        controller.step(game, action, time);
        screenViewer.draw(gui);
    }
}
