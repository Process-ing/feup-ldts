package timelessodyssey.states;

import timelessodyssey.Game;
import timelessodyssey.control.Controller;
import timelessodyssey.control.menu.EntryController;
import timelessodyssey.control.menu.SettingsMenuController;
import timelessodyssey.gui.GUI;
import timelessodyssey.gui.LanternaGUI;
import timelessodyssey.model.menu.SettingsMenu;
import timelessodyssey.view.screens.MenuViewer;
import timelessodyssey.view.screens.ScreenViewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class SettingsMenuState extends State<SettingsMenu> {
    public SettingsMenuState(SettingsMenu menu) throws IOException {
        super(menu);
    }

    @Override
    public void step(Game game, GUI gui, long frameCount) throws IOException, URISyntaxException, FontFormatException {
        if (gui instanceof LanternaGUI lanternaGUI)
            lanternaGUI.setArrowSpam(false);
        super.step(game, gui, frameCount);
    }

    @Override
    protected Controller<SettingsMenu> createController() {
        return new SettingsMenuController(getModel(), new EntryController(getModel()));
    }

    @Override
    protected ScreenViewer<SettingsMenu> createScreenViewer() throws IOException {
        return new MenuViewer<>(getModel());
    }
}
