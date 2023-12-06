package timelessodyssey.control.menu;

import timelessodyssey.Game;
import timelessodyssey.model.menu.MainMenu;

public class MainMenuController extends MenuController<MainMenu> {
    public MainMenuController(MainMenu menu, EntryController entryController) {
        super(menu, entryController);
    }

    @Override
    protected void onQuit(Game game) {
        game.setState(null);
    }
}
