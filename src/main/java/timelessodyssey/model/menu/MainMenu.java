package timelessodyssey.model.menu;

import timelessodyssey.Game;

import java.util.Arrays;
import java.util.List;

public class MainMenu extends Menu {
    @Override
    protected List<Entry> createEntries() {
        Entry start = new Entry(Game.PIXEL_WIDTH / 2 - (5 / 2) * 3 - (5 / 2) - 2, 55, Entry.Type.START_GAME);
        Entry settings = new Entry(Game.PIXEL_WIDTH / 2 - (8 / 2) * 3 - (8 / 2), 65, Entry.Type.SETTINGS);
        Entry exit = new Entry(Game.PIXEL_WIDTH / 2 - (4 / 2) * 3 - (4 / 2), 75, Entry.Type.EXIT);
        return Arrays.asList(start, settings, exit);
    }
}
