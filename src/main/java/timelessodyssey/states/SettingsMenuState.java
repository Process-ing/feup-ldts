package timelessodyssey.states;

import timelessodyssey.control.Controller;
import timelessodyssey.control.menu.EntryController;
import timelessodyssey.control.menu.SettingsMenuController;
import timelessodyssey.model.menu.SettingsMenu;
import timelessodyssey.view.screens.MenuViewer;
import timelessodyssey.view.screens.ScreenViewer;

import java.io.IOException;

public class SettingsMenuState extends State<SettingsMenu> {
    public SettingsMenuState(SettingsMenu menu) throws IOException {
        super(menu);
    }

    @Override
    protected Controller<SettingsMenu> createController() {
        return new SettingsMenuController(getModel(), new EntryController(getModel()));
    }

    @Override
    protected ScreenViewer<SettingsMenu> createScreenViewer() throws IOException {
        return new MenuViewer<>(getModel());
    }

    @Override
    protected boolean allowArrowSpam() {
        return false;
    }
}
