package timelessodyssey.model.menu.settings.entries;

import timelessodyssey.Game;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.menu.Entry;
import timelessodyssey.model.menu.main.MainMenu;
import timelessodyssey.states.MainMenuState;

import java.io.IOException;

public class GoBackEntry extends Entry {
    public GoBackEntry(int x, int y) {
        super(x, y);
    }

    @Override
    protected String createEntryText() {
        return "Go Back";
    }

    @Override
    public void doAction(Game game, GUI gui, GUI.Action action) throws IOException {
        if (action == GUI.Action.SELECT)
            game.setState(new MainMenuState(new MainMenu()));
    }
}
