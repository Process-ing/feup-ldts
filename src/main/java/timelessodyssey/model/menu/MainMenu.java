package timelessodyssey.model.menu;

import java.util.Arrays;
import java.util.List;

public class MainMenu extends Menu {
    @Override
    protected List<Entry> createEntries() {
        Entry start = new Entry(5, 55, Entry.Type.START_GAME);
        Entry settings = new Entry(5, 65, Entry.Type.SETTINGS);
        Entry exit = new Entry(5, 75, Entry.Type.EXIT);
        return Arrays.asList(start, settings, exit);
    }
}
