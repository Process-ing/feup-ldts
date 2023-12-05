package timelessodyssey.model.menu.main.entries;

import timelessodyssey.Game;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.menu.Entry;

public class ExitEntry extends Entry {
    public ExitEntry(int x, int y) {
        super(x, y);
    }

    @Override
    protected String createEntryText() {
        return "Exit";
    }

    @Override
    public void doAction(Game game, GUI gui, GUI.Action action) {
        if (action == GUI.Action.SELECT)
            game.setState(null);
    }
}
