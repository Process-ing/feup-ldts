package timelessodyssey.states;

import timelessodyssey.control.Controller;
import timelessodyssey.control.menu.EntryController;
import timelessodyssey.control.menu.MainMenuController;
import timelessodyssey.model.menu.MainMenu;
import timelessodyssey.view.SpriteLoader;
import timelessodyssey.view.screens.MenuViewer;
import timelessodyssey.view.screens.ScreenViewer;

import java.io.IOException;

public class MainMenuState extends State<MainMenu> {
    public MainMenuState(MainMenu model, SpriteLoader spriteLoader) throws IOException {
        super(model, spriteLoader);
    }

    @Override
    protected ScreenViewer<MainMenu> createScreenViewer(SpriteLoader spriteLoader) throws IOException {
        return new MenuViewer<>(getModel());
    }

    @Override
    protected Controller<MainMenu> createController() {
        return new MainMenuController(getModel(), new EntryController(getModel()));
    }

    @Override
    protected boolean allowArrowSpam() {
        return false;
    }
}
