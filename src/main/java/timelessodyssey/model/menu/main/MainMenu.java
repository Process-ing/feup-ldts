package timelessodyssey.model.menu.main;

import timelessodyssey.model.menu.Entry;
import timelessodyssey.model.menu.Menu;
import timelessodyssey.model.menu.main.entries.ExitEntry;
import timelessodyssey.model.menu.main.entries.SettingsEntry;
import timelessodyssey.model.menu.main.entries.StartEntry;

import java.util.Arrays;
import java.util.List;

public class MainMenu extends Menu {
    @Override
    protected List<Entry> createEntries() {
        Entry start = new StartEntry(5, 55);
        Entry settings = new SettingsEntry(5, 65);
        Entry exit = new ExitEntry(5, 75);
        return Arrays.asList(start, settings, exit);
    }
}
