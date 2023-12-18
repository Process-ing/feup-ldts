package timelessodyssey.control.menu;

import timelessodyssey.Game;
import timelessodyssey.model.menu.MainMenu;
import timelessodyssey.model.menu.SettingsMenu;
import timelessodyssey.states.MainMenuState;

import java.io.IOException;

public class SettingsMenuController extends MenuController<SettingsMenu> {
    public SettingsMenuController(SettingsMenu menu, EntryController entryController) {
        super(menu, entryController);
    }

    @Override
    protected void onQuit(Game game) throws IOException {
        game.setState(new MainMenuState(new MainMenu(), game.getSpriteLoader()));
    }
}
