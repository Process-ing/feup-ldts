package timelessodyssey.model.menu;

import java.util.List;

public class SettingsMenu extends Menu {
    @Override
    protected List<Entry> createEntries() {
        Entry resolution = new Entry(15, 55, Entry.Type.RESOLUTION);
        Entry goBack = new Entry(15, 75, Entry.Type.TO_MAIN_MENU);
        return List.of(resolution, goBack);
    }
}
