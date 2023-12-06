package timelessodyssey.states;

import timelessodyssey.control.Controller;
import timelessodyssey.control.menu.EntryController;
import timelessodyssey.control.menu.MainMenuController;
import timelessodyssey.model.menu.MainMenu;
import timelessodyssey.view.screens.MenuViewer;
import timelessodyssey.view.screens.ScreenViewer;

import java.io.IOException;

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
}
