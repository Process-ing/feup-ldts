package timelessodyssey.model.menu.main.entries;

import timelessodyssey.Game;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.menu.Entry;
import timelessodyssey.model.menu.settings.SettingsMenu;
import timelessodyssey.states.SettingsMenuState;

import java.io.IOException;

public class SettingsEntry extends Entry {
    public SettingsEntry(int x, int y) {
        super(x, y);
    }

    @Override
    protected String createEntryText() {
        return "Settings";
    }

    @Override
    public void doAction(Game game, GUI gui, GUI.Action action) throws IOException {
        if (action == GUI.Action.SELECT)
            game.setState(new SettingsMenuState(new SettingsMenu()));
    }
}
