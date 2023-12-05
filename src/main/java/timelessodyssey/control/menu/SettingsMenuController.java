package timelessodyssey.control.menu;

import timelessodyssey.Game;
import timelessodyssey.model.menu.main.MainMenu;
import timelessodyssey.model.menu.settings.SettingsMenu;
import timelessodyssey.states.MainMenuState;

import java.io.IOException;

public class SettingsMenuController extends MenuController<SettingsMenu> {
    public SettingsMenuController(SettingsMenu menu) {
        super(menu);
    }

    @Override
    protected void onQuit(Game game) throws IOException {
        game.setState(new MainMenuState(new MainMenu()));
    }
}
