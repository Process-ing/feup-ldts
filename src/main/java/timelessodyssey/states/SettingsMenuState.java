package timelessodyssey.states;

import timelessodyssey.control.Controller;
import timelessodyssey.control.menu.EntryController;
import timelessodyssey.control.menu.SettingsMenuController;
import timelessodyssey.model.menu.SettingsMenu;
import timelessodyssey.view.SpriteLoader;
import timelessodyssey.view.ViewerProvider;
import timelessodyssey.view.screens.MenuViewer;
import timelessodyssey.view.screens.ScreenViewer;

import java.io.IOException;

public class SettingsMenuState extends State<SettingsMenu> {
    public SettingsMenuState(SettingsMenu menu, SpriteLoader spriteLoader) throws IOException {
        super(menu, spriteLoader);
    }

    @Override
    protected Controller<SettingsMenu> createController() {
        return new SettingsMenuController(getModel(), new EntryController(getModel()));
    }

    @Override
    protected ScreenViewer<SettingsMenu> createScreenViewer(ViewerProvider viewerProvider) {
        return new MenuViewer<>(getModel(), viewerProvider);
    }

    @Override
    protected boolean allowArrowSpam() {
        return false;
    }
}
