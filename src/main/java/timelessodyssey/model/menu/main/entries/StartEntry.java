package timelessodyssey.model.menu.main.entries;

import timelessodyssey.Game;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.game.scene.SceneBuilder;
import timelessodyssey.model.menu.Entry;
import timelessodyssey.states.GameState;

import java.io.IOException;

public class StartEntry extends Entry {
    public StartEntry(int x, int y) {
        super(x, y);
    }

    @Override
    protected String createEntryText() {
        return "Start";
    }

    @Override
    public void doAction(Game game, GUI gui, GUI.Action action) throws IOException {
        if (action == GUI.Action.SELECT)
            game.setState(new GameState(new SceneBuilder().createScene()));
    }
}
