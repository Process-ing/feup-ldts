package timelessodyssey.model.menu.settings;

import timelessodyssey.model.menu.Entry;
import timelessodyssey.model.menu.Menu;
import timelessodyssey.model.menu.settings.entries.GoBackEntry;
import timelessodyssey.model.menu.settings.entries.ResolutionEntry;

import java.util.List;

public class SettingsMenu extends Menu {
    @Override
    protected List<Entry> createEntries() {
        Entry resolution = new ResolutionEntry(5, 55);
        Entry goBack = new GoBackEntry(5, 75);
        return List.of(resolution, goBack);
    }
}
