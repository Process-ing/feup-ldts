package timelessodyssey.control.menu;

import timelessodyssey.Game;
import timelessodyssey.model.menu.main.MainMenu;

public class MainMenuController extends MenuController<MainMenu> {
    public MainMenuController(MainMenu menu) {
        super(menu);
    }

    @Override
    protected void onQuit(Game game) {
        game.setState(null);
    }
}
